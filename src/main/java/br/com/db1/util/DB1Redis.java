package br.com.db1.util;

import redis.clients.jedis.Jedis;

public class DB1Redis {

	private static Jedis conexao = new Jedis("localhost");

	public static void set(final String key, final String value) {
	    System.out.println(String.format("Setting in Redis %s -> %s", key, value));
	    conexao.set(key, value);
	    conexao.expire(key, 30);
    }

    public static String get(final String key) {
	    return conexao.get(key);
    }
	
}
