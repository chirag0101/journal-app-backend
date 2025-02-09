package net.engineeringdigest.journalApp.controller;

import net.engineeringdigest.journalApp.entity.UserEntry;
import net.engineeringdigest.journalApp.services.UserEntryService;
import org.bson.types.ObjectId;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import java.util.List;

@RestController
@RequestMapping("/user")
public class UserEntryController {
    @Autowired
    private UserEntryService userEntryService;

    @GetMapping
    public ResponseEntity<List<UserEntry>> getAllUsers(){
        List<UserEntry> usersEntries=userEntryService.viewUsers();
        if(usersEntries.isEmpty()){
           return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
        return new ResponseEntity<>(usersEntries,HttpStatus.OK
        );
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<?> deleteUser(@PathVariable ObjectId id){
        userEntryService.deleteById(id);
        return new ResponseEntity<>(HttpStatus.OK);
    }

//    @GetMapping("/{username}")
//    public ResponseEntity<?> findUser(@PathVariable String userName){
//        UserEntry usersEntries=userEntryService.findByUserName(userName);
//        if(usersEntries==null){
//            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
//        }
//        return new ResponseEntity<>(usersEntries,HttpStatus.OK);
//    }

    @PostMapping
    public ResponseEntity<?> createNewUser(@RequestBody UserEntry userEntry){
        try{
            userEntryService.createNewUser(userEntry);
            return new ResponseEntity<>(userEntry,HttpStatus.CREATED);
        }catch (Exception e){
            return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
        }
    }

    @PutMapping("/{userName}")
    public ResponseEntity<?> updateUser(@PathVariable String userName,@RequestBody UserEntry userEntry){
        try{
            UserEntry user=userEntryService.findByUserName(userName);
            if(user!=null){
                user.setUserName(userEntry.getUserName());
                user.setPassword(userEntry.getPassword());
                userEntryService.saveEntry(user);
            }
            return new ResponseEntity<>(HttpStatus.OK);
        }catch (Exception e){
            return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
        }
    }
}
