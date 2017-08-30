package br.com.db1.session;

public class DB1SessionRestResolver {

	public DB1SessionRest getSession(final String id) {
		final DB1SessionRest session = DB1SessionRest.getSessionOrCreateNew(id);
		System.out.println(String.format("Session resolved for id [%s] - %s", id, session));
	    return session;
	}
	
}
