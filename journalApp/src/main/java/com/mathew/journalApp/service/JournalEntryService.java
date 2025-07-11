package com.mathew.journalApp.service;

import com.mathew.journalApp.entity.JournalEntry;
import com.mathew.journalApp.entity.User;
import com.mathew.journalApp.repository.JournalEntryRepository;
import org.bson.types.ObjectId;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;

import java.time.LocalDateTime;
import java.util.List;
import java.util.Optional;

@Component
public class JournalEntryService {
@Autowired
    private JournalEntryRepository journalEntryRepository;
@Autowired
    private UserService userService;

   @Transactional
   public void saveEntry(JournalEntry journalEntry, String userName)
    {
        try {
            User user = userService.findByUserName(userName);
            journalEntry.setDate(LocalDateTime.now());
            JournalEntry saved = journalEntryRepository.save(journalEntry);
            user.getJournalEntries().add(saved);
            userService.saveEntry(user);
        } catch (Exception e) {
            System.out.print(e);
            throw new RuntimeException("An error occured while using entry",e);

        }

    }

    public void saveEntry(JournalEntry journalEntry)
    {
        journalEntryRepository.save(journalEntry);
    }
    public List<JournalEntry> getall()
    {
        return journalEntryRepository.findAll();
    }
    public Optional<JournalEntry> findById(ObjectId id)
    {
        return journalEntryRepository.findById(id);
    }
    public void deleteById(ObjectId id, String userName)
    {
        User user = userService.findByUserName(userName);
        user.getJournalEntries().removeIf(x -> x.getId().equals(id));
        userService.saveEntry(user);
        journalEntryRepository.deleteById(id);
    }
}
