package bot.commands;

import bot.commands.core.SlackCommandBase;
import com.ullink.slack.simpleslackapi.SlackSession;
import com.ullink.slack.simpleslackapi.events.SlackMessagePosted;

/**
 * Created by Garu on 22/03/2017.
 */

public class EchoCommand extends SlackCommandBase {

    public static final String ECHO = "!echo ";

    @Override
    public boolean filter(SlackMessagePosted message) {
        return message.getMessageContent().startsWith("!echo ");
    }

    @Override
    public void handle(SlackSession session, SlackMessagePosted message) {
        String echo = message.getMessageContent().replace(ECHO, "");
        answer(session, message, echo);
    }
}
