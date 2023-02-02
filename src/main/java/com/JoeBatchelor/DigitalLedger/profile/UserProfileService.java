package com.JoeBatchelor.DigitalLedger.profile;
import com.JoeBatchelor.DigitalLedger.bucket.BucketName;
import com.JoeBatchelor.DigitalLedger.filestore.FileStore;
import org.apache.http.entity.ContentType;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;
import java.io.IOException;
import java.util.*;


@Service
public class UserProfileService {

    private final UserProfileDataAccessService userProfileDataAccessService;
    private final FileStore fileStore;

    @Autowired
    public UserProfileService(UserProfileDataAccessService userProfileDataAccessService, FileStore fileStore) {
        this.userProfileDataAccessService = userProfileDataAccessService;
        this.fileStore = fileStore;
    }

    List<UserProfile> getUserProfiles() {
        return userProfileDataAccessService.getUserProfiles();
    }


    void uploadUserProfileImage(UUID userProfileId, MultipartFile file) {
        isFileEmpty(file);

        isImage(file);

        UserProfile user = getUserProfile(userProfileId);

        Map<String, String> metaadata = ExtractMetaData(file);

        String path = String.format("%s/%s", BucketName.PROFILE_IMAGE.getBucketName(), user.getUserProfileId());
        String filename = String.format("%s-%s",file.getOriginalFilename(), UUID.randomUUID());

        try {
            fileStore.save(path, filename, Optional.of(metaadata),file.getInputStream());
            user.setUserProfileImageLink(filename);
        } catch (IOException e) {
            throw new IllegalStateException(e);
        }
    }

    private Map<String, String> ExtractMetaData(MultipartFile file) {
        Map<String, String> metaadata = new HashMap<>();
        metaadata.put("Content-Type", file.getContentType());
        metaadata.put("Content-Length", String.valueOf(file.getSize()));
        return metaadata;
    }

    UserProfile getUserProfile(UUID userProfileId) {
        return userProfileDataAccessService
                .getUserProfiles()
                .stream()
                .filter(userProfile -> userProfile.getUserProfileId().equals(userProfileId))
                .findFirst()
                .orElseThrow(() -> new IllegalStateException(String.format("User profile %s not found", userProfileId)));
    }

    private void isImage(MultipartFile file) {
        if(!Arrays.asList(ContentType.IMAGE_JPEG.getMimeType(),ContentType.IMAGE_PNG.getMimeType(), ContentType.IMAGE_GIF.getMimeType()).contains(file.getContentType())){
            throw new IllegalStateException("File must be an image. ["+file.getContentType()+"]");
        }
    }

    private static void isFileEmpty(MultipartFile file) {
        if (file.isEmpty()){
        throw new IllegalStateException("Cannot upload empty file [ "+ file.getSize() + " ]");
        }
    }

    public byte[] downloadUserProfileImage(UUID userProfileId){
       UserProfile user = getUserProfile(userProfileId);
        String path = String.format("%s/%s",
                BucketName.PROFILE_IMAGE.getBucketName(),
                user.getUserProfileId(),
                user.getUserProfileImageLink());

       return user.getUserProfileImageLink()
                .map(key -> fileStore.download(path, key))
                .orElse(new byte[0]);

    }
}
