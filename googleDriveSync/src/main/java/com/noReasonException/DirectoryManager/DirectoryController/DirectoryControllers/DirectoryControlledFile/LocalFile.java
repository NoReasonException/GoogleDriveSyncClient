package com.noReasonException.DirectoryManager.DirectoryController.DirectoryControllers.DirectoryControlledFile;

import com.noReasonException.DirectoryManager.DirectoryController.DirectoryController;

import java.io.File;
import java.io.InputStream;
import java.io.OutputStream;
import java.net.URI;

public class LocalFile extends DirectoryControlledFile {
    public LocalFile(String s) {
        super(s);
    }

    public LocalFile(String s, String s1) {
        super(s, s1);
    }

    public LocalFile(File file, String s) {
        super(file, s);
    }

    public LocalFile(URI uri) {
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
