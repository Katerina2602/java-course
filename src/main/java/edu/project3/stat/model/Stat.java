package edu.project3.stat.model;

import edu.project3.file.model.RequestRecord;
import java.time.LocalDate;

public interface Stat {
    void collect(RequestRecord requestRecord);

    String renderAsMarkdown();

    String renderAsAdoc();

    void addFilename(String filename);

    void addStartAndEndDates(LocalDate from, LocalDate to);
}
