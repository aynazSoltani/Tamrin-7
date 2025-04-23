public class Thermostat extends Device {
    private int temperature;

    public Thermostat(String name, String protocol) {
        super(name, "thermostat", protocol);
        this.temperature = 20;
        setStatus(false);
    }

    public int getTemperature() {
        return temperature;
    }

    @Override
    public void setProperty(String property, int value) throws InvalidPropertyException {
        if (property.equals("temperature")) {
            if (value >= 10 && value <= 30) {
                this.temperature = value;
            } else {
                throw new InvalidPropertyException("The value must be between 10 and 30");
            }
        } else if (property.equals("status")) {
            if (value == 1) {
                setStatus(true);
            } else if (value == 0) {
                setStatus(false);
            } else {
                throw new InvalidPropertyException("The value must be off or on");
            }
        } else {
            throw new InvalidPropertyException("Unknown property: " + property);
        }
    }
}
