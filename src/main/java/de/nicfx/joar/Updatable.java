package de.nicfx.joar;

import org.bukkit.plugin.Plugin;
import org.bukkit.plugin.PluginManager;

public interface Updatable {

    void pluginUpdate();

    void init();

    void saveConfig();

    void initEvents(Plugin plugin, PluginManager manager);
}
