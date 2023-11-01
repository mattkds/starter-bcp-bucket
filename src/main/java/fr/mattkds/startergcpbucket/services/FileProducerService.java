package fr.mattkds.startergcpbucket.services;

import org.springframework.stereotype.Service;

/**
 * Basic service to provide file methods
 */
@Service
public class FileProducerService {

    /*
     * File extension use for our uploaded files
     */
    private static final String EXTENSION_TXT = ".txt";

    /**
     * Method to build filename
     * @param name name of file
     * @return filename
     */
    public String getFileName(String name) {
        return name + "_generated" + EXTENSION_TXT;
    }

}
