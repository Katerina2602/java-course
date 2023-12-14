package edu.project3.stat;

import edu.project3.stat.model.Stat;
import java.time.LocalDate;
import java.util.List;

public interface StatsCollector {
    List<Stat> collect(
        String path,
        LocalDate from,
        LocalDate to
    );
}
