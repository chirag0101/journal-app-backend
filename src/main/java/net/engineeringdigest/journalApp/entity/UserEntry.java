package net.engineeringdigest.journalApp.entity;

import com.mongodb.lang.NonNull;
import lombok.Data;
import org.springframework.data.mongodb.core.index.Indexed;
import org.springframework.data.mongodb.core.mapping.DBRef;
import org.springframework.data.mongodb.core.mapping.Document;

import javax.persistence.Id;
import java.util.ArrayList;
import java.util.List;

@Data
@Document(collection = "users")

public class UserEntry {

    @Id
    private String _id;
    @Indexed(unique = true)

    @NonNull
    private String userName;

    @NonNull
    private String password;

    @DBRef
    List<JournalEntry> journalEntries=new ArrayList<>();
}
