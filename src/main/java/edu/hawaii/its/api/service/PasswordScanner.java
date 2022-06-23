package edu.hawaii.its.api.service;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import edu.hawaii.its.api.type.PasswordFoundException;

import org.springframework.stereotype.Service;

import javax.annotation.PostConstruct;
import java.util.List;

@Service
public class PasswordScanner {

    private static final Log logger = LogFactory.getLog(PasswordScanner.class);

    @PostConstruct
    public void init() throws PasswordFoundException {
        logger.info("init; starting...");
        checkForPasswords();
        logger.info("init; check for passwords finished.");
        logger.info("init; started.");
    }

    private void checkForPasswords() throws PasswordFoundException {
        CheckForPwdPattern checkForPwdPattern = new CheckForPwdPattern();

        String patternResult = "";
        String pattern = "^.*password.*\\=(?!\\s*$).+";
        String dirname = "src/main/resources";
        List<String> fileLocations = checkForPwdPattern.fileLocations(".properties", dirname, pattern);
        if (fileLocations != null && !fileLocations.isEmpty()) {
            for (String list : fileLocations) {
                patternResult += "\n" + list;
            }
        }

        if (patternResult.length() > 0) {
            throw new PasswordFoundException(patternResult);
        }
    }
}