package net.engineeringdigest.journalApp.services;

import net.engineeringdigest.journalApp.entity.UserEntry;
import net.engineeringdigest.journalApp.repository.UserRepository;
import org.bson.types.ObjectId;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.List;

@Component
public class UserEntryService {
    @Autowired
    private UserRepository userRepository;

    //create
    public void createNewUser(UserEntry userEntry) {
        userRepository.save(userEntry);
    }

    //read/view
    public List<UserEntry> viewUsers(){
        return userRepository.findAll();
    }

    //find by username
    public UserEntry findByUserName(String userName){
        return userRepository.findByUserName(userName);
    }

    //deleteById
    public void deleteById(ObjectId id){
        userRepository.deleteById(id);
    }

    //saveEntry
    public void saveEntry(UserEntry userEntry){
        userRepository.save(userEntry);
    }

    //updateEntry

}

