package com.engineeringDigest.journal.controller;


import com.engineeringDigest.journal.entity.JournalEntry;

import com.engineeringDigest.journal.exceptions.JournalNotFoundException;
import com.engineeringDigest.journal.service.JournalEntryService;
import lombok.AllArgsConstructor;
import lombok.RequiredArgsConstructor;
import org.bson.types.ObjectId;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.server.ResponseStatusException;

import java.time.LocalDateTime;
import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/journal")
@AllArgsConstructor
public class JournalEntryControllerV2 {

    private JournalEntryService journalEntryService;

    @GetMapping
    public List<JournalEntry> getAll(){
        return journalEntryService.getAll();
    }


    @PostMapping
    public JournalEntry createEntry(@RequestBody JournalEntry myEntry){
        myEntry.setDate(LocalDateTime.now());
        journalEntryService.saveEntry(myEntry);
        return myEntry;
    }

    @GetMapping("/{myId}")
    public ResponseEntity<JournalEntry> getbyId(@PathVariable ObjectId myId){

        Optional<JournalEntry> journalEntry = journalEntryService.findById(myId);
        if (journalEntry.isPresent()){
            return new ResponseEntity<>(journalEntry.get(),HttpStatus.OK);
        }
        return new ResponseEntity<>(HttpStatus.NOT_FOUND);
    }

    @DeleteMapping("/{myId}")
    public JournalEntry deletebyId(@PathVariable ObjectId myId){
        JournalEntry deleted = journalEntryService.findById(myId).orElseThrow(()->new JournalNotFoundException("Journal Not Found !"));
        journalEntryService.deleteById(myId);
        return deleted;
    }

    @PutMapping("/{id}")
    public JournalEntry updatejournalId(@PathVariable("id") ObjectId myId, @RequestBody JournalEntry newEntry){

        JournalEntry old = journalEntryService.findById(myId).orElse(null);

        if (old!=null){
            old.setTitle(newEntry.getTitle() != null && !newEntry.getTitle().equals(" ")? newEntry.getTitle() : old.getTitle());
            old.setContent(newEntry.getContent() != null && !newEntry.getContent().equals(" ")? newEntry.getContent() : old.getContent());
        }
        journalEntryService.saveEntry(old);
        return old;

    }

}
