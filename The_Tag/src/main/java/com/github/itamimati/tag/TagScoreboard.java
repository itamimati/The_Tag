package com.github.itamimati.tag;

import org.bukkit.Bukkit;
import org.bukkit.ChatColor;
import org.bukkit.entity.Player;
import org.bukkit.event.Listener;
import org.bukkit.potion.PotionEffect;
import org.bukkit.potion.PotionEffectType;
import org.bukkit.scoreboard.Scoreboard;
import org.bukkit.scoreboard.ScoreboardManager;
import org.bukkit.scoreboard.Team;

public class TagScoreboard implements Listener {
	private final TagMain instance;

	ScoreboardManager manager = Bukkit.getScoreboardManager();
	Scoreboard board = manager.getNewScoreboard();

	Team hunter;
	Team escaper;

	public TagScoreboard(TagMain plugin)
	{
		instance = plugin;

		hunter = board.registerNewTeam("Hunter");
		escaper = board.registerNewTeam("escaper");

		hunter.setPrefix(ChatColor.RED + "[Hunter]" + ChatColor.RESET);
		escaper.setPrefix(ChatColor.RED + "[Escaper]" + ChatColor.RESET);

		hunter.setOption(Team.Option.NAME_TAG_VISIBILITY, Team.OptionStatus.FOR_OWN_TEAM);
		escaper.setOption(Team.Option.NAME_TAG_VISIBILITY, Team.OptionStatus.FOR_OWN_TEAM);
	}

	public void onTagConvert(Player toTag, Player toEscaper)
	{
		hunter.removeEntry(toEscaper.getName().toString());
		escaper.removeEntry(toTag.getName().toString());
		hunter.addEntry(toTag.getName().toString());
		escaper.addEntry(toEscaper.getName().toString());

		toEscaper.setWalkSpeed((float) 0.2);
		toEscaper.addPotionEffect(new PotionEffect(PotionEffectType.SPEED, 5 * 20, 3));

		toTag.setWalkSpeed((float) 0.3);
		toTag.addPotionEffect(new PotionEffect(PotionEffectType.SLOW, 3 * 20, 5));;
		toTag.addPotionEffect(new PotionEffect(PotionEffectType.BLINDNESS, 3 * 20, 1));
	}

	public void onScoreReset()
	{
		for (Player player : Bukkit.getOnlinePlayers())
		{
			hunter.removeEntry(player.getName().toString());
			escaper.removeEntry(player.getName().toString());

			player.setWalkSpeed((float) 0.2);
			player.teleport(instance.clobby.getLobby());
		}
	}
}
