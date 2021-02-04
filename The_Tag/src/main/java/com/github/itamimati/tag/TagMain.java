package com.github.itamimati.tag;

import org.bukkit.configuration.file.FileConfiguration;
import org.bukkit.plugin.java.JavaPlugin;

public class TagMain extends JavaPlugin{

	public String g_prefix = "+ Tag + :";
	public FileConfiguration config;

	public TagPlayer cplayer = new TagPlayer(this);
	public TagScoreboard cboard = new TagScoreboard(this);
	public TagLobby clobby = new TagLobby(this);

	public boolean tag_ingame = false;

	@Override
	public void onEnable()
	{
		new TagCommands(this);
		this.getPluginLoader().createRegisteredListeners(cplayer,this);
		this.getPluginLoader().createRegisteredListeners(cboard,this);

		config = this.getConfig();

		tag_ingame = false;
	}

	@Override
	public void onDisable()
	{

	}
}
