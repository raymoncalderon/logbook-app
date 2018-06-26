package com.rgfp.psd.logbook.service;

import com.rgfp.psd.logbook.domain.Note;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.annotation.PostConstruct;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Optional;

@Service
public class NoteService {

    @Autowired
    private NoteRepository noteRepository;

    private List<Note> allNotes;
    private HashMap<String, Integer> dictionary;

    @PostConstruct
    public void syncAllNotes() {
        this.allNotes = this.findAll();
        this.updateDictionary();
    }

    public List<Note> findAll() {
        List<Note> noteList = (List<Note>) noteRepository.findAll();
        this.allNotes = noteList;
        return noteList;

    }

    public Optional<Note> findOne(Long id) {
        return noteRepository.findById(id);
    }

    public void saveNote(Note note) {
        note.setTimestamp(LocalDateTime.now());
        noteRepository.save(note);
        this.syncAllNotes();
    }

    public void deleteNote(Long id) {
        noteRepository.deleteById(id);
        this.syncAllNotes();
    }

    public List<String> getRepeatedWords() {

        // not implemented
        return new ArrayList<>();

    }

    private void updateDictionary() {
        dictionary = new HashMap<>();
        String unwantedCharacters = "[,|.|:|?|!]";

        for(Note note : allNotes) {
            for (String word: note.getContent().toLowerCase().replaceAll(unwantedCharacters, "").split(" ")) {
                if (dictionary.containsKey(word)) {
                    dictionary.replace(word, dictionary.get(word) + 1);
                } else {
                    dictionary.put(word, 1);
                }
            }
        }
    }

    List<Note> getAllNotes() {
        return allNotes;
    }

    public void setNoteRepository(NoteRepository noteRepository) {
        this.noteRepository = noteRepository;
    }

    public void cloneNote(Long id){
        Note note = this.findOne(id).get();
        Note clonedNote = note.clone();
        this.saveNote(clonedNote);
    }
}