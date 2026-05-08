package eu.donyka.config;

import me.shedaniel.autoconfig.ConfigData;
import me.shedaniel.autoconfig.annotation.Config;
import me.shedaniel.autoconfig.annotation.ConfigEntry;

@Config(name = "hitcolor")
public final class HConfig implements ConfigData {
    @ConfigEntry.Gui.Tooltip
    public boolean enable = false;

    @ConfigEntry.Gui.Tooltip
    public boolean enableArmor = false;

    @ConfigEntry.ColorPicker(allowAlpha = false)
    public int color = 0xFF0000;

    @ConfigEntry.BoundedDiscrete(min = 0L, max = 255L)
    @ConfigEntry.Gui.RequiresRestart(false)
    public int alpha = 255;
}

