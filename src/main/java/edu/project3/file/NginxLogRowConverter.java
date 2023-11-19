package edu.project3.file;

import edu.project3.file.model.RequestRecord;
import java.time.OffsetDateTime;
import java.time.format.DateTimeFormatter;
import java.util.Locale;
import java.util.Optional;
import java.util.regex.Matcher;
import java.util.regex.Pattern;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

public class NginxLogRowConverter implements Converter {
    private static final Logger LOGGER = LogManager.getLogger();
    private static final DateTimeFormatter DATE_TIME_FORMATTER =
        DateTimeFormatter.ofPattern("dd/MMM/yyyy:HH:mm:ss Z", Locale.ENGLISH);
    private static final Pattern REQUEST_LOG = Pattern.compile(
        "([^ ]*) ([^ ]*) ([^ ]*) \\[([^\\]]*)\\] \"([^\"]*)\" ([^ ]*) ([^ ]*) \"([^\"]*)\" \"([^\"]*)\""
    );
    private static final int IP_GROUP = 1;
    private static final int DATETIME_GROUP = 4;
    private static final int RESOURCE_GROUP = 5;
    private static final int HTTP_CODE_GROUP = 6;
    private static final int SIZE_GROUP = 7;
    private static final int AGENT_GROUP = 9;

    @Override
    public Optional<RequestRecord> convert(String row) {
        Matcher matcher = REQUEST_LOG.matcher(row);

        if (matcher.find()) {
            return Optional.of(
                new RequestRecord(
                    matcher.group(IP_GROUP),
                    OffsetDateTime.parse(matcher.group(DATETIME_GROUP), DATE_TIME_FORMATTER),
                    matcher.group(RESOURCE_GROUP),
                    Integer.parseInt(matcher.group(HTTP_CODE_GROUP)),
                    Long.parseLong(matcher.group(SIZE_GROUP)),
                    matcher.group(AGENT_GROUP)
                )
            );
        } else {
            LOGGER.warn("Failed parse row '%s'".formatted(row));
            return Optional.empty();
        }
    }
}
