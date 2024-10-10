package de.nicfx.joar;

import com.google.gson.JsonArray;
import com.google.gson.JsonElement;
import com.google.gson.JsonObject;
import org.bukkit.*;
import org.bukkit.plugin.Plugin;
import org.bukkit.plugin.PluginManager;

import java.util.ArrayList;

public class MapManager implements Updatable {

    private JOAR joar;

    private Location playerSpawnLocation;
    private ArrayList<Location> mobSpawnLocation;

    private World world;

    public MapManager(JOAR joar) {
        this.joar = joar;
    }

    @Override
    public void pluginUpdate() {

    }

    @Override
    public void init() {
        initLocations();
    }

    @Override
    public void saveConfig() {

    }

    @Override
    public void initEvents(Plugin plugin, PluginManager manager) {

    }

    private void initLocations() {

        JsonObject playerSpawnLocation = joar.getConfigManager().getConfig().getAsJsonObject("map").get("playerSpawn").getAsJsonObject();
        this.playerSpawnLocation = joar.getConfigManager().getLocation(playerSpawnLocation);
        String mapWorldName = joar.getConfigManager().getConfig().getAsJsonObject("map").get("world").getAsJsonPrimitive().getAsString();
        System.out.println(mapWorldName);
        this.world = Bukkit.getWorld(mapWorldName);
        ArrayList<Location> mobSpawnLocation = new ArrayList<>();
        JsonArray arr = joar.getConfigManager().getConfig().getAsJsonObject("map").getAsJsonArray("mobSpawnLocation");
        for (JsonElement object : arr.asList()) {
            mobSpawnLocation.add(joar.getConfigManager().getLocation(object.getAsJsonObject()));
        }

        world.setSpawnLocation(this.playerSpawnLocation);
        world.setDifficulty(Difficulty.HARD);
        world.setPVP(true);
        world.setSpawnFlags(false, false);
        world.setFullTime(0);
        world.setHardcore(false);
        world.setClearWeatherDuration(Integer.MAX_VALUE);
        world.setAutoSave(false);
        world.setGameRule(GameRule.ANNOUNCE_ADVANCEMENTS, false);
        world.setGameRule(GameRule.BLOCK_EXPLOSION_DROP_DECAY, false);
        world.setGameRule(GameRule.COMMAND_BLOCK_OUTPUT, false);
        world.setGameRule(GameRule.DISABLE_RAIDS, true);
        world.setGameRule(GameRule.COMMAND_MODIFICATION_BLOCK_LIMIT, 1);
        world.setGameRule(GameRule.DISABLE_ELYTRA_MOVEMENT_CHECK, true);
        world.setGameRule(GameRule.DO_DAYLIGHT_CYCLE, false);
        world.setGameRule(GameRule.DO_ENTITY_DROPS, false);
        world.setGameRule(GameRule.DO_FIRE_TICK, true);
        world.setGameRule(GameRule.ANNOUNCE_ADVANCEMENTS, false);
        world.setGameRule(GameRule.DO_IMMEDIATE_RESPAWN, true);
        world.setGameRule(GameRule.DO_INSOMNIA, false);
        world.setGameRule(GameRule.DO_LIMITED_CRAFTING, false);
        world.setGameRule(GameRule.DO_MOB_LOOT, false);
        world.setGameRule(GameRule.DO_MOB_SPAWNING, false);
        world.setGameRule(GameRule.DO_PATROL_SPAWNING, false);
        world.setGameRule(GameRule.DO_TILE_DROPS, false);
        world.setGameRule(GameRule.DO_TRADER_SPAWNING, false);
        world.setGameRule(GameRule.DO_VINES_SPREAD, false);
        world.setGameRule(GameRule.DO_WARDEN_SPAWNING, false);
        world.setGameRule(GameRule.DO_WEATHER_CYCLE, false);
        world.setGameRule(GameRule.DROWNING_DAMAGE, false);
        world.setGameRule(GameRule.ENDER_PEARLS_VANISH_ON_DEATH, true);
        world.setGameRule(GameRule.FALL_DAMAGE, false);
        world.setGameRule(GameRule.FIRE_DAMAGE, false);
        world.setGameRule(GameRule.FORGIVE_DEAD_PLAYERS, false);
        world.setGameRule(GameRule.FREEZE_DAMAGE, false);
        world.setGameRule(GameRule.KEEP_INVENTORY, true);
        world.setGameRule(GameRule.LAVA_SOURCE_CONVERSION, false);
        world.setGameRule(GameRule.GLOBAL_SOUND_EVENTS, true);
        world.setGameRule(GameRule.LOG_ADMIN_COMMANDS, false);
        world.setGameRule(GameRule.MAX_COMMAND_CHAIN_LENGTH, 1);
        world.setGameRule(GameRule.MAX_COMMAND_FORK_COUNT, 1);
        world.setGameRule(GameRule.MAX_ENTITY_CRAMMING, (int) Short.MAX_VALUE);
        world.setGameRule(GameRule.MOB_EXPLOSION_DROP_DECAY, false);
        world.setGameRule(GameRule.MOB_GRIEFING, false);
        world.setGameRule(GameRule.NATURAL_REGENERATION, false);
        world.setGameRule(GameRule.PLAYERS_NETHER_PORTAL_CREATIVE_DELAY, (int) Short.MAX_VALUE);
        world.setGameRule(GameRule.PLAYERS_NETHER_PORTAL_DEFAULT_DELAY, (int) Short.MAX_VALUE);
        world.setGameRule(GameRule.PLAYERS_SLEEPING_PERCENTAGE, 100);
        world.setGameRule(GameRule.PROJECTILES_CAN_BREAK_BLOCKS, false);
        world.setGameRule(GameRule.RANDOM_TICK_SPEED, 0);
        world.setGameRule(GameRule.REDUCED_DEBUG_INFO, true);
        world.setGameRule(GameRule.SEND_COMMAND_FEEDBACK, false);
        world.setGameRule(GameRule.SHOW_DEATH_MESSAGES, false);
        world.setGameRule(GameRule.SNOW_ACCUMULATION_HEIGHT, 1);
        world.setGameRule(GameRule.SPAWN_CHUNK_RADIUS, 1);
        world.setGameRule(GameRule.SPAWN_RADIUS, 1);
        world.setGameRule(GameRule.SPECTATORS_GENERATE_CHUNKS, false);
        world.setGameRule(GameRule.TNT_EXPLOSION_DROP_DECAY, false);
        world.setGameRule(GameRule.UNIVERSAL_ANGER, true);
        world.setGameRule(GameRule.WATER_SOURCE_CONVERSION, false);

    }

    public JOAR getJoar() {
        return joar;
    }

    public ArrayList<Location> getMobSpawnLocation() {
        return this.mobSpawnLocation;
    }

    public Location getPlayerSpawnLocation() {
        return playerSpawnLocation;
    }

    public World getWorld() {
        return world;
    }
}
