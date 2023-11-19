package edu.project3.file;

import edu.project3.file.model.RequestRecord;
import java.util.Optional;

public interface Converter {
    Optional<RequestRecord> convert(String row);
}
