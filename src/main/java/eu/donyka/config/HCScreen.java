package eu.donyka.config;

import me.shedaniel.autoconfig.AutoConfig;
import me.shedaniel.autoconfig.AutoConfigClient;
import me.shedaniel.autoconfig.serializer.GsonConfigSerializer;
import net.minecraft.client.gui.screens.Screen;

public final class HCScreen {
    private HCScreen() {
    }

    public static void init() {
        AutoConfig.register(HConfig.class, GsonConfigSerializer::new);
    }

    public static HConfig getConfig() {
        return AutoConfig.getConfigHolder(HConfig.class).getConfig();
    }

    public static Screen getScreen(Screen parent) {
        return AutoConfigClient.getConfigScreen(HConfig.class, parent).get();
    }
}

