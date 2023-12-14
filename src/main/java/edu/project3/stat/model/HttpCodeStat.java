package edu.project3.stat.model;

import edu.project3.file.model.RequestRecord;
import java.time.LocalDate;
import java.util.HashMap;
import java.util.Map;
import java.util.stream.Collectors;

public class HttpCodeStat implements Stat {
    private static final String TITLE = "Коды ответов";
    private static final String MARKDOWN_FORMAT = "| %s | %s | %s |";
    private static final String ADOC_FORMAT = "| %s | %s | %s";
    private final Map<Integer, Integer> httpCodes = new HashMap<>();

    @Override
    public void collect(RequestRecord requestRecord) {
        httpCodes.merge(requestRecord.httpCode(), 1, Integer::sum);
    }

    @Override
    public String renderAsMarkdown() {
        return """
            ### %s
            | Код | Имя | Количество |
            |:-------:|:---------:|:---------|
            %s
            """
            .formatted(
                TITLE,
                formattingHttpCodes(MARKDOWN_FORMAT)
            );
    }

    @Override
    public String renderAsAdoc() {
        return """
            === %s
            [options="header"]
            |====================
            | Код | Имя | Количество
            %s
            |=======================
            """
            .formatted(
                TITLE,
                formattingHttpCodes(ADOC_FORMAT)
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

    public Map<Integer, Integer> getHttpCodes() {
        return httpCodes;
    }

    private String formattingHttpCodes(String format) {
        if (httpCodes.isEmpty()) {
            return "";
        }

        return httpCodes.entrySet()
            .stream()
            .sorted((o1, o2) -> o2.getValue().compareTo(o1.getValue()))
            .map(entry -> format.formatted(
                    entry.getKey(),
                    HttpCode.getByValue(entry.getKey()).getDescription(),
                    entry.getValue()
                )
            )
            .collect(Collectors.joining(System.lineSeparator()));
    }
}
