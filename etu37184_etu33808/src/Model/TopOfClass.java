package Model;

public class TopOfClass {
    private String className;
    private String serverName;
    private double purcent;

    public TopOfClass(String className, String serverName, double purcent) {
        this.className = className;
        this.serverName = serverName;
        this.purcent = purcent;
    }

    public String getClassName() {
        return className;
    }

    public void setClassName(String className) {
        this.className = className;
    }

    public String getServerName() {
        return serverName;
    }

    public void setServerName(String serverName) {
        this.serverName = serverName;
    }

    public double getPurcent() {
        return purcent;
    }

    public void setPurcent(double purcent) {
        this.purcent = purcent;
    }
}
