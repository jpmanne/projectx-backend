package com.at.projx.util;

import java.util.ArrayList;
import java.util.Collection;
import java.util.HashMap;
import java.util.Map;
import java.util.Set;

public class MultiValueMap<K, V> {
	protected Map<K, ArrayList<V>> map = new HashMap<K, ArrayList<V>>();

	public void put(K key, V value) {
		ArrayList<V> list = map.get(key);
		if (list == null) {
			list = new ArrayList<V>();
			map.put(key, list);
		}
		list.add(value);
	}

	public ArrayList<V> get(K key) {
		return map.get(key);
	}

	public Set<K> keySet() {
		return map.keySet();
	}

	public Collection<ArrayList<V>> valueLists() {
		return map.values();
	}

	public Set<Map.Entry<K, ArrayList<V>>> entrySet() {
		return map.entrySet();
	}

	public boolean containsKey(K key) {
		return map.containsKey(key);
	}

	public int sizeKeys() {
		return map.size();
	}

	public int sizeValues() {
		int sum = 0;
		for (ArrayList<V> list : map.values()) {
			sum += list.size();
		}
		return sum;
	}

	public boolean isEmpty() {
		return map.isEmpty();
	}

	public Map<K, ArrayList<V>> getMap() {
		return map;
	}
}