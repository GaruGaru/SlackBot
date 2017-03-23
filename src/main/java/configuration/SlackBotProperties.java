package configuration;

import java.io.FileInputStream;
import java.io.IOException;
import java.util.Properties;

/**
 * Created by fabiocollini on 23/03/17.
 */
public class SlackBotProperties implements SlackBotConfiguration {

    public static SlackBotProperties create() {
        return new SlackBotProperties();
    }

    private Properties properties;

    private SlackBotProperties() {
        this.properties = new Properties();
        try {
            this.properties.load(new FileInputStream("slack.properties"));
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }

    @Override
    public String getToken() {
        return properties.getProperty("token");
    }

}
