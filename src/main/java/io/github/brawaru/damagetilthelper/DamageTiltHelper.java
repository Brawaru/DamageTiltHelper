package io.github.brawaru.damagetilthelper;

import com.destroystokyo.paper.event.entity.EntityKnockbackByEntityEvent;
import com.google.common.io.ByteArrayDataOutput;
import com.google.common.io.ByteStreams;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.plugin.java.JavaPlugin;

public final class DamageTiltHelper extends JavaPlugin implements Listener {
  private static final String CHANNEL_NAME = "damagetilt:attackedatyaw";

  @Override
  public void onEnable() {
    getServer().getMessenger().registerOutgoingPluginChannel(this, CHANNEL_NAME);
    getServer().getPluginManager().registerEvents(this, this);
  }

  @EventHandler
  private void onKnockback(EntityKnockbackByEntityEvent e) {
    if (e.getEntity() instanceof Player) {
      Player player = (Player) e.getEntity();
      ByteArrayDataOutput out = ByteStreams.newDataOutput();
      out.writeFloat(player.getHurtDirection());
      player.sendPluginMessage(this, CHANNEL_NAME, out.toByteArray());
    }
  }

  @Override
  public void onDisable() {
    getServer().getMessenger().unregisterOutgoingPluginChannel(this);
  }
}
