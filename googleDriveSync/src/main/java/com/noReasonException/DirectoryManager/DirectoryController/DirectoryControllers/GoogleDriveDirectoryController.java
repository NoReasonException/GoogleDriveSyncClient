package com.noReasonException.DirectoryManager.DirectoryController.DirectoryControllers;

import com.noReasonException.DirectoryManager.DirectoryController.DirectoryControllers.DirectoryControlledFile.DirectoryControlledFile;
import com.noReasonException.DirectoryManager.DirectoryController.DirectoryController;
import com.noReasonException.DirectoryManager.DirectoryController.DirectoryControllers.DirectoryControlledFile.GoogleDriveFile;

public class GoogleDriveDirectoryController extends DirectoryController {
    public DirectoryControlledFile create(String path) {
        return null;
    }

    public boolean delete(String path) {
        return false;
    }

    public DirectoryControlledFile getFileFromPath(String path) {
        return null;
    }
}
