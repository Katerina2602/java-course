package edu.project3.file;

import edu.project3.file.model.RequestRecord;
import java.time.OffsetDateTime;
import java.time.ZoneOffset;
import java.util.Optional;
import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;

class NginxLogRowConverterTest {
    private final Converter converter = new NginxLogRowConverter();

    @Test
    void willSuccessConvertRowToModel() {
        String row =
            "93.180.71.3 - - [16/May/2015:08:05:32 +0000] \"GET /downloads/product_1 HTTP/1.1\" 304 0 \"-\" \"Debian APT-HTTP/1.3 (0.8.16~exp12ubuntu10.21)\"";

        Optional<RequestRecord> record = converter.convert(row);

        assertTrue(record.isPresent());
        assertEquals("93.180.71.3", record.get().ip());
        assertEquals(OffsetDateTime.of(2015, 5, 16, 8, 5, 32, 0, ZoneOffset.ofHours(0)), record.get().dateTime());
        assertEquals("GET /downloads/product_1 HTTP/1.1", record.get().request());
        assertEquals(304, record.get().httpCode());
        assertEquals(0L, record.get().size());
        assertEquals("Debian APT-HTTP/1.3 (0.8.16~exp12ubuntu10.21)", record.get().agent());
    }

    @Test
    void willReturnEmptyOptionalWhenFormatLogIsWrong() {
        String row = "1.1.1.1 - 2023.10.12 200 GET /logs";

        Optional<RequestRecord> record = converter.convert(row);

        assertTrue(record.isEmpty());
    }
}
