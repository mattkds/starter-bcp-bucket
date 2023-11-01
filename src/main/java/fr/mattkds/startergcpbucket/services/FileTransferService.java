package fr.mattkds.startergcpbucket.services;

import com.google.cloud.storage.Blob;
import com.google.cloud.storage.BlobId;
import com.google.cloud.storage.Bucket;
import com.google.cloud.storage.Storage;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

import java.nio.charset.StandardCharsets;

/**
 * FileTransfer Service dedicated to sending and reading operation on GCP Buckets
 * bucketName represent the GCP Bucket name use to store our uploaded files
 *
 */
@Service
public class FileTransferService {

    @Value("${starter-gcp-bucket.bucket-name}")
    private String bucketName;
    /*
    * storage is the Storage bean declare in the common GCP config on class GcpConfig
    */
    private final Storage storage;
    /*
     * File producer Service to provide file building methods
     */
    private final FileProducerService fileProducerService;
    /**
     * FileTransfer Service constructor
     *
     * @param storage             the storage gcp object to inject
     * @param fileProducerService the fileProducer service
     */
    public FileTransferService(Storage storage, FileProducerService fileProducerService) {
        this.storage = storage;
        this.fileProducerService = fileProducerService;
    }

    /**
     * Send Blob method, this method retrieve the bucket to use, create a file (Blob) with the word to send as content
     * The file generated is a byte array
     * The Blob is after that created in the bucket
     * @param content content to put in file
     * @return the identifier of blob
     */
    public BlobId sendBlob(String content) {
        Bucket bucket = this.getBucket(bucketName);
        byte[] blobContent = content.getBytes(StandardCharsets.UTF_8);
        String blobName = fileProducerService.getFileName(content);
        Blob blob = bucket.create(blobName, blobContent);
        return blob.getBlobId();
    }

    /*
     * Retrieve and create the Bucket object used to send Blob in bucket
     */
    private Bucket getBucket(String bucketName) {
        return storage.get(bucketName);
    }

}
