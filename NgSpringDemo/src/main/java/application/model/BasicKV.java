package application.model;


public class BasicKV {
    private String key;
    private String value;

    public BasicKV(String key, String value) {
        this.key = key;
        this.value = value;
    }

    @Override
    public String toString() {
        return "BasicKV{" +
                "key='" + key + '\'' +
                ", value='" + value + '\'' +
                '}';
    }

    public String getKey() {
        return key;
    }

    public void setKey(String key) {
        this.key = key;
    }

    public String getValue() {
        return value;
    }

    public void setValue(String value) {
        this.value = value;
    }
}
