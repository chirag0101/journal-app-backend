package net.engineeringdigest.journalApp.entity;

import lombok.Setter;
import org.bson.types.ObjectId;

import javax.persistence.Id;

public class JournalEntry {
    @Id
    private ObjectId _id;
    @Setter
    private String name;

    public ObjectId getId() {
        return _id;
    }

    public void setId(ObjectId id) {
        this._id = id;
    }

}
