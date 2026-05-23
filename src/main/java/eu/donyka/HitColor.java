package eu.donyka;

import eu.donyka.config.HCScreen;
import eu.donyka.listener.OverlayReloadListener;
import net.fabricmc.api.ClientModInitializer;
import net.fabricmc.fabric.api.client.event.lifecycle.v1.ClientTickEvents;

public final class HitColor implements ClientModInitializer {
    @Override
    public void onInitializeClient() {
        HCScreen.init();
        ClientTickEvents.END_CLIENT_TICK.register(client -> OverlayReloadListener.callEvent());
    }
}

