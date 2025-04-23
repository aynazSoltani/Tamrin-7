public class Light extends Device {
    private int brightness;

    public Light(String name, String protocol) {
        super(name, "light", protocol);
        this.brightness = 50;
        setStatus(false);
    }

    public int getBrightness() {
        return brightness;
    }

    @Override
    public void setProperty(String property, int value) throws InvalidPropertyException {
        if (property.equals("brightness")) {
            if (value >= 0 && value <= 100) {
                this.brightness = value;
            } else {
                throw new InvalidPropertyException("The value must be between 0 and 100");
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
