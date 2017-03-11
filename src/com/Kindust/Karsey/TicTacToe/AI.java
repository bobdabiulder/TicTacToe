package com.Kindust.Karsey.TicTacToe;

import java.security.SecureRandom;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Random;

public class AI {
	Space[] space;
	ArrayList<Integer> availible;
	boolean winReady;
	int winAt;
	int[][] winpat = { { 0, 1, 2 }, { 3, 4, 5 }, { 6, 7, 8 }, { 0, 3, 6 }, { 1, 4, 7 }, { 2, 5, 8 }, { 0, 4, 8 },
			{ 2, 4, 6 } };

	public AI(Space[] space, ArrayList<Integer> avail) {
		this.space = space;
		this.availible = avail;
	}

	public int aiMove(int prev, boolean first) {
		int result = move(first); // = move();

		return result;
	}

	private int move(boolean first) {
		int best = Integer.MAX_VALUE;
		int worst = Integer.MIN_VALUE;
		if(availible.size() == 0){
			return 1000;
		}
			int r = new SecureRandom().nextInt(availible.size());
		System.out.print("22222sdf   ");
		// System.out.println(availible.get(0) + " " +availible.get(1) + " "
		// +availible.get(2) + " " +availible.get(3) + " " +availible.get(4) + "
		// " );
		// return availible.get(r);
		int out = minimax();
		if (first) {
			first = false;
			System.out.println("First move!  Must pick " + r);
			return availible.get(r);
		} else if (out > 8) {
			System.out.println("Code 0; Unable to decide, picking: " + r);
			return availible.get(r);
		} else if (!availible.contains(out)) {
			System.out.println("Code 1; Unable to decide, picking: " + r);
			return availible.get(r);
		}
		System.out.println("Going at " + out);
		return out;
	}

	private int minimax() {
		// ArrayList<Integer>[]
		winReady = false;
		int[] corresp = convert(availible);
		int[] scores = new int[availible.size()];
		@SuppressWarnings("unchecked")
		int[][] re = new int[corresp.length][];

		for (int i = 0; i < corresp.length; i++) {
			scores[i] = 0;
		}
		/**
		 * This for statement cycles through each potential win condition. If
		 * the conditions are met, assign values to each space # based on
		 * priority (see below)
		 * 
		 * Each if statement checks: If two of three spots needed to win are
		 * owned by the same team. If they are, someone will win next move. If
		 * the other person will, stop them. If you will, forget them! Win
		 * already! If the remaining spot is empty. This prevents attempting to
		 * stop an already blocked move. If one of the two taken spots needed to
		 * win is unowned. If it is, both of them are unowned since both spots
		 * are already equal. This prevents false positives.
		 * 
		 * If all of the above conditions are met, set the score at the
		 * corresponding index to the place to 10, if it will stop them, or 100
		 * if you will win.
		 **/
		for (int i = 0; i < winpat.length; i++) {
			System.out.println();
			System.out.println(
					"Score assidd " + space[winpat[i][0]].getTeam(false) + " " + space[winpat[i][1]].getTeam(false)
							+ " " + space[winpat[i][2]].getTeam(false) + " /Team:" + space[i].getTeam(false));
			// for(int i1 = 0; i1 < winpat[i].length; i1++){
			if (Integer.parseInt(space[winpat[i][0]].getTeam(false)) == Integer.parseInt(space[winpat[i][1]].getTeam(false)) && Integer.parseInt(space[winpat[i][2]].getTeam(false)) == 0
						&& Integer.parseInt(space[winpat[i][0]].getTeam(false)) != 0) {
				if (Integer.parseInt(space[winpat[i][0]].getTeam(false)) == 1) {
					scores[availible.indexOf(winpat[i][2])] = 10;
					System.out.println("Score assigned 10");
				} else if (Integer.parseInt(space[winpat[i][0]].getTeam(false)) == 2) {
					scores[availible.indexOf(winpat[i][2])] = 100;
					System.out.println("Score assigned 100, breaking!");
					System.out.println();
					winReady = true;
					winAt = winpat[i][2];
					break;
				}
				System.out.println("Score assigned");

			} else if (Integer.parseInt(space[winpat[i][1]].getTeam(false)) == Integer.parseInt(space[winpat[i][2]].getTeam(false)) && Integer.parseInt(space[winpat[i][0]].getTeam(false)) == 0
						&& Integer.parseInt(space[winpat[i][1]].getTeam(false)) != 0) {
				if (Integer.parseInt(space[winpat[i][1]].getTeam(false)) == 1) {
					scores[availible.indexOf(winpat[i][0])] = 10;
					System.out.println("Score assigned 10");
				} else if (Integer.parseInt(space[winpat[i][1]].getTeam(false)) == 2) {
					scores[availible.indexOf(winpat[i][0])] = 100;
					System.out.println("Score assigned 100, breaking!");
					System.out.println();
					winReady = true;
					winAt = winpat[i][0];
					break;
				}
				System.out.println("Score assigned");

			} else if (Integer.parseInt(space[winpat[i][0]].getTeam(false)) == Integer.parseInt(space[winpat[i][2]].getTeam(false)) && Integer.parseInt(space[winpat[i][1]].getTeam(false)) == 0
						&& Integer.parseInt(space[winpat[i][0]].getTeam(false)) != 0) {
				if (Integer.parseInt(space[winpat[i][2]].getTeam(false)) == 1) {
					scores[availible.indexOf(winpat[i][1])] = 10;
					System.out.println("Score assigned 10");
				} else if (Integer.parseInt(space[winpat[i][2]].getTeam(false)) == 2) {
					scores[availible.indexOf(winpat[i][1])] = 100;
					System.out.println("Score assigned 100, breaking!");
					System.out.println();
					winReady = true;
					winAt = winpat[i][1];
					break;
				}
				System.out.println("Score assigned");
			}

			// }
		}

		// ArrayList<Integer>[] moves = new ArrayList[scores.length];
		// ArrayList<ArrayList<Integer>> moves = new
		// ArrayList<ArrayList<Integer>>();

		int[][] out = new int[scores.length][];
		for (int i = 0; i < scores.length; i++) {
			int[] in = { scores[i], corresp[i] };
			out[i] = in;
		}

		boolean done = false;
		int fin = 0;
		if (!winReady) {
			for (int i = 0; i < out.length; i++) {
				if (out[i][0] == 100) {
					fin = out[i][1];
					System.out.println("Found win!  Spot: " + fin);
					break;
				} else if (out[i][0] == 10) {
					fin = out[i][1];
					System.out.println("Must stop O from winning!  Spot: " + fin);
					break;
				}
			}
		} else if(winReady){
			fin = winAt;
			System.out.println("Suck that!  Moving: " + fin);
		}
		if (!availible.contains(fin)) {
			return 1000;
		}
		// Arrays.sort(out);
		return fin;
	}

	/////////////////////////////

	private int[] convert(ArrayList<Integer> ar) {
		int[] re = new int[ar.size()];

		for (int i = 0; i < ar.size(); i++) {
			re[i] = (int) ar.get(i);
		}

		return re;
	}

	private ArrayList<Integer> convertB(int[] ar) {
		ArrayList<Integer> re = new ArrayList<Integer>();

		for (int i = 0; i < ar.length; i++) {
			re.add(ar[i]);
		}

		return re;
	}
}
