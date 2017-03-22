package bot.commands;

import com.ullink.slack.simpleslackapi.SlackSession;
import com.ullink.slack.simpleslackapi.events.SlackMessagePosted;

/**
 * Created by Garu on 22/03/2017.
 */
public interface SlackCommand {

    boolean filter(SlackMessagePosted message);

    void handle(SlackSession session, SlackMessagePosted message);

}
