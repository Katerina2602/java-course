package edu.project3.stat;

import edu.project3.file.Converter;
import edu.project3.file.FileFinder;
import edu.project3.file.NginxLogRowConverter;
import edu.project3.stat.model.AgentStat;
import edu.project3.stat.model.CommonStat;
import edu.project3.stat.model.HttpCodeStat;
import edu.project3.stat.model.IpStat;
import edu.project3.stat.model.RequestedResourceStat;
import edu.project3.stat.model.Stat;
import java.nio.file.Path;
import java.time.LocalDate;
import java.util.Collections;
import java.util.List;
import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;

class StatsCollectorImplTest {
    private final FileFinder fileFinder = mock(FileFinder.class);
    private final StatProvider statProvider = new FullStatProvider();
    private final Converter converter = new NginxLogRowConverter();
    private final StatsCollector collector = new StatsCollectorImpl(
        fileFinder,
        statProvider,
        converter
    );

    @Test
    void willThrowExceptionWhenFilesNOtFound() {
        when(fileFinder.find(any())).thenReturn(Collections.emptyList());

        assertThrows(IllegalArgumentException.class, () -> collector.collect("path", LocalDate.now(), LocalDate.now()));
    }

    @Test
    void willSuccessCollectStatsFromTestFile() {
        String path = "nging-log-for-test.txt";

        when(fileFinder.find(path))
            .thenReturn(List.of(getTestFile("project3/nging-log-for-test.txt")));

        List<Stat> stats = collector.collect(path, LocalDate.of(2015, 5, 17), LocalDate.of(2015, 5, 18));

        assertStat(stats);
    }

    private void assertStat(List<Stat> stats) {
        stats.forEach(stat -> {
            if (stat instanceof CommonStat) {
                CommonStat commonStat = (CommonStat) stat;
                assertEquals(3, commonStat.getTotalRequestCount());
                assertEquals(12L, commonStat.getTotalSize());
                assertEquals(4.0, commonStat.getAverageSize());
            } else if (stat instanceof RequestedResourceStat) {
                RequestedResourceStat resourceStat = (RequestedResourceStat) stat;
                assertEquals(2, resourceStat.getRequestedResources().size());
                assertEquals(2, resourceStat.getRequestedResources().get("/downloads/product_2"));
                assertEquals(1, resourceStat.getRequestedResources().get("/downloads/product_4"));
            } else if (stat instanceof HttpCodeStat) {
                HttpCodeStat httpCodeStat = (HttpCodeStat) stat;
                assertEquals(2, httpCodeStat.getHttpCodes().size());
                assertEquals(1, httpCodeStat.getHttpCodes().get(304));
                assertEquals(2, httpCodeStat.getHttpCodes().get(200));
            } else if (stat instanceof IpStat) {
                IpStat ipStat = (IpStat) stat;
                assertEquals(3, ipStat.getIps().size());
                assertEquals(1, ipStat.getIps().get("93.180.71.3"));
                assertEquals(1, ipStat.getIps().get("80.91.33.133"));
                assertEquals(1, ipStat.getIps().get("80.91.33.135"));
            } else if (stat instanceof AgentStat) {
                AgentStat agentStat = (AgentStat) stat;
                assertEquals(1, agentStat.getAgents().size());
                assertEquals(3, agentStat.getAgents().get("Debian APT-HTTP/1.3 (0.8.16~exp12ubuntu10.17)"));
            }
        });
    }

    private Path getTestFile(String filename) {
        return Path.of(getClass().getClassLoader().getResource(filename).getPath());
    }
}
