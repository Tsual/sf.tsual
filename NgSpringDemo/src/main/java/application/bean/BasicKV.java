package application.controller.redis.kv;


public class RedisKV {
    private String key;
    private String value;

    public RedisKV(String key, String value) {
        this.key = key;
        this.value = value;
    }

    @Override
    public String toString() {
        return "RedisKV{" +
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
