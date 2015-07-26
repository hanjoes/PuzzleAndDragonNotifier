package us.bibos.notifier.Probe;

import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;

import us.bibos.notifier.Probe.ProbeData.ProbeData;
import us.bibos.notifier.Probe.ProbeHandlers.ProbeHandler;

public class PNDWikiProbe implements Probe {

    public static final String PND_WIKI_CH_TIMETABLE = "daily_timetable";

    public static class NotificationProbeData extends ProbeData {
        public Element timeTable;

        public NotificationProbeData(Element timeTable) {
            this.timeTable = timeTable;
        }
    }

    @Override
    public void probAndExecute(Document doc, ProbeHandler handler) {
        Element timeTableElement = doc.getElementById(PND_WIKI_CH_TIMETABLE);
        ProbeData data = new NotificationProbeData(timeTableElement);
        handler.handle(data);
    }
}
