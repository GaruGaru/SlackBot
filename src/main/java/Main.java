import bot.SlackBot;
import bot.commands.EchoCommand;
import configuration.SlackBotProperties;

import java.io.IOException;


/**
 * Created by Garu on 22/03/2017.
 */
public class Main {

    private static final String TOKEN = "xoxp-2281819148-35509433602-158376412853-1533ff43316d9f78df946af663520579";

    public static void main(String[] args) throws IOException {

        SlackBot slackBot = SlackBot.Builder.create()
                .configure(SlackBotProperties.create())
                .command(new EchoCommand())
                .build();

        slackBot.connect();
    }
}
