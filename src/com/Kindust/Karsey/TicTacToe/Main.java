package com.Kindust.Karsey.TicTacToe;

public class Main {

	public int[] value = { -1, 0, 1 }; // Loss, Tie, Win
	int r, c, it1 = 0;
	Space[] space = new Space[9];

	public static void main(String args[]) {
		Main begin = new Main();
	}

	public Main() {
		init();
		Game game = new Game(space);
		while(game.repeat){
			init();
			game = new Game(space);
		}
		System.out.println("Thanks for playing!");
		game.sc.close();
	}

	public void init() {
		c = 0;
		r = 0;
		for (int it = 0; it <= 8; it++) {
			space[it] = new Space();
			space[it].setTeam(0);
			space[it].setCol(0);
			space[it].setRow(0);
		}
		for (int i = 0; i < 9; i++) {

			space[i].setCol(c);
			space[i].setRow(r);
			space[i].setTeam(0);
			if (c != 2) {
				c++;
			} else {
				c = 0;
				r++;
			}
			if (r == 2 && c == 2) {
				break;
			}
		}
		space[8].setCol(2);
		space[8].setRow(2);
		space[8].setTeam(0);
		r = 0;
		c = 0;
		spit();
	}

	public void spit() {
		for (int i1 = 0; i1 < 9; i1++) {
			if (r != 2) {
				r++;
			} else {
				r = 0;
				c++;
			}
		}
		System.out.println();
		System.out.println();
	}

}
