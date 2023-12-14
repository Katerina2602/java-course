package edu.project3.stat;

import edu.project3.file.Converter;
import edu.project3.file.FileFinder;
import edu.project3.stat.model.Stat;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.time.LocalDate;
import java.util.List;
import java.util.Optional;
import java.util.stream.Stream;

public class StatsCollectorImpl implements StatsCollector {
    private final FileFinder fileFinder;
    private final StatProvider statProvider;
    private final Converter converter;

    public StatsCollectorImpl(
        FileFinder fileFinder,
        StatProvider statProvider,
        Converter converter
    ) {
        this.fileFinder = fileFinder;
        this.statProvider = statProvider;
        this.converter = converter;
    }

    @Override
    public List<Stat> collect(
        String path,
        LocalDate from,
        LocalDate to
    ) {
        List<Path> files = fileFinder.find(path);
        List<Stat> stats = statProvider.getStats();

        if (files.isEmpty()) {
            throw new IllegalArgumentException("Not found files for path - " + path);
        }

        files.forEach(file -> handleFile(stats, file, from, to));

        return stats;
    }

    private void handleFile(List<Stat> stats, Path path, LocalDate from, LocalDate to) {
        stats.forEach(stat -> {
            stat.addFilename(path.getFileName().toString());
            stat.addStartAndEndDates(from, to);
        });

        try (Stream<String> stream = Files.lines(path)) {
            stream
                .map(converter::convert)
                .filter(Optional::isPresent)
                .map(Optional::get)
                .filter(requestRecord -> dateInRange(requestRecord.dateTime().toLocalDate(), from, to))
                .forEach(requestRecord -> stats.forEach(stat -> stat.collect(requestRecord)));
        } catch (IOException e) {
            throw new RuntimeException("Failed to handle file - " + path.toAbsolutePath());
        }
    }

    private boolean dateInRange(LocalDate currentDate, LocalDate from, LocalDate to) {
        return !(currentDate.isBefore(from) || currentDate.isAfter(to));
    }
}
