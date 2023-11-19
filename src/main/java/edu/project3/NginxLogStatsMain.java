package edu.project3;

import edu.project3.cli.CLIArgumentParser;
import edu.project3.cli.ConsoleRenderer;
import edu.project3.cli.Renderer;
import edu.project3.cli.model.Options;
import edu.project3.file.FileFinder;
import edu.project3.file.FileFinderImpl;
import edu.project3.file.NginxLogRowConverter;
import edu.project3.stat.FullStatProvider;
import edu.project3.stat.StatProvider;
import edu.project3.stat.StatsCollector;
import edu.project3.stat.StatsCollectorImpl;
import edu.project3.stat.model.Stat;
import edu.project3.stat.model.StatFormat;
import java.net.http.HttpClient;
import java.time.LocalDate;
import java.util.List;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

public class NginxLogStatsMain {
    private static final Logger LOGGER = LogManager.getLogger();

    private NginxLogStatsMain() {
    }

    public static void main(String[] args) {
        Options options = CLIArgumentParser.parse(args);

        HttpClient httpClient = HttpClient.newHttpClient();
        FileFinder fileFinder = new FileFinderImpl(httpClient);
        StatProvider statProvider = new FullStatProvider();
        NginxLogRowConverter converter = new NginxLogRowConverter();
        Renderer renderer = new ConsoleRenderer();

        StatsCollector statsCollector = new StatsCollectorImpl(fileFinder, statProvider, converter);

        List<Stat> collectedStats = statsCollector.collect(
            options.getPath(),
            options.getFrom().orElse(LocalDate.MIN),
            options.getTo().orElse(LocalDate.MAX)
        );

        LOGGER.info(
            renderer.render(
                collectedStats,
                options.getFormat().orElse(StatFormat.MARKDOWN)
            )
        );
    }
}
