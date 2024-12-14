package me.lolnypop.noCreeperExplosion.Listeners;

import me.lolnypop.noCreeperExplosion.NoCreeperExplosion;
import org.bukkit.event.EventHandler;
import org.bukkit.entity.EntityType;
import org.bukkit.entity.LivingEntity;
import org.bukkit.entity.Player;
import org.bukkit.entity.Tameable;
import org.bukkit.event.Listener;
import org.bukkit.event.entity.CreatureSpawnEvent;
import org.bukkit.event.entity.EntityDamageByEntityEvent;
import org.bukkit.event.entity.EntityExplodeEvent;
import org.bukkit.event.entity.EntityTargetEvent;

public class CreeperListeners implements Listener {

    private final NoCreeperExplosion plugin;

    public CreeperListeners(NoCreeperExplosion plugin) {
        this.plugin = plugin;
    }

    // Disables creeper griefing
    @EventHandler
    public void onCreeperExplode(EntityExplodeEvent event) {

        boolean shouldCreeperGrief = plugin.getConfig().getBoolean("creeper-explosion-grief");

        if (shouldCreeperGrief) {
            return;
        }

        if (event.getEntity().getType() == EntityType.CREEPER) {
            event.setCancelled(true);
        }
    }

    // Disables creeper damage to entities
    @EventHandler
    public void onCreeperDamageToEntities(EntityDamageByEntityEvent event) {

        boolean shouldCreeperDamageEntities = plugin.getConfig().getBoolean("creeper-damage-entities");

        if (shouldCreeperDamageEntities) {
            return;
        }

        if (event.getDamager().getType() == EntityType.CREEPER) {

            if (event.getEntity() instanceof Player) {
                return;
            }

            if (event.getEntity() instanceof LivingEntity) {
                event.setCancelled(true);
            }
        }
    }

    // Disables creeper damage to Players
    @EventHandler
    public void onCreeperDamageToPlayers(EntityDamageByEntityEvent event) {

        boolean shouldCreeperDamageEntities = plugin.getConfig().getBoolean("creeper-damage-players");

        if (shouldCreeperDamageEntities) {
            return;
        }

        if (event.getDamager().getType() == EntityType.CREEPER) {

            if (event.getEntity() instanceof Player) {
                event.setCancelled(true);
            }
        }
    }

    // Disable creeper spawning
    @EventHandler
    public void onCreeperSpawn(CreatureSpawnEvent event) {

        boolean shouldCreeperSpawn = plugin.getConfig().getBoolean("disable-creeper-spawning");

        if (!shouldCreeperSpawn) {
            return;
        }

        if (event.getEntity().getType() == EntityType.CREEPER) {
            event.setCancelled(true);
        }
    }

    // Disable creeper targetting players
    @EventHandler
    public void onCreeperTarget(EntityTargetEvent event) {

        boolean shouldCreeperTarget = plugin.getConfig().getBoolean("disable-creeper-aggression");

        if (!shouldCreeperTarget) {
            return;
        }

        if (event.getEntity().getType() == EntityType.CREEPER) {
            event.setCancelled(true);
        }
    }

    // Disable creeper damage to tamed animals
    @EventHandler
    public void onCreeperDamageTamedAnimal(EntityDamageByEntityEvent event) {

        boolean shouldCreeperDamageTamedAnimals = plugin.getConfig().getBoolean("creeper-damage-pets");

        if (shouldCreeperDamageTamedAnimals) {
            return;
        }

        if (event.getDamager().getType() == EntityType.CREEPER) {
            if (event.getEntity() instanceof Tameable tameable) {

                if (tameable.isTamed()) {
                    event.setCancelled(true);
                }
            }
        }
    }
}