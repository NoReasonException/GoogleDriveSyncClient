package com.noReasonException.EventGeneratable;

public interface EventGenerator {
    boolean waitForEvent();
    com.noReasonException.EventGeneratable.EventRequest.EventRequest getLastEventOccured();
    com.noReasonException.DirectoryController.DirectoryController    getDirectoryController();

}
