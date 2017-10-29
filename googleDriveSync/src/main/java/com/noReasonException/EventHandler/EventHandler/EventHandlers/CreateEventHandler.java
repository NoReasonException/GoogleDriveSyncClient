package com.noReasonException.EventHandler.EventHandler.EventHandlers;

import com.noReasonException.DirectoryManager.DirectoryController.DirectoryController;
import com.noReasonException.EventGeneratable.EventRequest.EventRequest;
import com.noReasonException.EventHandler.EventHandler.EventHandler;

public class CreateEventHandler extends EventHandler {
    public void run() {
        System.out.println("Create Event Handled (Details Following...)"+getPendingRequest());
    }
    public CreateEventHandler(EventRequest ev, DirectoryController target) {
        super(ev,target);
    }
}
