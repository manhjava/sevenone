package com.sevenone.sevenfb.util;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;

import com.google.gson.Gson;
import com.sevenone.sevenfb.model.Team;
import com.sevenone.sevenfb.model.User;

public class JsonUtil {
	
	protected static final transient Log log = LogFactory.getLog(JsonUtil.class);

	public static String userToJson(User user) {
		
		if(user == null) {
			return "{\"userIsNull\":\"true\"}";
		}

		Gson gson = new Gson();
		String gsonstr = "{\"userIsNull\":\"true\"}";
		try {
			gsonstr = gson.toJson(user);
		} catch (Exception e) {
			log.error("Exception: error when json user");
		}
		return gsonstr;
	}

	public static String teamToJson(Team team) {
		if(team == null) {
			return "{\"teamIsNull\":\"true\"}";
		}
		team.setPlayers(null);//Offending field: players
		Gson gson = new Gson();
		
		String gsonstr = "{\"teamIsNull\":\"true\"}";
		try {
			gsonstr = gson.toJson(team);
		} catch (Exception e) {
			log.error("Exception: error when json team");
		}
		return gsonstr;
	}

}
