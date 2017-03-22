package bot.commands;

import com.ullink.slack.simpleslackapi.SlackSession;
import com.ullink.slack.simpleslackapi.events.SlackMessagePosted;

/**
 * Created by Garu on 22/03/2017.
 */

public class EchoCommand implements SlackCommand {

    @Override
    public boolean filter(SlackMessagePosted message) {
        return message.getMessageContent().startsWith("!echo ");
    }

    @Override
    public void handle(SlackSession session, SlackMessagePosted message) {
        String echo = message.getMessageContent().replace("!echo ", "");
        session.sendMessage(message.getChannel(), echo);
    }
}
