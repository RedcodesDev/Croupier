package net.statifybot.croupier.game.draw;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map.Entry;

import org.checkerframework.common.returnsreceiver.qual.This;

import java.util.Random;

import com.google.common.collect.Multimap;

import net.statifybot.croupier.game.rounds.Round;

public class Draw {

	Round round;
	int number;
	SelectedNumber num;
	long plr1Id, plr2Id, plr3Id, plr4Id;

	HashMap<Long, Integer> winners;
	HashMap<Long, Integer> loser;

	public Draw(Round round) {
		this.round = round;

	}

	public void selectNumber() {
		Random rnd = new Random();
		this.number = rnd.nextInt(37);
//		this.number = 17;
		this.num = new SelectedNumber(this.number);
		System.out.println("Selected Number: " + this.number);
		System.out.println("Color: " + this.num.getColor());
		System.out.println("Is Even: " + this.num.isEven());
		System.out.println("Even Raw: " + this.num.getEven());
	}

	public SelectedNumber getSelectedNumber() {
		return this.num;
	}
	
	public int getNumber() {
		return this.number;
	}

	public void retrieveWinners() {

		this.winners = new HashMap<Long, Integer>();
		this.loser = new HashMap<Long, Integer>();

		ArrayList<Integer> plr1 = new ArrayList<Integer>();
		this.plr1Id = 0;
		ArrayList<Integer> plr2 = new ArrayList<Integer>();
		this.plr2Id = 0;
		ArrayList<Integer> plr3 = new ArrayList<Integer>();
		this.plr3Id = 0;
		ArrayList<Integer> plr4 = new ArrayList<Integer>();
		this.plr4Id = 0;

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
				} else if (plr4Id == entry.getKey()) {
					plr4.add(selectedNum);
				} else {
					if (plr1.isEmpty()) {
						plr1Id = entry.getKey();
						plr1.add(selectedNum);
					} else if (plr2.isEmpty()) {
						plr2Id = entry.getKey();
						plr2.add(selectedNum);
					} else if (plr3.isEmpty()) {
						plr3Id = entry.getKey();
						plr3.add(selectedNum);
					} else if (plr4.isEmpty()) {
						plr4Id = entry.getKey();
						plr4.add(selectedNum);
					}
				}

				for (int plrNum : plr1) {
					if (plr1.size() == 1) {
						if (this.number == plrNum) {
							this.winners.put(plr1Id, 36);
						} else {
							this.winners.put(plr1Id, 0);
						}
					} else if (plr1.size() == 2) {
						if (this.number == plrNum) {
							this.winners.put(plr1Id, 18);
						} else {
							this.winners.put(plr1Id, 0);
						}
					} else if (plr1.size() == 3) {
						if (this.number == plrNum) {
							this.winners.put(plr1Id, 19);
						} else {
							this.winners.put(plr1Id, 0);
						}
					} else if (plr1.size() == 4) {
						if (this.number == plrNum) {
							this.winners.put(plr1Id, 9);
						} else {
							this.winners.put(plr1Id, 0);
						}
					} else if (plr1.size() == 5) {
						if (this.number == plrNum) {
							this.winners.put(plr1Id, 6);
						} else {
							this.winners.put(plr1Id, 0);
						}
					} else if (plr1.size() == 6) {
						if (this.number == plrNum) {
							this.winners.put(plr1Id, 3);
						} else {
							this.winners.put(plr1Id, 0);
						}
					}
				}

				for (int plrNum : plr2) {
					if (plr2.size() == 1) {
						if (this.number == plrNum) {
							this.winners.put(plr2Id, 35);
						} else {
							this.winners.put(plr2Id, 0);
						}
					} else if (plr2.size() == 2) {
						if (this.number == plrNum) {
							this.winners.put(plr2Id, 17);
						} else {
							this.winners.put(plr2Id, 0);
						}
					} else if (plr2.size() == 3) {
						if (this.number == plrNum) {
							this.winners.put(plr2Id, 11);
						} else {
							this.winners.put(plr2Id, 0);
						}
					} else if (plr2.size() == 4) {
						if (this.number == plrNum) {
							this.winners.put(plr2Id, 8);
						} else {
							this.winners.put(plr2Id, 0);
						}
					} else if (plr2.size() == 5) {
						if (this.number == plrNum) {
							this.winners.put(plr2Id, 5);
						} else {
							this.winners.put(plr2Id, 0);
						}
					} else if (plr2.size() == 6) {
						if (this.number == plrNum) {
							this.winners.put(plr2Id, 2);
						} else {
							this.winners.put(plr2Id, 0);
						}
					}
				}

