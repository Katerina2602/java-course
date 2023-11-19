package edu.project3.stat.model;

import edu.project3.file.model.RequestRecord;
import java.time.LocalDate;
import java.util.HashMap;
import java.util.Map;
import java.util.stream.Collectors;

public class AgentStat implements Stat {
    private static final String TITLE = "Используемые агенты";
    private static final String MARKDOWN_FORMAT = "| %s | %s |";
    private static final String ADOC_FORMAT = "| %s | %s";
    private final Map<String, Integer> agents = new HashMap<>();

    @Override
    public void collect(RequestRecord requestRecord) {
        agents.merge(requestRecord.agent(), 1, Integer::sum);
    }

    @Override
    public String renderAsMarkdown() {
        return """
            ### %s

            | Агент | Количество |
            |:-------:|:---------|
            %s
            """
            .formatted(
                TITLE,
                formattingAgents(MARKDOWN_FORMAT)
            );
    }

    @Override
    public String renderAsAdoc() {
        return """
            === %s
            [options="header"]
            |====================
            | Агент | Количество
            %s
            |=======================
            """
            .formatted(
                TITLE,
                formattingAgents(ADOC_FORMAT)
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

    public Map<String, Integer> getAgents() {
        return agents;
    }

    private String formattingAgents(String format) {
        if (agents.isEmpty()) {
            return "";
        }

        return agents.entrySet()
            .stream()
            .sorted((o1, o2) -> o2.getValue().compareTo(o1.getValue()))
            .map(entry -> format.formatted(entry.getKey(), entry.getValue()))
            .collect(Collectors.joining(System.lineSeparator()));
    }
}
