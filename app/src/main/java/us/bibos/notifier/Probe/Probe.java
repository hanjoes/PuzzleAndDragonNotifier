package us.bibos.notifier.Probe;

import org.jsoup.nodes.Document;

import us.bibos.notifier.Probe.ProbeHandlers.ProbeHandler;

public interface Probe {
    void probAndExecute(Document doc, ProbeHandler handler);
}
