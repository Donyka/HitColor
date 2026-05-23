package eu.donyka.config;

import me.shedaniel.autoconfig.AutoConfig;
import me.shedaniel.autoconfig.serializer.GsonConfigSerializer;
import net.minecraft.client.gui.screen.Screen;

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
        return AutoConfig.getConfigScreen(HConfig.class, parent).get();
    }
}

