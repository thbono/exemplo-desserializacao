package exemplo;

import util.DB1Jedis;
import static util.DB1Gson.GSON;

public class DB1SessionRestResolver {

	public DB1SessionRest getSession(String id){
		String sessaoDesserializada = DB1Jedis.conexao.get(id);
		if (sessaoDesserializada == null){
			DB1SessionRest novaSessao = new DB1SessionRest();
			DB1Jedis.conexao.set(novaSessao.getId(), GSON.toJson(novaSessao));
			//acredito que este seria mesmo o Json certo.
			sessaoDesserializada = DB1Jedis.conexao.get(novaSessao.getId());
		}
		return GSON.fromJson(sessaoDesserializada, DB1SessionRest.class);
	}
	
}
