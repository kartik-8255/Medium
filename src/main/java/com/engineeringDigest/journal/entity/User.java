package com.engineeringDigest.journal.entity;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.NonNull;
import lombok.Setter;
import org.bson.types.ObjectId;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.index.Indexed;
import org.springframework.data.mongodb.core.mapping.DBRef;
import org.springframework.data.mongodb.core.mapping.Document;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;


@Document(collection = "Users")
@Getter
@Setter

public class User {

    @Id
    private ObjectId id;
    @NonNull
    @Indexed(unique = true)
    private String userName;
    @NonNull
    private String password;
    @DBRef
    private List <JournalEntry> journalEntries= new ArrayList<>();
}
