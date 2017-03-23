package configuration;

/**
 * Created by fabiocollini on 23/03/17.
 */
public class SlackBotStaticConfiguration implements SlackBotConfiguration {

    public static SlackBotStaticConfiguration create() {
        return new SlackBotStaticConfiguration();
    }

    private SlackBotStaticConfiguration() {
    }

    private String token;

    public SlackBotStaticConfiguration token(String token) {
        this.token = token;
        return this;
    }

    @Override
    public String getToken() {
        return token;
    }
}
