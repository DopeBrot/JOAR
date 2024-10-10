package de.nicfx.joar;

import com.google.gson.Gson;
import com.google.gson.JsonElement;
import com.google.gson.JsonObject;
import com.google.gson.JsonParser;
import org.bukkit.Bukkit;
import org.bukkit.Location;
import org.bukkit.plugin.Plugin;
import org.bukkit.plugin.PluginManager;

import java.io.*;
import java.net.URISyntaxException;
import java.security.CodeSource;

public class ConfigManager implements Updatable {

    private Gson gson;
    private final File configFile;
    private final JsonElement configJson;
    private final JOAR joar;

    public ConfigManager(JOAR joar) {
        this.joar = joar;
        this.gson = new Gson();
        this.configFile = new File("plugins/JOAR/config.json");
        new File("plugins/JOAR").mkdirs();
        if (!configFile.exists()) {
            try {
                configFile.createNewFile();
                copyResourceFile("config.json", "plugins/JOAR/config.json");
            } catch (IOException | URISyntaxException e) {
                throw new RuntimeException(e);
            }
        }
        try {
            this.configJson = JsonParser.parseReader(new FileReader(configFile));
        } catch (FileNotFoundException e) {
            throw new RuntimeException(e);
        }
        System.out.println(configJson.isJsonObject());
    }

    @Override
    public void initEvents(Plugin plugin, PluginManager manager) {

    }

    @Override
    public void pluginUpdate() {

    }

    @Override
    public void init() {

    }

    @Override
    public void saveConfig() {

    }

    public JsonObject getConfig() {
        return configJson.getAsJsonObject();
    }

    public Location getLocation(JsonObject object) {
        int x = object.get("x").getAsJsonPrimitive().getAsInt();
        int y = object.get("y").getAsJsonPrimitive().getAsInt();
        int z = object.get("z").getAsJsonPrimitive().getAsInt();
        String w = object.get("world").getAsJsonPrimitive().getAsString();
        return new Location(Bukkit.getWorld(w), x, y, z);
    }

    /**
     * Copies a resource file from the JAR to the specified output path.
     *
     * @param resourcePath   The path to the resource inside the JAR (e.g., "/yourfile.txt").
     * @param outputFileName The name of the output file (relative to the JAR's location).
     * @throws IOException        If an I/O error occurs.
     * @throws URISyntaxException If the URI syntax is incorrect.
     */
    public void copyResourceFile(String resourcePath, String outputFileName) throws IOException, URISyntaxException {
        // Load the resource file
        InputStream resourceStream = this.joar.getClass().getResourceAsStream(resourcePath);

        if (resourceStream == null) {
            throw new FileNotFoundException("Resource not found: " + resourcePath);
        }

        // Get the directory where the JAR file is located
        CodeSource codeSource = this.joar.getClass().getProtectionDomain().getCodeSource();
        File jarFile = new File(codeSource.getLocation().toURI());
        File jarDir = jarFile.getParentFile(); // The directory containing the JAR

        // Create the destination file in the JAR directory
        File destinationFile = new File(jarDir, outputFileName);

        // Write the file from resources to the destination directory
        try (OutputStream outputStream = new FileOutputStream(destinationFile)) {
            copyStream(resourceStream, outputStream);
            System.out.println("File successfully copied to: " + destinationFile.getAbsolutePath());
        } catch (IOException e) {
            System.err.println("Failed to copy file: " + e.getMessage());
            throw e;
        }
    }

    // Helper method to copy InputStream to OutputStream
    private void copyStream(InputStream in, OutputStream out) throws IOException {
        byte[] buffer = new byte[1024];
        int bytesRead;
        while ((bytesRead = in.read(buffer)) != -1) {
            out.write(buffer, 0, bytesRead);
        }
    }

}
