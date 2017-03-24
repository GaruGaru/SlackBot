package configuration;

/**
 * Created by fabiocollini on 23/03/17.
 */
public class SlackBotProperties implements SlackBotConfiguration {

    public static SlackBotProperties create() {
        return new SlackBotProperties();
    }

    private Configuration configuration;

    private SlackBotProperties() {
        this.configuration = Configuration.fromProperties("slack");
    }

    @Override
    public String getToken() {
        return configuration.get("token");
    }

}
