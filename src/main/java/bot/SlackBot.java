package bot;

import bot.commands.SlackCommand;
import com.ullink.slack.simpleslackapi.SlackSession;
import com.ullink.slack.simpleslackapi.events.SlackMessagePosted;
import com.ullink.slack.simpleslackapi.impl.SlackSessionFactory;
import com.ullink.slack.simpleslackapi.listeners.SlackMessagePostedListener;
import configuration.SlackBotConfiguration;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

/**
 * Created by Garu on 22/03/2017.
 */
public class SlackBot implements SlackMessagePostedListener {

    private SlackBotConfiguration configuration;

    private List<SlackCommand> commands;

    public SlackBot(SlackBotConfiguration configuration, List<SlackCommand> commands) {
        this.commands = commands;
        this.configuration = configuration;
    }

    public void connect() {
        try {
            SlackSession session = SlackSessionFactory.createWebSocketSlackSession(configuration.getToken());
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

        private SlackBotConfiguration configuration;
        private List<SlackCommand> commands;

        public Builder() {
            this.commands = new ArrayList<>();
        }

        public Builder configure(SlackBotConfiguration configuration) {
            this.configuration = configuration;
            return this;
        }

        public Builder command(SlackCommand command) {
            this.commands.add(command);
            return this;
        }

        public SlackBot build() {
            return new SlackBot(configuration, commands);
        }
    }
}
