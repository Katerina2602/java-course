package edu.project3.file;

import java.nio.file.Path;
import java.util.List;

public interface FileFinder {
    List<Path> find(String pattern);
}
