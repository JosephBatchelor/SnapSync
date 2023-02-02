package com.JoeBatchelor.DigitalLedger.profile;

import com.JoeBatchelor.DigitalLedger.datastore.DemoUserProfileDataStore;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public class UserProfileDataAccessService {

    private final DemoUserProfileDataStore demoUserProfileDataStore;

    @Autowired
    public UserProfileDataAccessService(DemoUserProfileDataStore demoUserProfileDataStore) {
        this.demoUserProfileDataStore = demoUserProfileDataStore;
    }

    List<UserProfile> getUserProfiles() {
        return demoUserProfileDataStore.getUserProfiles();
    }

}
