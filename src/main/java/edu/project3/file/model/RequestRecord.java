package edu.project3.file.model;

import java.time.OffsetDateTime;

public record RequestRecord(
    String ip,
    OffsetDateTime dateTime,
    String request,
    int httpCode,
    long size,
    String agent
) {
}
