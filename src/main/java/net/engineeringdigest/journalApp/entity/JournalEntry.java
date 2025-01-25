package net.engineeringdigest.journalApp.entity;

import org.bson.types.ObjectId;

import javax.persistence.Id;

public class JournalEntry {
    @Id
    private ObjectId _id;
    private String name;
    private String title;

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public ObjectId getId() {
        return _id;
    }

    public void setId(ObjectId id) {
        this._id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }
}
