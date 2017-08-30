package br.com.db1.session;

public abstract class DB1Session {

	public abstract void put(String key, Object value);

	public abstract void remove(String key);
	
	public abstract Object get(String key);
	
}
