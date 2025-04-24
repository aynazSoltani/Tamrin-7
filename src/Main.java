import java.util.Scanner;

public class Main {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        SmartHomeSystem system = new SmartHomeSystem();
        StringBuilder output = new StringBuilder();

        int q = Integer.parseInt(scanner.nextLine().trim());
        for (int i = 0; i < q; i++) {
            String line = scanner.nextLine().trim();

            if (line.isEmpty()) {
                i--;
                continue;
            }

            String[] parts = line.split(" ");
            String command = parts[0];

            try {
                switch (command) {
                    case "add_device":
                        if (parts.length != 4) {
                            output.append("invalid input\n");
                            break;
                        }
                        String type = parts[1];
                        String name = parts[2];
                        String protocol = parts[3];
                        output.append(system.addDevice(type, name, protocol)).append("\n");
                        break;

                    case "set_device":
                        if (parts.length != 4) {
                            output.append("invalid input\n");
                            break;
                        }
                        name = parts[1];
                        String property = parts[2];
                        String value = parts[3];
                        output.append(system.setDevice(name, property, value)).append("\n");
                        break;

                    case "remove_device":
                        if (parts.length != 2) {
                            output.append("invalid input\n");
                            break;
                        }
                        name = parts[1];
                        output.append(system.removeDevice(name)).append("\n");
                        break;

                    case "list_devices":
                        output.append(system.listDevices(true)).append("\n");
                        break;

                    case "add_rule":
                        if (parts.length != 4) {
                            output.append("invalid input\n");
                            break;
                        }
                        String deviceName = parts[1];
                        String time = parts[2];
                        String action = parts[3];
                        Rule rule = new Rule(deviceName, time, action);
                        output.append(system.addRule(rule)).append("\n");
                        break;

                    case "list_rules":
                        output.append(system.listRules()).append("\n");
                        break;

                    case "check_rules":
                        if (parts.length != 2) {
                            output.append("invalid input\n");
                            break;
                        }
                        String checkTime = parts[1];
                        output.append(system.checkRules(checkTime)).append("\n");
                        break;

                    default:
                        output.append("invalid input\n");
                        break;
                }
            } catch (InvalidPropertyException e) {
                output.append(e.getMessage()).append("\n");
            }
        }

        System.out.print(output.toString().trim());
    }
}
