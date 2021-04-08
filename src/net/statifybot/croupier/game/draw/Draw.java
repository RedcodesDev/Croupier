package net.statifybot.croupier.game.draw;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map.Entry;
import java.util.Random;

import com.google.common.collect.Multimap;

import net.statifybot.croupier.game.rounds.Round;

public class Draw {

	Round round;
	int number;

	public Draw(Round round) {
		this.round = round;

	}

	public void selectNumber() {
		Random rnd = new Random();
		this.number = rnd.nextInt(37);
	}

	public void retrieveWinners() {

		HashMap<Long, Integer> winners = new HashMap<Long, Integer>();
		HashMap<Long, Integer> loser = new HashMap<Long, Integer>();

		ArrayList<Integer> plr1 = new ArrayList<Integer>();
		long plr1Id = 0;
		ArrayList<Integer> plr2 = new ArrayList<Integer>();
		long plr2Id = 0;
		ArrayList<Integer> plr3 = new ArrayList<Integer>();
		long plr3Id = 0;
		ArrayList<Integer> plr4 = new ArrayList<Integer>();
		long plr4Id = 0;

		Multimap<Long, String> map = this.round.getBetMap();

		for (Entry<Long, String> entry : map.entries()) {

			try {
				int selectedNum = Integer.valueOf(entry.getValue());

				if (plr1Id == entry.getKey()) {
					plr1.add(selectedNum);
				} else if (plr2Id == entry.getKey()) {
					plr2.add(selectedNum);
				} else if (plr3Id == entry.getKey()) {
					plr3.add(selectedNum);
				} else if(plr4Id == entry.getKey()) {
					plr4.add(selectedNum);
				} else {
					if(plr1.isEmpty()) {
						plr1Id = entry.getKey();
						plr1.add(selectedNum);
					} else if(plr2.isEmpty()) {
						plr2Id = entry.getKey();
						plr2.add(selectedNum);
					} else if(plr3.isEmpty()) {
						plr3Id = entry.getKey();
						plr3.add(selectedNum);
					} else if(plr4.isEmpty()) {
						plr4Id = entry.getKey();
						plr4.add(selectedNum);
					}
				}
				
				

				if (selectedNum == this.number) {
					if (winners.containsKey(entry.getKey())) {
						winners.put(entry.getKey(), winners.get(entry.getKey()) + 35);
					} else {
						winners.put(entry.getKey(), 35);
					}
				}

			} catch (NumberFormatException ex) {
				
			}

		}

	}
}
