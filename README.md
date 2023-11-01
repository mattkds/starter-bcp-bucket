# starter-gcp-bucket
Starter App to implement a GCP Bucket System with Java and Spring framework

## Introduction
This repository is a simple implementation of a spring web app using gcp cloud tool to store file on GCP Bucket Folder.

### Technical Stack

[Java 17](https://openjdk.org/projects/jdk/17/)

### Built With

![Spring](https://img.shields.io/static/v1?style=for-the-badge&message=Spring&color=6DB33F&logo=Spring&logoColor=FFFFFF&label=)
![Apache Maven](https://img.shields.io/static/v1?style=for-the-badge&message=Apache+Maven&color=C71A36&logo=Apache+Maven&logoColor=FFFFFF&label=)
![Google Cloud](https://img.shields.io/badge/GoogleCloud-%234285F4.svg?style=for-the-badge&logo=google-cloud&logoColor=white)

### Usage of Google Cloud Storage and Buckets

First of all a key has to be generated on GCP project with the dedicated service account that you will use

```json
{
  "type": "service_account",
  "project_id": "my-gcp-project",
  "private_key_id": "XXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXX",
  "private_key": "-----BEGIN PRIVATE KEY-----\nXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXX-----END PRIVATE KEY-----\n",
  "client_email": "my-gcp-sa@my-gcp-project.iam.gserviceaccount.com",
  "client_id": "0000000000000000000",
  "auth_uri": "https://accounts.google.com/o/oauth2/auth",
  "token_uri": "https://oauth2.googleapis.com/token",
  "auth_provider_x509_cert_url": "https://www.googleapis.com/oauth2/v1/certs",
  "client_x509_cert_url": "https://www.googleapis.com/robot/v1/metadata/x509/my-gcp-sa%40my-gcp-project.iam.gserviceaccount.com",
  "universe_domain": "googleapis.com"
}
```

This JSON Token will be used on the Spring boot Startup and to give the correct credentials for upload/download on GCP projects.
To generate this token on a slef managed GCP project on IAM & Administration > Service Account > Select your serviceAccount (3 dots) > Manage Key > Add key

On app properties part, you have to put the available location of this token and the corresponding GCP project

```properties
spring.cloud.gcp.project-id=my-gcp-project
spring.cloud.gcp.credentials.location=file:src/main/resources/sa-key.json
starter-gcp-bucket.bucket-name=my-gcp-bucket
starter-gcp-bucket.config-file=sa-key.json
```

Then, on the startup of Spring Boot application you will be able to connect to your storage, and use it to manage GCP Buckets.

```java
@Bean
public Storage storage() throws IOException {
    Credentials credentials = GoogleCredentials.fromStream(new ClassPathResource(credentialPath).getInputStream());
    return StorageOptions.newBuilder().setCredentials(credentials).setProjectId(projectId).build().getService();
}
```

### Test the API

#### GET
`send-to-gcs` [http://localhost:8080/send-to-gcs/test](#http://localhost:8080/send-to-gcs/test) <br/>

If your GCP configuration is correct, a file should be generated and send to your chosen bucket.
