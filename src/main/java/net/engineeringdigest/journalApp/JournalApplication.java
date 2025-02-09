package net.engineeringdigest.journalApp;


import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;
import org.springframework.data.mongodb.MongoDatabaseFactory;
import org.springframework.data.mongodb.MongoTransactionManager;
import org.springframework.data.mongodb.repository.config.EnableMongoRepositories;
import org.springframework.transaction.PlatformTransactionManager;
import org.springframework.transaction.annotation.EnableTransactionManagement;

@SpringBootApplication
@EnableMongoRepositories(basePackages = "net.engineeringdigest.journalApp.repository")
@EnableTransactionManagement
public class JournalApplication {

    public static void main(String[] args) {
        SpringApplication.run(JournalApplication.class, args);
    }

    @Bean   //PlatformTransactionManager contains method declaration of Commit & Rollback
    public PlatformTransactionManager platformTransactionManager(MongoDatabaseFactory dbFactory){   //MongoDBFactory ensures all connections with the Db, Db sessions, etc.
        return new MongoTransactionManager(dbFactory);  //MongoTransactionManager handles the whole transaction
    }

}