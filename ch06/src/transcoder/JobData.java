package transcoder;

public class JobData {
    private String source;
    private String target;

    public JobData(String src, String tgt) {
        this.source = src;
        this.target = tgt;
    }

    public String getSource() {
        return source;
    }

    public void setSource(String source) {
        this.source = source;
    }

    public String getTarget() {
        return target;
    }

    public void setTarget(String target) {
        this.target = target;
    }
}
