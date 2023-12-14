package edu.project3.stat.model;

import edu.project3.file.model.RequestRecord;
import java.time.LocalDate;
import java.util.HashMap;
import java.util.Map;
import java.util.stream.Collectors;

public class RequestedResourceStat implements Stat {
    private static final String TITLE = "Запрашиваемые ресурсы";
    private static final String MARKDOWN_FORMAT = "| '%s' | %s |";
    private static final String ADOC_FORMAT = "| \\'%s' | %s";
    private final Map<String, Integer> requestedResources = new HashMap<>();

    @Override
    public void collect(RequestRecord requestRecord) {
        requestedResources.merge(parseResource(requestRecord.request()), 1, Integer::sum);
    }

    @Override
    public String renderAsMarkdown() {
        return """
            ### %s

            | Ресурс | Количество |
            |:-------:|:---------|
            %s
            """
            .formatted(
                TITLE,
                formattingRequestedResources(MARKDOWN_FORMAT)
            );
    }

    @Override
    public String renderAsAdoc() {
        return """
            === %s
            [options="header"]
            |====================
            | Ресурс | Количество
            %s
            |=======================
            """
            .formatted(
                TITLE,
                formattingRequestedResources(ADOC_FORMAT)
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

    public Map<String, Integer> getRequestedResources() {
        return requestedResources;
    }

    private String formattingRequestedResources(String format) {
        if (requestedResources.isEmpty()) {
            return "";
        }

        return requestedResources.entrySet()
            .stream()
            .sorted((o1, o2) -> o2.getValue().compareTo(o1.getValue()))
            .map(entry -> format.formatted(entry.getKey(), entry.getValue()))
            .collect(Collectors.joining(System.lineSeparator()));
    }

    private String parseResource(String request) {
        return request.substring(request.indexOf(" ") + 1, request.lastIndexOf(" "));
    }
}
