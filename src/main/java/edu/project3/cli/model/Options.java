package edu.project3.cli.model;

import edu.project3.stat.model.StatFormat;
import java.time.LocalDate;
import java.util.Optional;

public class Options {
    private String path;
    private Optional<LocalDate> from = Optional.empty();
    private Optional<LocalDate> to = Optional.empty();
    private Optional<StatFormat> format = Optional.empty();

    public String getPath() {
        return path;
    }

    public void setPath(String path) {
        this.path = path;
    }

    public Optional<LocalDate> getFrom() {
        return from;
    }

    public void setFrom(Optional<LocalDate> from) {
        this.from = from;
    }

    public Optional<LocalDate> getTo() {
        return to;
    }

    public void setTo(Optional<LocalDate> to) {
        this.to = to;
    }

    public Optional<StatFormat> getFormat() {
        return format;
    }

    public void setFormat(Optional<StatFormat> format) {
        this.format = format;
    }
}
