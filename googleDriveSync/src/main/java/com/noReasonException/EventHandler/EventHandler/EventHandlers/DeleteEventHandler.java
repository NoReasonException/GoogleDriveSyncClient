package com.noReasonException.EventHandler.EventHandler.EventHandlers;

import com.noReasonException.DirectoryManager.DirectoryController.DirectoryController;
import com.noReasonException.EventGeneratable.EventRequest.EventRequest;
import com.noReasonException.EventHandler.EventHandler.EventHandler;

public class DeleteEventHandler extends EventHandler {
    public void run() {
        System.out.println("Delete Event Handled (Details Following...)"+getPendingRequest());
    }
    public DeleteEventHandler(EventRequest ev, DirectoryController target) {
        super(ev,target);
    }
}
