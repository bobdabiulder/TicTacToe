package com.Kindust.Karsey.TicTacToe;

public class Space {

	public boolean isOpen;
	private int row;
	private int col;
	public int[] coord = { col, row };
	public Team t;

	public Space() {
		isOpen = true;
		t = new Team(0);
		row = 0;
		col = 0;
		t.setTeam(0);
	}

	/*------------------------------------*/
	public String getTeam(boolean hr) {
		if (hr) {
			return t.getTeam();
		}
		return String.valueOf(t.getTeamNum() + "");
	}

	public void setTeam(int newTeam) {
		if (t.getTeamNum() == 0) {
			t.setTeam(newTeam);
			isOpen = false;
		}
	}

	/*------------------------------------*/
	public int getCol() {
		System.out.println("d");
		return col;
	}

	public void setCol(int col) {
		this.col = col;
		updateCoord();
	}

	/*------------------------------------*/
	public int getRow() {
		return row;
	}

	public void setRow(int row) {
		this.row = row;
		updateCoord();
	}

	private void updateCoord() {
		coord[0] = col;
		coord[1] = row;
	}

}
