package com.Kindust.Karsey.TicTacToe;

public class Main {
	
	int[] value = {-1, 0, 1}; //Loss, Tie, Win
	int[] row = {0,1,2};
	int[] col = {0,1,2};
	int ro,co;
	int r,c,it = 0,it1 = 0;
	Space[] space = new Space[9];
	
	public static void main(String args[]){
		Main game = new Main();
	}
	
	public Main(){
		init();
	}
	
	public void init(){
		c = 0;
		r = 0;
		it = 0;
		while(r < 3 && c < 3 ){
			space[it].setCol(c + 1);
			space[it].setRow(r + 1);
			space[it].setTeam(0);
			if(r != 2){
				r++;
			} else {
				r = 0;
				c++;
			}
			it++;
		}
		spit();
	}
	
	public void spit(){
		while(r < 3 && c < 3){
			System.out.println(space[it1].getCol() + ", " + space[it1].getRow() + " . . ." + space[it1].coord);
			if(r != 2){
				r++;
			} else {
				r = 0;
				c++;
			}
		}
	}
	
}
