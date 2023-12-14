package edu.project3.cli;

import edu.project3.stat.model.Stat;
import edu.project3.stat.model.StatFormat;
import java.util.List;
import java.util.stream.Collectors;

public class ConsoleRenderer implements Renderer {
    @Override
    public String render(List<Stat> stats, StatFormat format) {
        return switch (format) {
            case MARKDOWN -> stats.stream()
                .map(Stat::renderAsMarkdown)
                .collect(Collectors.joining());
            case ADOC -> stats.stream()
                .map(Stat::renderAsAdoc)
                .collect(Collectors.joining());
            default -> throw new UnsupportedOperationException();
        };
    }
}
