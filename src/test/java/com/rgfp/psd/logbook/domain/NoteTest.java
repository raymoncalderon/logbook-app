package com.rgfp.psd.logbook.domain;

import org.junit.Before;
import org.junit.Test;

import static org.junit.Assert.*;

public class NoteTest {

    // your tests here
    private Note note;
    private String contenido;
    //

    @Before
    public void starup(){
        note=new Note();
        contenido="123456789012345678901234567890";
        note.setContent(contenido);
    }

    @Test
    public void validaGlosaNoNula(){
        String content;

        content = note.getSummary();

        assertNotNull(content);
    }

    @Test
    public void devulveQuinceCaracteres(){
        String content;
        content=note.getSummary();
        int largoPalabra = content.length();

        assertEquals(largoPalabra, 15);
        assertEquals(contenido.substring(0, 15), content);

    }

    @Test
    public void devulveMenosDeQuinceCaracteres(){
        String content;
        String contenido2="1234567890";
        note.setContent(contenido2);
        content=note.getSummary();

        int largoPalabra = content.length();

        assertTrue(largoPalabra<15);
        assertEquals(contenido2, content);
    }

}