public class Thermostat extends Device {
    private int temperature;

    public Thermostat(String name, String protocol) {
        super(name, protocol);
        this.temperature = 20;
    }

    public void setProperty(String property, String value) throws InvalidPropertyException {
        switch (property) {
            case "status":
                setStatus(value);
                break;
            case "temperature":
                try {
                    int temp = Integer.parseInt(value);
                    if (temp < 10 || temp > 30) {
                        throw new InvalidPropertyException("invalid value");
                    }
                    this.temperature = temp;
                } catch (NumberFormatException e) {
                    throw new InvalidPropertyException("invalid value");
                }
                break;
            default:
                throw new InvalidPropertyException("invalid property");
        }
    }

    @Override
    public String getInfo() {
        return name + " " + status + " " + temperature + "C " + protocol;
    }
}