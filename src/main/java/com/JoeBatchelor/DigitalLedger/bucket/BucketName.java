package com.JoeBatchelor.DigitalLedger.bucket;

public enum BucketName {

    PROFILE_IMAGE("digital-ledger-upload");

    private final String bucketName;

    BucketName(String bucketName) {
        this.bucketName = bucketName;
    }

    public String getBucketName() {
        return bucketName;
    }
}