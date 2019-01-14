package sf.learn;

import org.json.JSONArray;
import org.json.JSONObject;
import sf.uds.del.IRun_2;
import sf.util.StringHelper;

import java.util.ArrayList;

public class JsonFind {
    public static void main(String[] args) throws Exception {
    }

    static void dwo(JSONObject o, IRun_2<String, Object> exec) throws Exception {
        dwo(o, exec, null);
    }

    static void dwo(Object o, IRun_2<String, Object> exec, String key) throws Exception {
        if (o instanceof JSONArray)
            for (int i = 0; i < ((JSONArray) o).length(); i++)
                dwo(((JSONArray) o).get(i), exec, null);
        else if (o instanceof JSONObject)
            for (String key_t : ((JSONObject) o).keySet())
                dwo(((JSONObject) o).get(key_t), exec, key_t);
        else if (key != null && exec != null)
            exec.run(key, o);
    }
}
