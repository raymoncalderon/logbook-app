package com.rgfp.psd.logbook.domain;

import org.junit.Before;
import org.junit.Test;

import java.time.LocalDate;
import java.time.LocalDateTime;

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

    @Test
    public void devuelveObjetoNoteConNombreyContenido(){
      Note note = new Note();
      note.setTitle("Title Test1");
      note.setContent("Content Test1");

      Note noteClone=note.clone();

      assertEquals(note.getTitle(), noteClone.getTitle());
      assertEquals(note.getContent(), noteClone.getContent());
    }

    @Test
    public void devuelveObjetoNoteFechaMayor(){
        Note note = new Note();
        note.setTimestamp(LocalDateTime.now().minusDays(1));

        Note noteClone=note.clone();

        assertTrue(noteClone.getTimestamp().isAfter(note.getTimestamp()));
    }

    @Test
    public void devuelveObjetoNoteFechaMenor(){
        Note note = new Note();
        note.setTimestamp(LocalDateTime.now().plusDays(1));

        Note noteClone=note.clone();

        assertTrue(noteClone.getTimestamp().isAfter(note.getTimestamp()));

    }

    @Test
    public void devuelveObjetoNotenoNulo(){
        Note note = new Note();

        assertNotNull(note.clone());
    }


}