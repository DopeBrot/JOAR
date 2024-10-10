package de.nicfx.joar;

import de.nicfx.joar.mob.ActionMob;
import org.bukkit.Location;
import org.bukkit.entity.Entity;
import org.bukkit.entity.EntityType;
import org.bukkit.entity.memory.MemoryKey;
import org.bukkit.plugin.Plugin;
import org.bukkit.plugin.PluginManager;
import org.bukkit.scheduler.BukkitScheduler;

import java.util.ArrayList;
import java.util.UUID;

public class MobManager implements Updatable {

    private final JOAR joar;
    private final BukkitScheduler scheduler;

    private final ArrayList<ActionMob> mobs;

    private final ArrayList<Location> mobSpawnLocations;

    private int mobsPerPlayer = 5;
    private int maxMobs = 5;

    public MobManager(JOAR joar) {
        this.joar = joar;
        this.mobSpawnLocations = new ArrayList<>();
        this.scheduler = joar.getServer().getScheduler();
        this.scheduler.runTaskTimer(joar, this::update, 0, 20);
        this.mobs = new ArrayList<>();
        this.maxMobs = 5;
    }

    @Override
    public void initEvents(Plugin plugin, PluginManager manager) {

    }

    private void update() {
        this.maxMobs = this.joar.getPlayerManager().players().size() * mobsPerPlayer;

        if (mobs.isEmpty()) {
            while (this.mobs.size() < maxMobs) {
                ActionMob mob = new ActionMob(EntityType.ZOMBIE);
                this.mobs.add(mob);
                mob.spawn(mobSpawnLocations.get(Math.round((float) Math.random() * mobSpawnLocations.size())));
            }
        }

        if (!mobs.isEmpty()) {
            for (ActionMob mob : mobs) {
                if (mob.isSpawned()) {
                    for (UUID player : joar.getPlayerManager().players()) {
                        mob.getEntity().setMemory(MemoryKey.ANGRY_AT, player);
                        mob.getEntity().setMemory(MemoryKey.UNIVERSAL_ANGER, true);
                    }
                }
            }
        }
    }

    @Override
    public void pluginUpdate() {
    }

    @Override
    public void init() {
        for (Entity entity : this.joar.getMapManager().getWorld().getEntities()) {
            entity.remove();
        }
        this.mobsPerPlayer = 15;
        this.mobsPerPlayer = this.joar.getConfigManager().getConfig().getAsJsonObject("settings").get("mobsPerPlayer").getAsJsonPrimitive().getAsInt();

    }

    @Override
    public void saveConfig() {

    }
}
