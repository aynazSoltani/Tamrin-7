public abstract class Device {
    protected String name;
    protected String status;
    protected String protocol;

    public Device(String name, String protocol) {
        this.name = name;
        this.protocol = protocol;
        this.status = "off";
    }

    public String getName() {
        return name;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) throws InvalidPropertyException {
        if (!status.equals("on") && !status.equals("off")) {
            throw new InvalidPropertyException("invalid value");
        }
        this.status = status;
    }

    public String getProtocol() {
        return protocol;
    }

    public abstract String getInfo();
}