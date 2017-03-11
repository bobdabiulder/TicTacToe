package com.Kindust.Karsey.TicTacToe;

import java.util.ArrayList;
import java.util.Random;

public class AI {
	Space[] space;
	ArrayList<Integer> availible;
	int[][] winpat = {{0,1,2},{3,4,5},{6,7,8},
						{0,3,6},{1,4,7},{2,5,8},
							{0,4,8},{2,4,6}};
	
	public AI(Space[] space, ArrayList<Integer> avail){
		this.space = space;
		this.availible = avail;
	}
	public int aiMove(){
		int result = move(); //= move();
		
		return result;
	}
	private int move(){
		int best = Integer.MAX_VALUE;
		int worst = Integer.MIN_VALUE;
		int r = new Random().nextInt(availible.size());
		return availible.get(r);
	}
}
