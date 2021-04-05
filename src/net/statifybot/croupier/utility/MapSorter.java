package net.statifybot.croupier.utility;

import java.util.Collections;
import java.util.HashMap;
import java.util.LinkedHashMap;
import java.util.Map;
import java.util.stream.Collectors;

import net.dv8tion.jda.api.entities.User;

public class MapSorter {

	public static HashMap<String, Integer> sortStringMap(HashMap<String, Integer> map, int limit) {
		map = map.entrySet().stream().sorted(Collections.reverseOrder(Map.Entry.comparingByValue())).limit(limit)
				.collect(Collectors.toMap(Map.Entry::getKey, Map.Entry::getValue, (e1, e2) -> e2, LinkedHashMap::new));

		return map;
	}

	public static HashMap<Long, Integer> sortLongMap(HashMap<Long, Integer> map, int limit) {
		map = map.entrySet().stream().sorted(Collections.reverseOrder(Map.Entry.comparingByValue())).limit(limit)
				.collect(Collectors.toMap(Map.Entry::getKey, Map.Entry::getValue, (e1, e2) -> e2, LinkedHashMap::new));

		return map;
	}
	
	public static HashMap<User, Integer> sortUserMap(HashMap<User, Integer> map, int limit) {
		map = map.entrySet().stream().sorted(Collections.reverseOrder(Map.Entry.comparingByValue())).limit(limit)
				.collect(Collectors.toMap(Map.Entry::getKey, Map.Entry::getValue, (e1, e2) -> e2, LinkedHashMap::new));

		return map;
	}
}
