package eu.donyka.config;

import me.shedaniel.autoconfig.AutoConfig;
import me.shedaniel.autoconfig.gui.ConfigScreenProvider;
import me.shedaniel.autoconfig.serializer.GsonConfigSerializer;
import net.minecraft.client.gui.screen.Screen;

import java.util.function.Supplier;

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
        Supplier<Screen> screen = AutoConfig.getConfigScreen(HConfig.class, parent);
        if (screen instanceof ConfigScreenProvider) {
            ConfigScreenProvider<HConfig> provider = (ConfigScreenProvider<HConfig>) screen;
            provider.setI13nFunction(manager -> "HitColor Settings");
            provider.setCategoryFunction((configName, category) -> "General");
            provider.setOptionFunction((category, field) -> getOptionName(field.getName()));
            return provider.get();
        }

        return screen.get();
    }

    private static String getOptionName(String fieldName) {
        switch (fieldName) {
            case "enable":
                return "Enable HitColor";
            case "enableArmor":
                return "Enable Armor Coloring";
            case "color":
                return "Hit Color";
            case "alpha":
                return "Alpha";
            default:
                return fieldName;
        }
    }
}

