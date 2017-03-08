package com.Kindust.Karsey.TicTacToe;

public class Space {
	
	public boolean isOpen;
	private int row,col;
	public int[] coord = {col,row};
	public Team t;
	
	public Space(){
		isOpen = true;
		row = 0;
		col = 0;
		t.setTeam(0);
	}
	/*------------------------------------*/
	public String getTeam(){
		return t.getTeam();
	}
	public void setTeam(int newTeam){
		if(isOpen == true && t.getTeamNum() != 0){
			t.setTeam(newTeam);
			isOpen = false;
		}
	}
	/*------------------------------------*/
	public int getCol(){
		return col;
	}
	public void setCol(int col){
		this.col = col;
	}
	/*------------------------------------*/
	public int getRow(){
		return row;
	}
	public void setRow(int row){
		this.row = row;
	}
	
}
