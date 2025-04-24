import java.util.*;

public class SmartHomeSystem {
    private final Map<String, Device> devices = new LinkedHashMap<>();
    private final List<Rule> rules = new ArrayList<>();

    public String addDevice(String type, String name, String protocol) {
        if (!type.equals("light") && !type.equals("thermostat")) {
            return "invalid input";
        }

        if (!protocol.equals("WiFi") && !protocol.equals("Bluetooth")) {
            return "invalid input";
        }

        if (!name.matches("[a-z0-9]{1,20}")) {
            return "invalid input";
        }

        if (devices.containsKey(name)) return "duplicate device name";

        Device device;
        if (type.equals("light")) {
            device = new Light(name, protocol);
        } else {
            device = new Thermostat(name, protocol);
        }

        devices.put(name, device);
        return "device added successfully";
    }

    public String setDevice(String name, String property, String value) throws InvalidPropertyException {
        Device device = devices.get(name);
        if (device == null) return "device not found";

        if (device instanceof Light) {
            ((Light) device).setProperty(property, value);
        } else if (device instanceof Thermostat) {
            ((Thermostat) device).setProperty(property, value);
        } else {
            throw new InvalidPropertyException("invalid property");
        }

        return "device updated successfully";
    }

    public String removeDevice(String name) {
        if (!devices.containsKey(name)) return "device not found";

        devices.remove(name);
        rules.removeIf(r -> r.getDeviceName().equals(name));
        return "device removed successfully";
    }

    public String listDevices(boolean detailed) {
        if (devices.isEmpty()) return "";
        StringBuilder sb = new StringBuilder();

        List<Device> reverseDevices = new ArrayList<>(devices.values());
        Collections.reverse(reverseDevices);
        for (Device d : reverseDevices) {
            sb.append(d.getInfo()).append("\n");
        }
        return sb.toString().trim();
    }

    public String addRule(Rule rule) {
        if (!devices.containsKey(rule.getDeviceName())) return "device not found";
        if (!rule.getAction().equals("on") && !rule.getAction().equals("off")) return "invalid action";
        if (!rule.getTime().matches("\\d{2}:\\d{2}") || !isValidTime(rule.getTime())) return "invalid time";

        for (Rule r : rules) {
            if (r.getDeviceName().equals(rule.getDeviceName()) && r.getTime().equals(rule.getTime())) {
                return "duplicate rule";
            }
        }

        rules.add(rule);
        return "rule added successfully";
    }

    public String checkRules(String time) {
        if (!time.matches("\\d{2}:\\d{2}") || !isValidTime(time)) return "invalid time";

        for (Rule rule : rules) {
            if (rule.getTime().equals(time)) {
                Device d = devices.get(rule.getDeviceName());
                if (d != null) {
                    try {
                        d.setStatus(rule.getAction());
                    } catch (InvalidPropertyException ignored) {}
                }
            }
        }

        return "rules checked";
    }

    public String listRules() {
        if (rules.isEmpty()) return "";
        StringBuilder sb = new StringBuilder();

        List<Rule> reverseRules = new ArrayList<>(rules);
        Collections.reverse(reverseRules);
        for (Rule r : reverseRules) {
            sb.append(r.getInfo()).append("\n");
        }
        return sb.toString().trim();
    }

    private boolean isValidTime(String time) {
        try {
            String[] parts = time.split(":");
            int h = Integer.parseInt(parts[0]);
            int m = Integer.parseInt(parts[1]);
            return h >= 0 && h < 24 && m >= 0 && m < 60;
        } catch (Exception e) {
            return false;
        }
    }
}
