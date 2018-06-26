package com.rgfp.psd.logbook.domain;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;
import java.time.LocalDateTime;

@Entity
@Table(name = "notes")
public class Note {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long id;
    private String title;
    private LocalDateTime timestamp;
    @Column(length=10000)
    private String content;

    public void setTimestamp(LocalDateTime timestamp) {
        this.timestamp = timestamp;
    }

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public LocalDateTime getTimestamp() {
        return timestamp;
    }

    public String getContent() {
        return content;
    }

    public void setContent(String content) {
        this.content = content;
    }

    public String getSummary() {
        // not implemented
        String content;
        content = getContent();

        if (content.length() > 15) {
            content = content.substring(0, 15);
        }
        return content;
    }

    public Note clone(){
        Note noteClonada=new Note();
        noteClonada.setTitle(this.getTitle());
        noteClonada.setContent(this.getContent());

        LocalDateTime fechaClonada=LocalDateTime.now();

        if (this.getTimestamp() == null) {
            noteClonada.setTimestamp(fechaClonada);

        } else{

            if (this.getTimestamp().isAfter(fechaClonada)){
                noteClonada.setTimestamp(this.getTimestamp().plusDays(1));
            } else{
                noteClonada.setTimestamp(fechaClonada);
            }
        }

        return  noteClonada;
    }
}
