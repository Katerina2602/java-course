package edu.project3.stat;

import edu.project3.stat.model.AgentStat;
import edu.project3.stat.model.CommonStat;
import edu.project3.stat.model.HttpCodeStat;
import edu.project3.stat.model.IpStat;
import edu.project3.stat.model.RequestedResourceStat;
import edu.project3.stat.model.Stat;
import java.util.List;
import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.assertTrue;

class FullStatProviderTest {
    private final StatProvider statProvider = new FullStatProvider();

    @Test
    void willReturnAllExpectedStats() {
        List<Class<? extends Stat>> expectedStats = List.of(
            CommonStat.class,
            RequestedResourceStat.class,
            HttpCodeStat.class,
            IpStat.class,
            AgentStat.class
        );

        List<? extends Class<? extends Stat>>
            actualStats = statProvider.getStats().stream().map(stat -> stat.getClass()).toList();

        assertTrue(actualStats.containsAll(expectedStats));
    }
}
