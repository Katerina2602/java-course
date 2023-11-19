package edu.project3.cli;

import edu.project3.stat.model.Stat;
import edu.project3.stat.model.StatFormat;
import java.util.List;

public interface Renderer {
    String render(List<Stat> stats, StatFormat format);
}
