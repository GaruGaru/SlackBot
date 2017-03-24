package bot.commands.core;

import bot.commands.SlackCommand;
import com.ullink.slack.simpleslackapi.SlackPreparedMessage;
import com.ullink.slack.simpleslackapi.SlackSession;
import com.ullink.slack.simpleslackapi.events.SlackMessagePosted;

import java.util.logging.Logger;

/**
 * Created by fabiocollini on 24/03/17.
 */
public abstract class SlackCommandBase implements SlackCommand {

    private Logger logger = Logger.getLogger("SlackCommand");

    public void answer(SlackSession session, SlackMessagePosted message, SlackPreparedMessage response) {
        session.sendMessage(message.getChannel(), response);
    }

    public void answer(SlackSession session, SlackMessagePosted message, String response) {
        session.sendMessage(message.getChannel(), new SlackPreparedMessage.Builder().withMessage(response).build());
    }

    public void log(String message){
        this.logger.info(message);
    }


}
