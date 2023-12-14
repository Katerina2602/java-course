package edu.project3.stat;

import edu.project3.stat.model.AgentStat;
import edu.project3.stat.model.CommonStat;
import edu.project3.stat.model.HttpCodeStat;
import edu.project3.stat.model.IpStat;
import edu.project3.stat.model.RequestedResourceStat;
import edu.project3.stat.model.Stat;
import java.util.List;

public class FullStatProvider implements StatProvider {
    @Override
    public List<Stat> getStats() {
        return List.of(
            new CommonStat(),
            new RequestedResourceStat(),
            new HttpCodeStat(),
            new IpStat(),
            new AgentStat()
        );
    }
}
