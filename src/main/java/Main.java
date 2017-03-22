import bot.SlackBot;
import bot.commands.EchoCommand;

import java.io.IOException;


/**
 * Created by Garu on 22/03/2017.
 */
public class Main {

    private static final String TOKEN = "<token>";

    public static void main(String[] args) throws IOException {

        SlackBot slackBot = SlackBot.Builder.create()
                .token(TOKEN)
                .command(new EchoCommand())
                .build();

        slackBot.connect();

    }
}
