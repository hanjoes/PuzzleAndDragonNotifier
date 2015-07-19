package us.bibos.puzzleanddragonnotifier.Probe;

import org.jsoup.nodes.Document;

import us.bibos.puzzleanddragonnotifier.Probe.ProbeHandlers.ProbeHandler;

public interface Probe {
    void probAndExecute(Document doc, ProbeHandler handler);
}
