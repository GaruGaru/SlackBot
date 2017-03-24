package configuration;

import java.io.FileInputStream;
import java.io.IOException;
import java.util.HashMap;
import java.util.Map;
import java.util.Properties;

/**
 * Created by fabiocollini on 24/03/17.
 */
public class Configuration {

    public static Configuration fromMap(Map<String, String> map) {
        return new Configuration(map);
    }

    public static Configuration empty() {
        return Configuration.fromMap(new HashMap<>());
    }

    public static Configuration fromProperties(String name) {
        name = name.endsWith(".properties") ? name : name + ".properties";
        try {
            Properties properties = new Properties();
            properties.load(new FileInputStream(name));
            Map<String, String> propertiesMap = new HashMap<>();
            properties.forEach((k, v) -> propertiesMap.put(k.toString(), v.toString()));
            return Configuration.fromMap(propertiesMap);
        } catch (IOException ex) {
            throw new RuntimeException("Configuration not found: " + name);
        }
    }

    private Map<String, String> map;

    private Configuration(Map<String, String> map) {
        this.map = map;
    }

    public String get(String key) {
        if (!map.containsKey(key))
            throw new RuntimeException("Configuration key not found: " + key);
        return map.get(key);
    }

    public String getOrDefault(String key, String defaultValue) {
        return map.getOrDefault(key, defaultValue);
    }

    public int getInt(String key) {
        try {
            return Integer.valueOf(get(key));
        } catch (NumberFormatException ex) {
            throw new RuntimeException("Not int: " + key);
        }
    }

    public int getIntOrDefault(String key, int defaultValue) {
        return map.containsKey(key) ? getInt(key) : defaultValue;
    }

    public void put(String key, String value) {
        this.map.put(key, value);
    }

    public void putIfAbsent(String key, String value) {
        this.map.putIfAbsent(key, value);
    }

    public boolean hasKey(String key) {
        return this.map.containsKey(key);
    }


}
