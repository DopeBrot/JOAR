package de.nicfx.joar;


import org.bukkit.Bukkit;
import org.bukkit.Location;
import org.bukkit.Sound;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.player.PlayerJoinEvent;
import org.bukkit.plugin.Plugin;
import org.bukkit.plugin.PluginManager;

import javax.annotation.Nullable;
import java.util.ArrayList;
import java.util.UUID;

public class PlayerManager implements Updatable, Listener {

    public JOAR joar;
    private ArrayList<UUID> players;
    private Location getPlayerSpawn;


    public PlayerManager(JOAR joar) {
        this.joar = joar;
        this.players = new ArrayList<>();
    }

    public ArrayList<UUID> players() {
        return this.players;
    }

    @Override
    public void initEvents(Plugin plugin, PluginManager manager) {
        manager.registerEvents(this, plugin);
    }

    private boolean player(Player player) {
        return player(player.getUniqueId());
    }

    private boolean player(UUID player) {
        return players.contains(player);
    }

    private void addPlayer(Player player) {
        this.players.add(player.getUniqueId());
    }


    private void update() {
        joar.getConsoleManager().out("Current Players " + this.players.size());
    }

    @Nullable
    private Player get(UUID player) {
        return Bukkit.getPlayer(player);
    }

    public void init() {
        this.getPlayerSpawn = joar.getMapManager().getPlayerSpawnLocation();
    }

    @Override
    public void saveConfig() {

    }

    public void teleportPlayerToSpawn(Player player) {
        player.teleport(this.getPlayerSpawn);
        player.playSound(player, Sound.ENTITY_CHICKEN_EGG, 1f, 0.5f);
    }

    @EventHandler
    public void listenerPlayerSpawn(PlayerJoinEvent event) {
        Player player = event.getPlayer();
        this.addPlayer(player);
        teleportPlayerToSpawn(player);
        this.joar.update();
    }

    @Override
    public void pluginUpdate() {

    }
}
