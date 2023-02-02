package com.JoeBatchelor.DigitalLedger.profile;


import javax.persistence.*;

import java.util.Objects;
import java.util.Optional;
import java.util.UUID;




@Entity
@Table(name = "Users")
public class UserProfile {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "userProfileId")
    private final UUID userProfileId;

    @Column(name = "username")
    private final String username;

    @Column(name = "password")
    private final String password;

    @Column(name = "userProfileImageLink")
    private String userProfileImageLink; // S3 key

    public UserProfile(UUID userProfileId,
                       String username,
                       String password,
                       String userProfileImageLink) {
        this.userProfileId = userProfileId;
        this.username = username;
        this.password = password;
        this.userProfileImageLink = userProfileImageLink;
    }

    public UUID getUserProfileId() {
        return userProfileId;
    }

    public String getUsername() {
        return username;
    }

    public String getPassword() {
        return password;
    }

    public Optional<String> getUserProfileImageLink() {
        return Optional.ofNullable(userProfileImageLink);
    }

    public void setUserProfileImageLink(String userProfileImageLink) {
        this.userProfileImageLink = userProfileImageLink;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        UserProfile that = (UserProfile) o;
        return Objects.equals(userProfileId, that.userProfileId) &&
                Objects.equals(username, that.username) &&
                Objects.equals(password, that.password)&&
                Objects.equals(userProfileImageLink, that.userProfileImageLink);
    }

    @Override
    public int hashCode() {
        return Objects.hash(userProfileId, username, userProfileImageLink);
    }
}
