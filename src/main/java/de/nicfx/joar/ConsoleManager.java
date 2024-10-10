package de.nicfx.joar;

import java.util.logging.Level;

public class ConsoleManager {

    private final JOAR joar;

    public ConsoleManager(JOAR joar) {
        this.joar = joar;
    }

    public void out(String s) {
        joar.getLogger().log(Level.INFO,s);
    }

}
