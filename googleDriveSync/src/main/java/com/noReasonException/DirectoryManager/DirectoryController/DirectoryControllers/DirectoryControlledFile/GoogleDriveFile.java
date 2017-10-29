package com.noReasonException.DirectoryManager.DirectoryController.DirectoryControllers.DirectoryControlledFile;

import com.noReasonException.DirectoryManager.DirectoryController.DirectoryController;

import java.io.File;
import java.io.InputStream;
import java.io.OutputStream;
import java.net.URI;
/***
 * GoogleDriveFile class...
 * @see DirectoryController
 * @author  noReasonException(Stefanos Stefanou)
 * @version 0.0.1
 *      Manages all operations between an file in google Drive Server . used by DirectoryController .
 * Known Bugs...
 * +---------------------------------------------------------------------------------------------------------------------+
 * +    ID  Description                                                     Version             Status          Notes    |
 * +---------------------------------------------------------------------------------------------------------------------+
 * FIXME...
 * +---------------------------------------------------------------------------------------------------------------------+
 * +    ID  Description                                                     Version             Status          Notes    |
 * +---------------------------------------------------------------------------------------------------------------------+
 */
public class GoogleDriveFile extends DirectoryControlledFile {
    public GoogleDriveFile(String s) {
        super(s);
    }

    public GoogleDriveFile(String s, String s1) {
        super(s, s1);
    }

    public GoogleDriveFile(File file, String s) {
        super(file, s);
    }

    public GoogleDriveFile(URI uri) {
        super(uri);
    }

    @Override
    public DirectoryController getDirectoryController() {
        return null;
    }

    @Override
    public InputStream getInputStream() {
        return null;
    }

    @Override
    public OutputStream getOutputStream() {
        return null;
    }
}
