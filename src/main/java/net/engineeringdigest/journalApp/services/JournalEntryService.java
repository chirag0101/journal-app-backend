package net.engineeringdigest.journalApp.services;

import net.engineeringdigest.journalApp.entity.JournalEntry;
import net.engineeringdigest.journalApp.entity.UserEntry;
import net.engineeringdigest.journalApp.repository.JournalRepository;
import org.bson.types.ObjectId;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Optional;

@Component  //this class is stored in IOC and used as dependency injection wherever required
public class JournalEntryService {
    @Autowired
    private JournalRepository journalRepository;
    @Autowired
    private UserEntryService userEntryService;

    //create
    @Transactional //to make the service transactional so that if any failure occurs, nothing happens(Atomicity)
    public void createNewJournal(JournalEntry journalEntry, String userName) {
        try{
            UserEntry user=userEntryService.findByUserName(userName);
            JournalEntry journalSaved=journalRepository.save(journalEntry);
            user.getJournalEntries().add(journalSaved);
            userEntryService.saveEntry(user);
        }catch (Exception e){
            System.out.println(e);
        }
    }

    //read/view
    public List<JournalEntry> viewUsers(){
        return journalRepository.findAll();
    }

    //find by id
    public Optional<JournalEntry> findById(ObjectId id){
        return journalRepository.findById(id);
    }

    //deleteById
    public void deleteById(String userName, ObjectId id){
        try{
            UserEntry user=userEntryService.findByUserName(userName);
            user.getJournalEntries().removeIf(x->x.getId().equals(id));
            userEntryService.saveEntry(user);
            journalRepository.deleteById(id);
        } catch (Exception e) {
            System.out.println(e);
        }
    }

}
