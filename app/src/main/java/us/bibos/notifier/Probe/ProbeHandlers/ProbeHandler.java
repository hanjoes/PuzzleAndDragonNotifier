package us.bibos.notifier.Probe.ProbeHandlers;

import us.bibos.notifier.Probe.ProbeData.ProbeData;

public interface ProbeHandler {
    void handle(ProbeData data);
}
