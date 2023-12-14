package edu.project3.stat.model;

import edu.project3.file.model.RequestRecord;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

public class CommonStat implements Stat {
    private static final String TITLE = "Общая информация";
    private final List<String> fileNames = new ArrayList<>();

    private int totalRequestCount = 0;
    private long totalSize = 0;
    private LocalDate from;
    private LocalDate to;

    @Override
    public void collect(RequestRecord requestRecord) {
        totalRequestCount++;
        totalSize += requestRecord.size();
    }

    @Override
    public String renderAsMarkdown() {
        return """
            ### %s

            | Метрика | Значение |
            |:-------:|:---------|
            | Файлы | %s |
            | Начальная дата | %s |
            | Конечная дата | %s |
            | Количество запросов | %s |
            | Средний размер ответа | %sb |
            """
            .formatted(
                TITLE,
                String.join(",", fileNames),
                formatDateTime(from),
                formatDateTime(to),
                totalRequestCount,
                getAverageSize()
            );
    }

    @Override
    public String renderAsAdoc() {
        return """
            === %s
            [options="header"]
            |====================
            | Метрика | Значение
            | Файлы | %s
            | Начальная дата | %s
            | Конечная дата | %s
            | Количество запросов | %s
            | Средний размер ответа | %sb
            |=======================
            """
            .formatted(
                TITLE,
                String.join(",", fileNames),
                formatDateTime(from),
                formatDateTime(to),
                totalRequestCount,
                getAverageSize()
            );
    }

    @Override
    public void addFilename(String filename) {
        fileNames.add(filename);
    }

    @Override
    public void addStartAndEndDates(LocalDate from, LocalDate to) {
        this.from = from;
        this.to = to;
    }

    public int getTotalRequestCount() {
        return totalRequestCount;
    }

    public long getTotalSize() {
        return totalSize;
    }

    public double getAverageSize() {
        return (double) totalSize / totalRequestCount;
    }

    private String formatDateTime(LocalDate dateTime) {
        if (dateTime.equals(LocalDate.MIN) || dateTime.equals(LocalDate.MAX)) {
            return "-";
        } else {
            return dateTime.toString();
        }
    }
}
