package com.zitemaker.noCreeperExplosion;

import org.bukkit.ChatColor;
import org.bukkit.command.Command;
import org.bukkit.command.CommandSender;
import org.bukkit.command.ConsoleCommandSender;
import org.bukkit.command.TabExecutor;
import org.bukkit.entity.Player;
import org.bukkit.plugin.Plugin;

import java.util.ArrayList;
import java.util.List;

public class ReloadCommand implements TabExecutor {

    private static final String ANSI_GREEN = "\u001B[92m";
    private static final String ANSI_RESET = "\u001B[0m";

    private final Plugin plugin = NoCreeperExplosion.getPlugin();

    @Override
    public boolean onCommand(CommandSender sender, Command command, String label, String[] args) {

        // Check arguments
        if (args.length == 1 && args[0].equalsIgnoreCase("reload")) {

            // Reload the configuration
            plugin.reloadConfig();

            // Send confirmation in console
            plugin.getLogger().info(ANSI_GREEN + "Configuration reloaded successfully." + ANSI_RESET);

            // Notify the player
            if (sender instanceof Player) {
                sender.sendMessage(ChatColor.GREEN + "Configuration reloaded successfully.");
            }

            return true;
        }

        // Show correct usage after incorrect arguments
        if (sender instanceof ConsoleCommandSender) {
            plugin.getLogger().info("Usage: /nocreeperexplosion reload");
        }

        if (sender instanceof Player) {
            sender.sendMessage(ChatColor.RED + "Usage: /nocreeperexplosion reload");
        }

        return true;
    }

    @Override
    public List<String> onTabComplete(CommandSender sender, Command command, String label, String[] args) {

        List<String> suggestions = new ArrayList<>();

        if (sender instanceof Player player) {

            if (player.hasPermission("nocreeperexplosion.reload")) {
                if (args.length == 1) {
                    if ("reload".startsWith(args[0].toLowerCase())) {
                        suggestions.add("reload");
                    }
                }
            }
        }

        return suggestions;
    }
}
