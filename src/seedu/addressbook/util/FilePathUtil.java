package seedu.addressbook.util;

import java.io.File;
import java.io.IOException;

/**
 * This is a utility class for file path validation.
 */
public class FilePathUtil {
    private static final int EXTENSION_INVALID_INDEX = 0;

    /**
     * Returns true if the given file path is acceptable.
     * The file path is acceptable if it has a folder that exists,
     * has file name that is acceptable to the operating system eg. does not contain <>:"/|?*.
     * File name must also contain an extension.
     */
    public static boolean isValidFilePath(String filePath) {
        if (filePath == null) {
            return false;
        }
        File filePathToValidate = new File(filePath);
        if (!hasValidFileName(filePathToValidate)) {
            return false;
        }
        if(!hasValidFolder(filePathToValidate)) {
            return false;
        }
        return true;
    }
    
    /**
     * Returns true if the file path has a folder that exists
     */
    private static boolean hasValidFolder(File path) {
        File folderToValidate = path.getAbsoluteFile().getParentFile();
        return folderToValidate.exists();
    }
    
    /**
     * Returns true if the file path has a valid file name
     * File name is valid if it has a name, an extension and no reserved characters <>:"/|?*.
     */
    private static boolean hasValidFileName(File filePath) {
        try {
            File fileNameToValidate = new File(filePath.getName()).getCanonicalFile();
            int extensionSeparatorIndex = fileNameToValidate.getName().lastIndexOf(".");
            return extensionSeparatorIndex > EXTENSION_INVALID_INDEX;
        } catch (IOException ioe) {
            return false;
        }
    }
}
