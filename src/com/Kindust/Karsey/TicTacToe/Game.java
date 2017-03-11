package com.Kindust.Karsey.TicTacToe;

import java.io.IOException;
import java.util.ArrayList;
import java.util.Scanner;

public class Game {

	Space[] space;
	int[] value;
	ArrayList avail;
	int[] row;
	int[] col;
	int r, c;
	String winner;
	boolean win, tie, validInput;

	public Game(Space[] space, int[] value, int[] row, int[] col, int r, int c) {
		this.space = space;
		this.value = value;
		this.row = row;
		this.col = col;
		this.r = r;
		this.c = c;

		win = false;
		avail = new ArrayList();
		populate();

		spit();
		gameLoop();

	}

	public void gameLoop() {
		boolean first = true;
		while (!win && !tie) {
			updateBoard();
			int nxt = userInput();
			while (nxt == 1000) {
				System.out.println("Malformed input!  Retry.");
				nxt = userInput();
			}
			space[nxt].setTeam(1);
			avail.remove((Object) nxt);
			//avail.remove(avail.indexOf(nxt));
			checkW();
			checkTie();
			System.out.println(avail.clone());
			AI ai = new AI(space, avail);
			enactAI(ai.aiMove(nxt, first));
			System.out.println(avail.clone());
			System.out.println(space.clone());
			checkW();
			checkTie();
			first = false;
		}

		System.out.println();
		System.out.println();
		System.out.println();
		updateBoard();
		if (win) {
			System.out.println(winner + " wins!!  Congrats.  I guarentee its not X :D");
		}
		if (tie) {
			System.out.println("Issa tie!");
		}
	}

	public void populate() {
		for (int i = 0; i < 9; i++) {
			avail.add(i);
		}
	}

	public void updateBoard() {
		System.out.println();
		System.out.println();
		System.out.println();
		System.out.println();
		for (int i = 0; i < avail.size(); i++) {
			System.out.println(avail.get(i));
		}
		System.out.println();

		for (int i = 0; i < 9; i++) {
			System.out.print(space[i].getTeam(true));
			if (i == 2 || i == 5 || i == 8) {
				System.out.println();
			} else {
				System.out.print(" | ");
			}
			if (i == 2 || i == 5) {
				System.out.println("--|---|---");
			}
		}

		System.out.println();
	}

	public int userInput() {
		validInput = false;
		main: while (!validInput) {
			one: while (!validInput) {
				Scanner sc = new Scanner(System.in);
				System.out.print("Input space #: ");
				int in = sc.nextInt();

				try {
					if (Integer.valueOf(space[in].getTeam(false)) != 0) {
						// System.out.println("ERROR");
						// System.exit(1);
						validInput = false;
						System.out.print("Invalid! Reenter number, spot already occupied.");
						break one;
					} else {
					}
				} catch (Exception e) {
				}

				if (-1 < in && in < 9) {
					validInput = true;
					
					return in;
				} else {
					// System.out.println("ERROR");
					// System.exit(1);
					validInput = false;
					System.out.print("Invalid!  Number must be 0 to 8, excluding taken spots.  ");
					break one;
				}
			}
		}
		return 1000;
	}

	public void enactAI(int move) {
		if(move == 1000){
			tie = true;
			return;
		}
		if (Integer.valueOf(space[move].getTeam(false)) != 0) {
			System.out.println("ERROR");
			System.exit(1);
		}
		avail.remove((Object) move);
		space[move].setTeam(2);

	}

	public void aiMove() {

	}

	public void checkW() {
		if (aWin()) {
			win = true;
		}
	}

	

	public void spit() {
		System.out.println();
		System.out.println();
		System.out.println();
		System.out.println();
		System.out.println();
		/*
		 * for(int i1 = 0; i1 < 9; i1++){ System.out.println();
		 * System.out.println(space[i1].getCol() + ", " + space[i1].getRow() +
		 * " . . ." + space[i1].coord[0] + space[i1].coord[1] + " Team:" +
		 * space[i1].getTeam(true) + "(" + space[i1].getTeam(false) + ")"); if(r
		 * != 2){ r++; } else { r = 0; c++; } } System.out.println();
		 * System.out.println();
		 */
	}
	public void checkTie() {
		if (avail.size() == 0) {
			tie = true;
		}
	}
	public boolean aWin() {
		if (Integer.valueOf(space[0].getTeam(false)) == Integer.valueOf(space[1].getTeam(false))
				&& Integer.valueOf(space[0].getTeam(false)) == Integer.valueOf(space[2].getTeam(false))
				&& Integer.valueOf(space[0].getTeam(false)) != 0) {
			winner = space[0].getTeam(true);
			win = true;
			return true;
		}
		if (Integer.valueOf(space[3].getTeam(false)) == Integer.valueOf(space[4].getTeam(false))
				&& Integer.valueOf(space[4].getTeam(false)) == Integer.valueOf(space[5].getTeam(false))
				&& Integer.valueOf(space[3].getTeam(false)) != 0) {
			winner = space[4].getTeam(true);
			win = true;
			return true;
		}
		if (Integer.valueOf(space[6].getTeam(false)) == Integer.valueOf(space[7].getTeam(false))
				&& Integer.valueOf(space[7].getTeam(false)) == Integer.valueOf(space[8].getTeam(false))
				&& Integer.valueOf(space[6].getTeam(false)) != 0) {
			winner = space[6].getTeam(true);
			win = true;
			return true;
		}

		////////////////////////

		if (Integer.valueOf(space[0].getTeam(false)) == Integer.valueOf(space[4].getTeam(false))
				&& Integer.valueOf(space[4].getTeam(false)) == Integer.valueOf(space[8].getTeam(false))
				&& Integer.valueOf(space[0].getTeam(false)) != 0) {
			winner = space[0].getTeam(true);
			win = true;
			return true;
		}
		if (Integer.valueOf(space[4].getTeam(false)) == Integer.valueOf(space[6].getTeam(false))
				&& Integer.valueOf(space[4].getTeam(false)) == Integer.valueOf(space[2].getTeam(false))
				&& Integer.valueOf(space[4].getTeam(false)) != 0) {
			winner = space[4].getTeam(true);
			win = true;
			return true;
		}
		////////////////////////
		if (Integer.valueOf(space[0].getTeam(false)) == Integer.valueOf(space[3].getTeam(false))
				&& Integer.valueOf(space[0].getTeam(false)) == Integer.valueOf(space[6].getTeam(false))
				&& Integer.valueOf(space[0].getTeam(false)) != 0) {
			winner = space[0].getTeam(true);
			win = true;
			return true;
		}
		if (Integer.valueOf(space[1].getTeam(false)) == Integer.valueOf(space[4].getTeam(false))
				&& Integer.valueOf(space[1].getTeam(false)) == Integer.valueOf(space[7].getTeam(false))
				&& Integer.valueOf(space[4].getTeam(false)) != 0) {
			winner = space[1].getTeam(true);
			win = true;
			return true;
		}
		if (Integer.valueOf(space[2].getTeam(false)) == Integer.valueOf(space[5].getTeam(false))
				&& Integer.valueOf(space[2].getTeam(false)) == Integer.valueOf(space[8].getTeam(false))
				&& Integer.valueOf(space[2].getTeam(false)) != 0) {
			winner = space[2].getTeam(true);
			win = true;
			return true;
		}
		return false;
	}

}
