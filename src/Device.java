public abstract class Device {
    private String name;
    private String type;
   private boolean status;
    private String protocol;

    public Device(String name, String type, String protocol) {
        this.name = name;
        this.type = type;
        this.protocol = protocol;
        this.status = false;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    public boolean getStatus() {
        return status;
    }

    public void setStatus(boolean status) {
        this.status = status;
    }

    public String getProtocol() {
        return protocol;
    }

    public void setProtocol(String protocol) {
        this.protocol = protocol;
    }

    public abstract void setProperty(String property, int value);
}
