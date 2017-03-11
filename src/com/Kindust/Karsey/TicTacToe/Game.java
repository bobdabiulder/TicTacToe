package com.Kindust.Karsey.TicTacToe;

import java.io.IOException;
import java.util.Scanner;

public class Game{
	
	Space[] space;
	int[] value;
	int[] row;
	int[] col; 
	int r,c;
	boolean win;
	
	public Game(Space[] space, int[] value, int[] row, int[] col, int r, int c){
		this.space = space;
		this.value = value;
		this.row = row;
		this.col = col;
		this.r = r;
		this.c = c;
		
		win = false;
		
		spit();
		gameLoop();
		
	}
	
	public void gameLoop(){
		while(!win){
			updateBoard();
			space[userInput()].setTeam(1);
		}
	}
	
	public void updateBoard(){
		System.out.println();
		
		for(int i = 0; i < 9; i++){
			System.out.print(space[i].getTeam(true));
			if(i == 2 || i == 5 || i ==8){
				System.out.println();
			} else {
				System.out.print(" | ");
			}
			if(i == 2 || i == 5){
				System.out.println("--|---|---");
			}
		}
		
		System.out.println();
	}
	
	public int userInput(){
		Scanner sc = new Scanner(System.in);
		int in = sc.nextInt();

		if (in < 9) {
			return in;
		} else{
			System.out.println("ERROR");
			System.exit(1);
		}
		return in;
	}

	
	
	
	public void spit(){
		System.out.println();
		System.out.println();		
		System.out.println();
		System.out.println();
		System.out.println();
		/*for(int i1 = 0; i1 < 9; i1++){
			System.out.println();
			System.out.println(space[i1].getCol() + ", " + space[i1].getRow() + " . . ." + space[i1].coord[0] + space[i1].coord[1] + " Team:" + space[i1].getTeam(true) + "(" + space[i1].getTeam(false) + ")");
			if(r != 2){
				r++;
			} else {
				r = 0;
				c++;
			}
		}
		System.out.println();
		System.out.println();*/
	}
}
