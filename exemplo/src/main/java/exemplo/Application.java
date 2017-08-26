package exemplo;

import model.UsuarioConsignet;

public class Application {

	public static void main(String[] args) {
		
		DB1SessionRestResolver resolver = new DB1SessionRestResolver();
		
		DB1SessionRest sessionCriada = resolver.getSession("");
		
		String idSessao = sessionCriada.getId();
		
		UsuarioConsignet user = new UsuarioConsignet("123", "LUCAS");
		
		sessionCriada.put("usuario-consignet", user);
		
		DB1SessionRest sessionRecuperada = resolver.getSession(idSessao);
		
		//Ao recuperar os objetos contidos dentro do map, eles vem como um LinkedHashTree, possuindo a chave como nome 
		//dos atributos e valor como valores dos atributos da classe, a intenção era recuperar objeto do tipo UsuarioConsignet.
		
		// a soluçao que pensei seria alterar a maneira como o put funciona, e ao inves de jogar no map, 
		// jogar em atributos da classe, porém acredito que seria muito trabalhoso.
		
		Object userRecuperado = sessionRecuperada.get("usuario-consignet");
				
		System.out.println(userRecuperado);
		
	}
	
}