				for (int plrNum : plr3) {
					if (plr3.size() == 1) {
						if (this.number == plrNum) {
							this.winners.put(plr3Id, 35);
						} else {
							this.winners.put(plr3Id, 0);
						}
					} else if (plr3.size() == 2) {
						if (this.number == plrNum) {
							this.winners.put(plr3Id, 17);
						} else {
							this.winners.put(plr3Id, 0);
						}
					} else if (plr3.size() == 3) {
						if (this.number == plrNum) {
							this.winners.put(plr3Id, 11);
						} else {
							this.winners.put(plr3Id, 0);
						}
					} else if (plr3.size() == 4) {
						if (this.number == plrNum) {
							this.winners.put(plr3Id, 8);
						} else {
							this.winners.put(plr3Id, 0);
						}
					} else if (plr3.size() == 5) {
						if (this.number == plrNum) {
							this.winners.put(plr3Id, 5);
						} else {
							this.winners.put(plr3Id, 0);
						}
					} else if (plr3.size() == 6) {
						if (this.number == plrNum) {
							this.winners.put(plr3Id, 2);
						} else {
							this.winners.put(plr3Id, 0);
						}
					}
				}

				for (int plrNum : plr4) {
					if (plr4.size() == 1) {
						if (this.number == plrNum) {
							this.winners.put(plr4Id, 35);
						} else {
							this.winners.put(plr4Id, 0);
						}
					} else if (plr4.size() == 2) {
						if (this.number == plrNum) {
							this.winners.put(plr4Id, 17);
						} else {
							this.winners.put(plr4Id, 0);
						}
					} else if (plr4.size() == 3) {
						if (this.number == plrNum) {
							this.winners.put(plr4Id, 11);
						} else {
							this.winners.put(plr4Id, 0);
						}
					} else if (plr4.size() == 4) {
						if (this.number == plrNum) {
							this.winners.put(plr4Id, 8);
						} else {
							this.winners.put(plr4Id, 0);
						}
					} else if (plr4.size() == 5) {
						if (this.number == plrNum) {
							this.winners.put(plr4Id, 5);
						} else {
							this.winners.put(plr4Id, 0);
						}
					} else if (plr4.size() == 6) {
						if (this.number == plrNum) {
							this.winners.put(plr4Id, 2);
						} else {
							this.winners.put(plr4Id, 0);
						}
					}
				}

			} catch (NumberFormatException ex) {

				if (entry.getValue().equals(this.num.getColor())) {
					if (winners.isEmpty()) {
						winners.put(entry.getKey(), 1);
					} else {
						winners.put(entry.getKey(), winners.get(entry.getKey()) + 1);
					}
				} else if (entry.getValue().equals(this.num.getColumn())) {
					if (winners.isEmpty()) {
						winners.put(entry.getKey(), 2);
					} else {
						winners.put(entry.getKey(), winners.get(entry.getKey()) + 2);
					}
				} else if (entry.getValue().equals(this.num.getRow())) {

					if (winners.isEmpty()) {
						winners.put(entry.getKey(), 2);
					} else {
						winners.put(entry.getKey(), winners.get(entry.getKey()) + 2);
					}
				} else if (entry.getValue().equals(this.num.getEven())) {
					if (!(this.number == 0)) {
						if (winners.isEmpty()) {
							winners.put(entry.getKey(), 1);
						} else {
							winners.put(entry.getKey(), winners.get(entry.getKey()) + 1);
						}
					} else {
						if(winners.isEmpty()) {
							winners.put(entry.getKey(), 0);
						}
					}
				} else if (entry.getValue().equals(this.num.getHalf())) {

					if (winners.isEmpty()) {
						winners.put(entry.getKey(), 1);
					} else {
						winners.put(entry.getKey(), winners.get(entry.getKey()) + 1);
					}
				} else {
					if (winners.isEmpty()) {
						winners.put(entry.getKey(), 0);
					}
				}

			}

		}

	}

	public Integer getChipsByWinner(long winnerId) {
		int chips = 0;
		if (this.winners.containsKey(winnerId)) {
			System.out.println("Won: " + this.winners.get(winnerId));
			chips += this.winners.get(winnerId);
		}
//
//		if (this.loser.containsKey(winnerId)) {
//			System.out.println("Lost: " + this.loser.get(winnerId));
//			chips -= this.loser.get(winnerId);
//		}
		return chips;
	}

	public HashMap<Long, Integer> getChips() {
		HashMap<Long, Integer> map = new HashMap<Long, Integer>();

		for (Entry<Long, Integer> entry : this.winners.entrySet()) {
			map.put(entry.getKey(), this.getChipsByWinner(entry.getKey()));
		}

		return map;

	}
}
