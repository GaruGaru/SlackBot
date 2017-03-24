package bot.commands.statsd;

import bot.commands.core.SlackCommandBase;
import com.timgroup.statsd.NonBlockingStatsDClient;
import com.timgroup.statsd.StatsDClient;
import configuration.Configuration;

/**
 * Created by fabiocollini on 24/03/17.
 */
public abstract class BaseStatsDCommand extends SlackCommandBase {

    private StatsDClient statsd;

    public BaseStatsDCommand() {
        Configuration configuration = Configuration.fromProperties("statsd");
        final String host = configuration.get("host");
        final String prefix = configuration.get("prefix");
        final int port = configuration.getIntOrDefault("port", 8125);
        this.statsd = new NonBlockingStatsDClient(prefix, host, port);
    }

    public StatsDClient getStatsd() {
        return statsd;
    }

}
