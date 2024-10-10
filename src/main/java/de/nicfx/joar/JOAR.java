package de.nicfx.joar;

import org.bukkit.Bukkit;
import org.bukkit.plugin.java.JavaPlugin;

import java.util.ArrayList;

public final class JOAR extends JavaPlugin {

    private ConfigManager configManager;
    private MapManager mapManager;
    private PlayerManager playerManager;
    private ConsoleManager consoleManager;
    private MobManager mobManager;

    private ArrayList<Updatable> updatables;

    private void init() {
        this.updatables = new ArrayList<>();
        this.consoleManager = new ConsoleManager(this);
        this.consoleManager.out("Initialized all Managers.");
        this.configManager = new ConfigManager(this);
        this.mapManager = new MapManager(this);
        this.playerManager = new PlayerManager(this);
        this.mobManager = new MobManager(this);
        updatables.add(configManager);
        updatables.add(mapManager);
        updatables.add(playerManager);
        updatables.add(mobManager);

        for (Updatable updatable : updatables) {
            updatable.init();
            updatable.initEvents(this, Bukkit.getPluginManager());
        }
        for (Updatable updatable : updatables) {
            updatable.pluginUpdate();
        }
    }

    @Override
    public void onEnable() {
        init();
    }

    @Override
    public void onDisable() {
        for (Updatable updatable : updatables) {
            updatable.saveConfig();
        }
    }


    public ConfigManager getConfigManager() {
        return configManager;
    }

    public MapManager getMapManager() {
        return mapManager;
    }

    public PlayerManager getPlayerManager() {
        return playerManager;
    }

    public ConsoleManager getConsoleManager() {
        return consoleManager;
    }


    public void update() {
        for (Updatable updatable : updatables) {
            updatable.pluginUpdate();
        }
    }
}
