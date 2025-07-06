package com.engineeringDigest.journal.repository;

import com.engineeringDigest.journal.entity.JournalEntry;
import com.engineeringDigest.journal.entity.User;
import org.bson.types.ObjectId;
import org.springframework.data.mongodb.repository.MongoRepository;

public interface UserRepository extends MongoRepository<User, ObjectId>{

    User findByUserName(String username);
}
