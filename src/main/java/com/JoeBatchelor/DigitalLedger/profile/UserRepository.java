package com.JoeBatchelor.DigitalLedger.profile;

import org.springframework.data.jpa.repository.JpaRepository;

import java.util.UUID;

public interface UserRepository extends JpaRepository<UserProfile, Integer> {

}
