package com.slayer.manager.gui;

public class Task {
	
	private final int level;
	private final String master;
	private final String monster;
	private final int amount;
	
	public Task(final int level, final String master, final String monster, final int amount){
		this.level = level;
		this.master = master;
		this.monster = monster;
		this.amount = amount;
	};
	
	public int level(){
		return level;
	}
	
	public String master(){
		return master;
	}

	public String monster(){
		return monster;
	}
	
	public int amount(){
		return amount;
	}
	
	public String toString(){
		return String.format("Level: %d | Master: %s | Monster: %s | Amount: %d", level, master, monster, amount);
	}

}
