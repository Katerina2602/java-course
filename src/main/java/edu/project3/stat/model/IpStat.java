package edu.project3.stat.model;

import edu.project3.file.model.RequestRecord;
import java.time.LocalDate;
import java.util.HashMap;
import java.util.Map;
import java.util.stream.Collectors;

public class IpStat implements Stat {
    private static final String TITLE = "IP адреса";
    private static final String MARKDOWN_FORMAT = "| %s | %s |";
    private static final String ADOC_FORMAT = "| %s | %s";
    private final Map<String, Integer> ips = new HashMap<>();

    @Override
    public void collect(RequestRecord requestRecord) {
        ips.merge(requestRecord.ip(), 1, Integer::sum);
    }

    @Override
    public String renderAsMarkdown() {
        return """
            ### %s

            | IP | Количество запросов |
            |:-------:|:---------|
            %s
            """
            .formatted(
                TITLE,
                formattingIps(MARKDOWN_FORMAT)
            );
    }

    @Override
    public String renderAsAdoc() {
        return """
            === %s
            [options="header"]
            |====================
            | IP | Количество запросов
            %s
            |=======================
            """
            .formatted(
                TITLE,
                formattingIps(ADOC_FORMAT)
            );
    }

    @Override
    public void addFilename(String filename) {
        // unsupported
    }

    @Override
    public void addStartAndEndDates(LocalDate from, LocalDate to) {
        // unsupported
    }

    public Map<String, Integer> getIps() {
        return ips;
    }

    private String formattingIps(String format) {
        if (ips.isEmpty()) {
            return "";
        }

        return ips.entrySet()
            .stream()
            .sorted((o1, o2) -> o2.getValue().compareTo(o1.getValue()))
            .map(entry -> format.formatted(entry.getKey(), entry.getValue()))
            .collect(Collectors.joining(System.lineSeparator()));
    }
}
