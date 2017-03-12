package com.Kindust.Karsey.TicTacToe;

public class Team {
	Team t;
	String[] teams = { " ", "X", "O" };
	int team;

	public Team(int team) {
		this.team = team;
	}

	public void setTeam(int t) {
		team = t;
	}

	public String getTeam() {
		return teams[team];
	}

	public int getTeamNum() {
		return team;
	}
}
