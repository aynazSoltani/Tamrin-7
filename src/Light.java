public class Light extends Device {
    private int brightness;

    public Light(String name, String protocol) {
        super(name, protocol);
        this.brightness = 50;
    }

    public void setProperty(String property, String value) throws InvalidPropertyException {
        switch (property) {
            case "status":
                setStatus(value);
                break;
            case "brightness":
                try {
                    int brightnessValue = Integer.parseInt(value);
                    if (brightnessValue < 0 || brightnessValue > 100) {
                        throw new InvalidPropertyException("invalid value");
                    }
                    this.brightness = brightnessValue;
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
        return name + " " + status + " " + brightness + "% " + protocol;
    }
}