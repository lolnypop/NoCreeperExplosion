package me.lolnypop.noCreeperExplosion;

import me.lolnypop.noCreeperExplosion.Commands.ReloadCommand;
import me.lolnypop.noCreeperExplosion.Listeners.CreeperListeners;
import org.bukkit.event.Listener;
import org.bukkit.plugin.java.JavaPlugin;

public final class NoCreeperExplosion extends JavaPlugin implements Listener {

    // ANSI color code for green
    private static final String ANSI_GREEN = "\u001B[92m";
    private static final String ANSI_RESET = "\u001B[0m";
    private static final String ANSI_RED = "\\e[0;31m";

    @Override
    public void onLoad() {

        // Plugin load success message
        getLogger().info(ANSI_GREEN + "NoCreeperExplosion.jar v" + getDescription().getVersion() + " has been loaded successfully" + ANSI_RESET);
    }

    @Override
    public void onEnable() {

        // Plugin startup success message
        getLogger().info(ANSI_GREEN + "NoCreeperExplosion.jar v" + getDescription().getVersion() + " has been enabled successfully" + ANSI_RESET);

        // Save default config
        getConfig().options().copyDefaults();
        saveDefaultConfig();

        // Register the event listeners
        getServer().getPluginManager().registerEvents(new CreeperListeners(this), this);

        // Register commands
        getCommand("nocreeperexplosion").setExecutor(new ReloadCommand(this));
        getCommand("nocreeperexplosion").setTabCompleter(new ReloadCommand(this));
    }

    @Override
    public void onDisable() {

        // Plugin disable success message
        getLogger().info(ANSI_RED + "NoCreeperExplosion.jar v" + getDescription().getVersion() + " has been disabled successfully" + ANSI_RESET);
    }
}