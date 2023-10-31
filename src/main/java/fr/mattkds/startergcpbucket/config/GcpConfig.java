package fr.mattkds.startergcpbucket.config;

import com.google.auth.Credentials;
import com.google.auth.oauth2.GoogleCredentials;
import com.google.cloud.storage.Storage;
import com.google.cloud.storage.StorageOptions;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.core.io.ClassPathResource;

import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStream;

/**
 * GCP Configuration to manage the storage of the application
 * credentialPath represent the credential path of config json file download from GCP for our Service account dedicated to this app
 * projectId is the GCP projectId that we use for this app
 *
 * The following configuration declare a bean storage used for our storage actions in GCP Buckets
 */
@Configuration
public class GcpConfig {

    @Value("${starter-gcp-bucket.config-file}")
    private String credentialPath;

    @Value("${spring.cloud.gcp.project-id}")
    private String projectId;

    @Bean
    public Storage storage() throws IOException {
        Credentials credentials = GoogleCredentials.fromStream(new ClassPathResource(credentialPath).getInputStream());
        return StorageOptions.newBuilder().setCredentials(credentials).setProjectId(projectId).build().getService();
    }

}
