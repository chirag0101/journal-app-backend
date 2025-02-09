package net.engineeringdigest.journalApp.controller;

import net.engineeringdigest.journalApp.entity.JournalEntry;
import net.engineeringdigest.journalApp.entity.UserEntry;
import net.engineeringdigest.journalApp.services.JournalEntryService;
import net.engineeringdigest.journalApp.services.UserEntryService;
import org.bson.types.ObjectId;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/journal")
public class JournalEntryController {

  @Autowired
  private JournalEntryService journalEntryService;
  @Autowired
  private UserEntryService userEntryService;

  @PostMapping("/{userName}")
  public ResponseEntity<?> createJournal(@PathVariable String userName,@RequestBody JournalEntry journalEntry){
    journalEntryService.createNewJournal(journalEntry,userName);
    return new ResponseEntity<>(HttpStatus.OK);
  }

  @GetMapping
  public ResponseEntity<?> showUser(){
    List<JournalEntry> journalEntry=journalEntryService.viewUsers();
    if(journalEntry!=null &&!journalEntry.isEmpty()){
      return new ResponseEntity<>(journalEntry,HttpStatus.OK);
    }
      return new ResponseEntity<>(HttpStatus.NOT_FOUND);
  }

  @GetMapping("/{userName}")
  public ResponseEntity<?> showUserById(@PathVariable String userName){
    try{
      UserEntry user=userEntryService.findByUserName(userName);
      List<JournalEntry> journalEntries=user.getJournalEntries();
      if(journalEntries!=null && !journalEntries.isEmpty()){
        return new ResponseEntity<>(journalEntries,HttpStatus.OK);
      }
    }catch (Exception e){
      System.out.println(e);
    }
    return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
  }

  @DeleteMapping("/{userName}/{id}")
  public ResponseEntity<?> deleteUserById(@PathVariable String userName, @PathVariable ObjectId id){
    journalEntryService.deleteById(userName,id);
    return new ResponseEntity<>(HttpStatus.NO_CONTENT);
  }

//  @PutMapping("/{id}")
//  public JournalEntry updateUserById(@PathVariable String id,@RequestBody JournalEntry journalEntry){
//    journalEntry.setId("id");
//    journalEntryService.createNewUser(journalEntry);
//    return journalEntry;
//  }

}
