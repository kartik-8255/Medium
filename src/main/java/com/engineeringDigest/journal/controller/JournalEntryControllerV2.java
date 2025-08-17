package com.engineeringDigest.journal.controller;


import com.engineeringDigest.journal.entity.JournalEntry;

import com.engineeringDigest.journal.entity.User;
import com.engineeringDigest.journal.exceptions.JournalNotFoundException;
import com.engineeringDigest.journal.service.JournalEntryService;
import com.engineeringDigest.journal.service.UserService;
import lombok.AllArgsConstructor;
import org.bson.types.ObjectId;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/journal")
@AllArgsConstructor
public class JournalEntryControllerV2 {

    private JournalEntryService journalEntryService;

    @Autowired
    private UserService userService;

    @GetMapping("/{userName}")
    public ResponseEntity<?> getAll(@PathVariable String userName){
        User user = userService.findByUserName(userName);
        List<JournalEntry> all =  user.getJournalEntries();
        if (all != null && !all.isEmpty()){
            return new ResponseEntity<>(all, HttpStatus.OK);
        }
        return new ResponseEntity<>(HttpStatus.NOT_FOUND);
    }



    @PostMapping("/{user}")
    public JournalEntry createEntry(@RequestBody JournalEntry myEntry, @PathVariable("user") String username){

        journalEntryService.saveEntry(myEntry,username);
        return myEntry;
    }
//
//    @GetMapping("/{myId}")
//    public ResponseEntity<JournalEntry> getbyId(@PathVariable ObjectId myId){
//
//        Optional<JournalEntry> journalEntry = journalEntryService.findById(myId);
//        if (journalEntry.isPresent()){
//            return new ResponseEntity<>(journalEntry.get(),HttpStatus.OK);
//        }
//        return new ResponseEntity<>(HttpStatus.NOT_FOUND);
//    }

    @DeleteMapping("/{username}/{myId}")
    public ResponseEntity<?> deletebyId(@PathVariable ObjectId myId, @PathVariable String username){
        journalEntryService.deleteById(myId,username);
        return new ResponseEntity<>(HttpStatus.OK);
    }

    @PutMapping("/{username}/{id}")
    public JournalEntry updatejournalId(@PathVariable("id") ObjectId myId,
                                        @RequestBody JournalEntry newEntry,
                                        @PathVariable String username){

        JournalEntry old = journalEntryService.findById(myId).orElse(null);

        if (old!=null){
            old.setTitle(newEntry.getTitle() != null && !newEntry.getTitle().equals(" ")? newEntry.getTitle() : old.getTitle());
            old.setContent(newEntry.getContent() != null && !newEntry.getContent().equals(" ")? newEntry.getContent() : old.getContent());
        }
        journalEntryService.saveEntry(old);
        return old;

    }

}
