package fr.mattkds.startergcpbucket.controller;

import com.google.cloud.storage.BlobId;
import fr.mattkds.startergcpbucket.services.FileTransferService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;

/**
 * Simple Rest Controller to send a file in GCP Bucket by perform API calls
 * This controller use the Basic File Transfer Service
 */
@RestController
public class UploadController {

    private static final Logger LOGGER = LoggerFactory.getLogger(UploadController.class);

    private final FileTransferService fileTransferService;

    public UploadController(FileTransferService fileTransferService) {
        this.fileTransferService = fileTransferService;
    }

    /**
     * Simple Endpoint to generate a file and send it to a GCP bucket
     * @param content the content to put in uploaded file, send as Path variable
     * @return ResponseEntity with 200 Http Code when the file is generated
     */
    @GetMapping("/send-to-gcs/{content}")
    public ResponseEntity<String> sendToGcs(@PathVariable String content) {
        BlobId blobId = fileTransferService.sendBlob(content);
        LOGGER.info("File generated in Bucket with name {}", blobId.getName() );
        return ResponseEntity.ok("File generated");
    }

}
