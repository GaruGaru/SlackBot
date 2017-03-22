package bot;

import bot.commands.SlackCommand;
import com.ullink.slack.simpleslackapi.SlackSession;
import com.ullink.slack.simpleslackapi.events.SlackMessagePosted;
import com.ullink.slack.simpleslackapi.impl.SlackSessionFactory;
import com.ullink.slack.simpleslackapi.listeners.SlackMessagePostedListener;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

/**
 * Created by Garu on 22/03/2017.
 */
public class SlackBot implements SlackMessagePostedListener {

    private List<SlackCommand> commands;

    private final String token;

    public SlackBot(String token, List<SlackCommand> commands) {
        this.token = token;
        this.commands = commands;
    }

    public void connect() {
        try {
            SlackSession session = SlackSessionFactory.createWebSocketSlackSession(token);
            session.connect();
            session.addMessagePostedListener(this);
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }

    @Override
    public void onEvent(SlackMessagePosted message, SlackSession session) {
        commands.stream()
                .filter(c -> c.filter(message))
                .forEach(c -> c.handle(session, message));
    }

    public static class Builder {
        public static Builder create() {
            return new Builder();
        }

        private String token;
        private List<SlackCommand> commands;

        public Builder() {
            this.token = "";
            this.commands = new ArrayList<>();
        }

        public Builder token(String token) {
            this.token = token;
            return this;
        }

        public Builder command(SlackCommand command) {
            this.commands.add(command);
            return this;
        }

        public SlackBot build() {
            return new SlackBot(token, commands);
        }
    }
}
