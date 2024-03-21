package com.example.dma;

public class Note {
    String title;  // Название заметки
    String description;  // Описание заметки
    String placeInWorld;  // Место на карте для заметки
    String timeNote;  // Дата, к которой привязана заметка

    public Note(String title, String description, String placeInWorld, String timeNote) {
        this.title = title;
        this.description = description;
        this.placeInWorld = placeInWorld;
        this.timeNote = timeNote;
    }

    public String getTitle() {
        return title;
    }
    public void setTitle(String title) {
        this.title = title;
    }

    public String getDescription() {
        return description;
    }
    public void setDescription(String description) {
        this.description = description;
    }

    public String getPlaceInWorld() {
        return placeInWorld;
    }
    public void setPlaceInWorld(String placeInWorld) {
        this.placeInWorld = placeInWorld;
    }

    public String getTimeNote() {
        return timeNote;
    }
    public void settTmeNote(String timeNote) {
        this.timeNote = timeNote;
    }
}
