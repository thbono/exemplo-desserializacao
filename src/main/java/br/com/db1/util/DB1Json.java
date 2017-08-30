package br.com.db1.util;

import com.google.gson.Gson;

public class DB1Json {

	private static Gson gson = new Gson();

	public static String toJson(final Object object) {
	    return gson.toJson(object);
    }

    public static <T> T fromJson(final String json, final Class<T> classOfT) {
        return gson.fromJson(json, classOfT);
    }
	
}
