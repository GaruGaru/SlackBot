package bot.commands.statsd;

import com.ullink.slack.simpleslackapi.SlackSession;
import com.ullink.slack.simpleslackapi.events.SlackMessagePosted;

/**
 * Created by fabiocollini on 24/03/17.
 */
public class JenkinsCounter extends BaseStatsDCommand {

    @Override
    public boolean filter(SlackMessagePosted message) {
        return message.getChannel().getName().equals("dev-jenkins");
    }

    @Override
    public void handle(SlackSession session, SlackMessagePosted message) {
        String body = message.getAttachments().isEmpty() ? "" : message.getAttachments().get(0).getText();
        getStatsd().increment("message");
    }
}
