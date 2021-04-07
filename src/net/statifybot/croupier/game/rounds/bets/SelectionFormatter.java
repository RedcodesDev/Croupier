package net.statifybot.croupier.game.rounds.bets;

import java.util.HashMap;
import java.util.Map.Entry;

import com.google.common.collect.ArrayListMultimap;
import com.google.common.collect.Multimap;

public class SelectionFormatter {

	Multimap<Long, String> map;
	String text;

	public SelectionFormatter(Multimap<Long, String> map) {
		this.map = map;

		this.text = "";
		for (Entry<Long, String> entry : this.map.entries()) {
			this.text = text + entry.getKey() + "_" + entry.getValue() + ";";
		}

	}

	public SelectionFormatter(String text) {
		this.text = text;
		this.map = ArrayListMultimap.create();

		String[] args = this.text.split(";");
		if (!text.equals("")) {
			for (int i = 0; i < args.length; i++) {
				String[] splitter = args[i].split("_");
				this.map.put(Long.valueOf(splitter[0]), splitter[1]);
			}
		}

	}

	public String getAsText() {
		return this.text;
	}

	public Multimap<Long, String> getAsMap() {
		return this.map;
	}

}
