import java.util.ArrayList;
import java.util.List;
import java.util.SplittableRandom;

public class SmartHomeSystem {
    private List<Device> devices;
    private List<Rule> rules;

    public SmartHomeSystem() {
        devices = new ArrayList<>();
        rules = new ArrayList<>();
    }

    public void addDevice(Device device) {
        devices.add(device);
    }

    public void addRule(Rule rule) {
        rules.add(rule);
    }

    public void listDevices() {
        for (Device device : devices) {
            String statusTxt = "off";
            if (device.getStatus()) {
                statusTxt = "on";
            }
            System.out.println(device.getName() + " " + device.getType() + " " + statusTxt);
        }
    }

    public void checkRules(String ruleTime) {
        for (Rule rule : rules) {
            if (rule.getTime().equals(ruleTime)) {
                System.out.println(rule.getDeviceName() + " " + rule.getAction());
            }
        }
    }

    public void addDevice(String type, String name, String protocol) throws InvalidPropertyException {
        for (Device device : devices) {
            if (device.getName().equals(name)) {
                throw new InvalidPropertyException("duplicate device name");
            }
        }
        if ((!type.equals("light") && !type.equals("thermostat")) || (!protocol.equals("WiFi") && !protocol.equals("Bluetooth"))) {
            throw new InvalidPropertyException("invalid input");
        }
        if (type.equals("light")) {
            devices.add(new Light(name, protocol));
        } else if (type.equals("thermostat")) {
            devices.add(new Thermostat(name, protocol));
        }
        System.out.println("device added successfully");
    }

    public void setDevice(String name, String property, String valueStr) throws InvalidPropertyException {
        Device foundDevice = null;
        for (Device device : devices) {
            if (device.getName().equals(name)) {
                foundDevice = device;
                break;
            }
        }

        if (foundDevice == null) {
            throw new InvalidPropertyException("device not found");
        }

        if (property.equals("status")) {
            boolean statusValue;
            if (valueStr.equals("on")) {
                statusValue = true;
            } else if (valueStr.equals("off")) {
                statusValue = false;
            }
            else {
                throw new InvalidPropertyException("invalid status");
            }
            foundDevice.setStatus(statusValue);
        } else {
            int value;
            try {
                value = Integer.parseInt(valueStr);
            } catch (NumberFormatException e) {
                throw new InvalidPropertyException("invalid value");
            }
            foundDevice.setProperty(property, value);
        }

        System.out.println("device updated successfully");
    }
   public void   removeDevice(String name) throws InvalidPropertyException{
        Device numberOfRemove = null ;
        for (Device device : devices){
           if(device.getName().equals(name)){
               numberOfRemove = device ;
               break;
           }
        }
       if(numberOfRemove == null){
           throw  new InvalidPropertyException("device not found");
       }
      devices.remove(numberOfRemove) ;
       System.out.println("device removed successfully");
   }
}

