package com.JoeBatchelor.DigitalLedger.datastore;

import com.JoeBatchelor.DigitalLedger.profile.UserProfile;
import com.JoeBatchelor.DigitalLedger.profile.UserRepository;
import org.springframework.boot.CommandLineRunner;
import org.springframework.context.annotation.Bean;
import org.springframework.stereotype.Repository;

import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

@Repository
public class DemoUserProfileDataStore {

    private static final List<UserProfile> USER_PROFILES = new ArrayList<>();


    static {
      USER_PROFILES.add(new UserProfile(UUID.fromString("623184c9-7a84-428e-a557-e6f577154440"), "Joseph Batchelor", "password", null));
      USER_PROFILES.add(new UserProfile(UUID.fromString("e0c76e2d-7de1-404f-82d5-908af2b52107"), "Antonio Mendez", "password", null));
    }


    //fIX THIS !!!!!!
//    @Bean
//    CommandLineRunner commandLineRunner(UserRepository userRepository){
//        return args -> {
//            userRepository.save(new UserProfile(UUID.fromString("623184c9-7a84-428e-a557-e6f577154440"), "Joseph Batchelor", "password", null));
//        };
//    }

    public List<UserProfile> getUserProfiles() {

        return USER_PROFILES;
    }

}
