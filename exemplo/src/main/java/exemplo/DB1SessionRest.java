package exemplo;

import java.util.HashMap;
import java.util.Map;
import java.util.UUID;

import util.DB1Jedis;
import static util.DB1Gson.GSON;

public class DB1SessionRest extends DB1Session{

	private String id = UUID.randomUUID().toString();
	private Map<String, Object> map = new HashMap<String, Object>();
	
	//private UsuarioConsignet usuarioConsignet;
	
	@Override
	public void put(String key, Object value) {
		//como ficaria a minha ideia.
		//if(key == "usuario-consignet")
		//	this.usuarioConsignet = (UsuarioConsignet) value;
		this.map.put(key, value);
		DB1Jedis.conexao.set(this.id, GSON.toJson(this));
	}

	@Override
	public void remove(String key) {
		this.map.remove(key);
		DB1Jedis.conexao.set(this.id, GSON.toJson(this));
	}

	@Override
	public Object get(String key) {
		return this.map.get(key);
	}

	public String getId() {
		return this.id;
	}
	
	public Map<String, Object> getMap() {
		return this.map;
	}
}
