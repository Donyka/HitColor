package eu.donyka.listener;

import java.util.ArrayList;
import java.util.List;

public interface OverlayReloadListener {
    List<OverlayReloadListener> LISTENERS = new ArrayList<>();

    void hitcolor$reloadOverlay();

    static void register(OverlayReloadListener listener) {
        LISTENERS.add(listener);
    }

    static void callEvent() {
        for (OverlayReloadListener listener : LISTENERS) {
            listener.hitcolor$reloadOverlay();
        }
    }
}

