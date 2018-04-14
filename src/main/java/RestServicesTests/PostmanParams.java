package RestServicesTests;

import com.google.gson.Gson;

import java.util.LinkedHashMap;
import java.util.Map;

public class PostmanParams {
    public Map<String, String> data;
    public String storage;

    public PostmanParams() {
        data = new LinkedHashMap<String, String>();
    }
    public String toJsonString() {
        Gson gson = new Gson();
        return gson.toJson(this);
    }
}
