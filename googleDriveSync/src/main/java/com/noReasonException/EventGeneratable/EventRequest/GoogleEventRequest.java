package com.noReasonException.EventGeneratable.EventRequest;

import com.noReasonException.DirectoryController.DirectoryController;
import com.noReasonException.EventGeneratable.EventGenerators.InotifyWrapper.ModifiedType;

public class GoogleEventRequest extends EventRequest {
    private java.lang.String id=null;
    public GoogleEventRequest(String filePath, ModifiedType type, DirectoryController eventSource,String id) {
        super(filePath, type, eventSource);
        this.id=id;
    }

    public String getId() {
        return id;
    }
}
