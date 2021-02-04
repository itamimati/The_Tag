package com.github.itamimati.tag;

import org.bukkit.entity.Entity;
import org.bukkit.entity.LivingEntity;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.entity.EntityDamageByEntityEvent;
import org.bukkit.potion.PotionEffectType;

public class TagPlayer implements Listener{
	private final TagMain instance;

	public TagPlayer(TagMain plugin)
	{
		instance = plugin;
	}

	@EventHandler
	public void onTouch(EntityDamageByEntityEvent event)
	{
		if (!instance.tag_ingame) return;

		Entity attacker = event.getEntity();
		Entity victim = event.getDamager();

		if (!(attacker instanceof Player)) return;
		if (!(victim instanceof Player)) return;

		if (!instance.cboard.hunter.hasEntry(attacker.getName().toString())) return;
		if (!instance.cboard.escaper.hasEntry(victim.getName().toString())) return;

		if (((LivingEntity) attacker).hasPotionEffect(PotionEffectType.BLINDNESS)) return;

		instance.cboard.onTagConvert((Player) attacker, (Player) victim);
	}
}
