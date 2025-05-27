//package com.engineeringDigest.journal.controller;
//
//
//import com.engineeringDigest.journal.entity.JournalEntry;
//import org.springframework.web.bind.annotation.*;
//
//import java.util.ArrayList;
//import java.util.HashMap;
//import java.util.List;
//import java.util.Map;
//
//@RestController
//@RequestMapping("/_journal")
//public class JournalEntryController {
//
//    private Map<Long , JournalEntry> journalenteries = new HashMap<>();
//
//    @GetMapping
//    public List<JournalEntry> getAll(){
//        return new ArrayList<>(journalenteries.values());
//    }
//
//
//    @PostMapping
//    public boolean createEntry(@RequestBody JournalEntry myEntry){
//        journalenteries.put(myEntry.getId(),myEntry);
//        return true;
//    }
//
//    @GetMapping("/id/{myId}")
//    public JournalEntry getbyId(@PathVariable long myId){
//        return journalenteries.get(myId);
//    }
//
//    @DeleteMapping("/id/{myId}")
//    public JournalEntry deletebyId(@PathVariable long myId){
//        return journalenteries.remove(myId);
//    }
//
//    @PutMapping("/id/{id}")
//    public JournalEntry updatejournlaId(@RequestBody JournalEntry myEntry, @PathVariable Long id){
//        return journalenteries.put(id,myEntry);
//    }
//
//}
