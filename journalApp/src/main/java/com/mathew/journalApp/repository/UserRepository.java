package com.mathew.journalApp.repository;

import com.mathew.journalApp.entity.JournalEntry;
import com.mathew.journalApp.entity.User;
import org.bson.types.ObjectId;
import org.springframework.data.mongodb.repository.MongoRepository;

public interface UserRepository extends MongoRepository<User, ObjectId> {
User findByUserName(String username);
}
