package it.feio.android.musixmatchapp.models;

import com.google.gson.internal.LinkedTreeMap;

import java.util.List;


public class LinkedTreeMapWrapper {

	private final LinkedTreeMap linkedTreeMap;

	public LinkedTreeMapWrapper(LinkedTreeMap linkedTreeMap) {
		this.linkedTreeMap = linkedTreeMap;
	}

	public List<LinkedTreeMap> getTracks() {
		return (List) get("message.body").get("track_list");
	}

	public LinkedTreeMap getTrackLyrics() {
		return (LinkedTreeMap) get("message.body").get("lyrics");
	}

	public LinkedTreeMap get(String mapPath) {
		String[] mapPathElement = mapPath.split("\\.");
		LinkedTreeMap map = linkedTreeMap;
		for (String s : mapPathElement) {
			map = (LinkedTreeMap) map.get(s);
		}
		return map;
	}

}
