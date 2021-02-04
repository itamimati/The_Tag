package com.github.itamimati.tag;

import org.bukkit.Location;

public class TagLobby {
	private TagMain instance;

	 Location lobby;

	 Location wait;

	public TagLobby(TagMain plugin)
	{
		instance = plugin;
	}

	public Location getLobby()
	{
		return lobby;
	}

	public Location getWait()
	{
		return wait;
	}

	public Location setLobby(Location locat)
	{
		return lobby = locat;
	}

	public Location setWait(Location locat)
	{
		return wait = locat;
	}
}
