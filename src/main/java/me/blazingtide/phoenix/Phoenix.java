package me.blazingtide.phoenix;

import com.google.common.collect.Maps;
import lombok.Getter;
import org.bukkit.Bukkit;
import org.bukkit.plugin.java.JavaPlugin;

import java.util.Map;
import java.util.UUID;

/**
 * I made this class static since I only want 1 instance of the GUI handler at all times.
 * So, each plugin should not be creating their own instances which will prevent overlaping of
 * GUIS.
 */
@Getter
public class Phoenix {

    static final String PREFIX = "[Phoenix GUI library] ";
    /**
     * Since we're going to be using multiple threads at the same time
     * when attempting to update GUIS for players, Concurrent HashMap will ensure that
     * there are 0 complications.
     */
    private final Map<UUID, GUI> openGUIS = Maps.newConcurrentMap();
    private final UpdaterThread updater = new UpdaterThread(this);

    private final JavaPlugin plugin;

    public Phoenix(JavaPlugin plugin) {
        this.plugin = plugin;

        Bukkit.getLogger().info(PREFIX + "Attempting to register the library.");
        Bukkit.getPluginManager().registerEvents(new PhoenixListener(openGUIS), plugin);
        GUI.PHOENIX = this;
    }

}
