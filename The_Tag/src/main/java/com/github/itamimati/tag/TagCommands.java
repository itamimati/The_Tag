package com.github.itamimati.tag;

import java.util.List;
import java.util.Random;

import org.bukkit.Bukkit;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Entity;
import org.bukkit.entity.Player;

public class TagCommands implements CommandExecutor{

	private final TagMain instance;

	public TagCommands(TagMain plugin)
	{
		this.instance = plugin;
	}

	public boolean onCommand(CommandSender sender, Command cmd, String label, String[] args)
	{
		if (!label.equalsIgnoreCase("tag")) return true;

		switch (args[0].toLowerCase())
		{
		case "reload":
			instance.reloadConfig();
			sender.sendMessage(instance.g_prefix + "Config Reloaded.");
			break;
		case "set":
			switch (args[1].toLowerCase())
			{
			case "tag":
				if (args[2].length() > 0)
				{
					Player player = Bukkit.getPlayerExact(args[2].toString());
					if (player != null)
					{
						if (instance.cboard.hunter.hasEntry(player.getName().toString()))
						{
							sender.sendMessage(instance.g_prefix + "対象はすでに鬼です");
							return true;
						}

						instance.cboard.hunter.addEntry(player.getName().toString());
						Bukkit.getServer().broadcastMessage(instance.g_prefix + player.getName() + "さんが鬼になりました。");

						if (instance.clobby.getWait() != null)
						{
							player.teleport(instance.clobby.getWait());
						}
					}
					else
					{
						sender.sendMessage(instance.g_prefix + "対象のプレイヤーが存在しません。");
					}
				}
				else
				{
					List<Player> list = null;

					for (Player player : Bukkit.getOnlinePlayers())
					{
						if (!instance.cboard.hunter.hasEntry(player.getName().toString()))
						{
							list.add(player);
						}
					}

					if (list == null)
					{
						sender.sendMessage(instance.g_prefix + "人数が足りません。");
					}

					int idx = new Random().nextInt(list.size());

					Player player = list.get(idx);

					instance.cboard.hunter.addEntry(player.getName().toString());
					Bukkit.getServer().broadcastMessage(instance.g_prefix + player.getName() + "さんが鬼になりました。");

					if (instance.clobby.getWait() != null)
					{
						player.teleport(instance.clobby.getWait());
					}
				}
				break;
			case "lobby":
				if (sender instanceof Player)
				{
					instance.clobby.setLobby(((Entity) sender).getLocation());
				}
				break;
			case "wait":
				if (sender instanceof Player)
				{
					instance.clobby.setWait(((Entity) sender).getLocation());
				}
				break;
			}
			break;
		default:

			break;
		}
		return true;
	}
}
