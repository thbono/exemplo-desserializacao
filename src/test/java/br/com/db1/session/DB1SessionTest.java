package br.com.db1.session;

import br.com.db1.model.UsuarioConsignet;
import org.junit.Test;

import java.util.Date;

import static org.junit.Assert.*;

public class DB1SessionTest {

    @Test
    public void test() {
        final DB1SessionRestResolver resolver = new DB1SessionRestResolver();

        DB1SessionRest session = resolver.getSession("");
        final String sessionId = session.getId();

        session.put("exemplo-string", "Teste de String");
        session.put("exemplo-data", new Date());
        session.put("exemplo-int", 10);
        session.put("exemplo-double", 10.5);
        session.put("exemplo-object", new UsuarioConsignet("1", "Tiago Bono"));

        session = resolver.getSession(sessionId);

        assertEquals("Teste de String", session.get("exemplo-string"));
        assertEquals(10, session.get("exemplo-int"));
        assertEquals(10.5, session.get("exemplo-double"));

        assertTrue(session.get("exemplo-data") instanceof Date);
        assertEquals(new Date().getDay(), ((Date) session.get("exemplo-data")).getDay());

        assertTrue(session.get("exemplo-object") instanceof UsuarioConsignet);
        assertEquals("1", ((UsuarioConsignet) session.get("exemplo-object")).getId());
        assertEquals("Tiago Bono", ((UsuarioConsignet) session.get("exemplo-object")).getNome());

        session.remove("exemplo-data");

        session = resolver.getSession(sessionId);

        assertNull(session.get("exemplo-data"));
    }

}
