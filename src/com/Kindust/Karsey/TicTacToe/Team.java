package com.Kindust.Karsey.TicTacToe;

public class Team {
	Team t;
	String[] teams = {" ", "X", "O"};
	int team;
	
	public Team(boolean isX){
		team = 0;
	}
	
	public void setTeam(int t){
		if(t != team){
			team = t;
		}
	}
	
	public String getTeam(){
		return teams[team];
	}
	public int getTeamNum(){
		return team;
	}
}
