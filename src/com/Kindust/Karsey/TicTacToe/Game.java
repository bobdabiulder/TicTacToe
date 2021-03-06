package com.Kindust.Karsey.TicTacToe;

import java.util.ArrayList;
import java.util.Scanner;

public class Game {

	Space[] space;
	ArrayList<Integer> avail;
	String winner;
	Scanner sc;
	boolean win, tie, validInput, repeat;

	public Game(Space[] space) {
		this.space = space;
		win = false;
		avail = new ArrayList<Integer>();
		sc = new Scanner(System.in);
		populate();
		System.out.println("Ready to play.");// https://opensource.org/licenses/MPL-2.0
		System.out.println();
		System.out.println();
		System.out.println();
		System.out.println("This software is licensed under the Mozilla Public License 2.0");
		System.out.println("Please visit https://opensource.org/licenses/MPL-2.0 for more info.");

		spit();
		gameLoop();
		repeat = repeat();
	}

	public void gameLoop() {
		boolean first = true;
		while (!win && !tie) {
			updateBoard(first);
			int nxt = userInput();
			while (nxt == 1000) {
				System.out.println("Malformed input!  Retry.");
				nxt = userInput();
			}
			space[nxt].setTeam(1);
			avail.remove((Object) nxt);
			// avail.remove(avail.indexOf(nxt));
			if (checkW() || checkTie()) {
				break;
			}
			System.out.println(avail.clone());
			AI ai = new AI(space, avail);
			enactAI(ai.aiMove(nxt, first));
			System.out.println(avail.clone());
			System.out.println(space.clone());
			if (checkW() || checkTie()) {
				break;
			}
			first = false;
		}

		System.out.println();
		System.out.println();
		System.out.println();
		updateBoard(false);
		if (win) {
			System.out.println(winner + " wins!!  Congrats to " + winner + ".  I can almost guarentee its not X :D");
		}
		if (tie) {
			System.out.println("Tie!");
		}
	}

	public void populate() {
		for (int i = 0; i < 9; i++) {
			avail.add(i);
		}
	}

	public void updateBoard(boolean first) {
		System.out.println();
		System.out.println();
		System.out.println();
		System.out.println();
		System.out.println("Availible spots:");
		for (int i = 0; i < avail.size(); i++) {
			System.out.println(avail.get(i));
		}
		System.out.println();
		if (first) {
			System.out.println("Scroll up for tips");
		}
		System.out.println();

		for (int i = 0; i < 9; i++) {
			System.out.print(" " + space[i].getTeam(true));
			if (i == 2 || i == 5 || i == 8) {
				System.out.println();
			} else {
				System.out.print(" |");
			}
			if (i == 2 || i == 5) {
				System.out.println("---|---|---");
			}
		}

		System.out.println();
	}

	public int userInput() {
		validInput = false;
		main: while (!validInput) {
			one: while (!validInput) {
				Scanner sc = new Scanner(System.in);
				// sc.reset();
				System.out.print("Input space #: ");
				while (!sc.hasNextInt()) {
					System.out.print("Invalid!  Not a number. Try again.  -  ");
					sc.nextLine();
				}
				int in = sc.nextInt();

				try {
					if (Integer.valueOf(space[in].getTeam(false)) != 0) {
						// System.out.println("ERROR");
						// System.exit(1);
						validInput = false;
						System.out.print("Invalid! Reenter number, spot already occupied.  -  ");
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
					System.out.print("Invalid!  Number must be 0 to 8, excluding taken spots.  -  ");
					break one;
				}
			}
		}
		return 1000;
	}

	public void enactAI(int move) {
		if (move == 1000) {
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

	public void spit() {
		System.out.println();
		System.out.println();
		System.out.println();
		System.out.println();
		System.out.println();
		System.out.println("Board Layout:");
		for (int i = 0; i < 9; i++) {
			System.out.print(" " + i);
			if (i == 2 || i == 5 || i == 8) {
				System.out.println();
			} else {
				System.out.print(" |");
			}
			if (i == 2 || i == 5) {
				System.out.println("---|---|---");
			}
		}
		/*
		 * for(int i1 = 0; i1 < 9; i1++){ System.out.println();
		 * System.out.println(space[i1].getCol() + ", " + space[i1].getRow() +
		 * " . . ." + space[i1].coord[0] + space[i1].coord[1] + " Team:" +
		 * space[i1].getTeam(true) + "(" + space[i1].getTeam(false) + ")"); if(r
		 * != 2){ r++; } else { r = 0; c++; } } System.out.println();
		 * System.out.println();
		 */
	}

	public boolean checkW() {
		if (aWin()) {
			win = true;
			return true;
		}
		return false;
	}

	public boolean checkTie() {
		if (avail.size() == 0) {
			tie = true;
			return true;
		}
		return false;
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

	private boolean repeat() {
		boolean valInp = false;
		boolean out = false;
		//main: while (!valInp) {
			one: while (!valInp) {
				sc = new Scanner(System.in);
				// sc.reset();
				System.out.print("Play again? 0 = yes, any other # = no.  Input: ");
				while (!sc.hasNextInt()) {
					System.out.print("Invalid!  Not a number. Try again.  -  ");
					sc.nextLine();
				}
				int in = sc.nextInt();
				if (in == 0) {
					valInp = true;

					return true;
				} else {
					// System.out.println("ERROR");
					// System.exit(1);
					return false;
				}
			}
		//}
		return out;
	}

}
