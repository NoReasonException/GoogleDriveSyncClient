package com.noReasonException.EventHandler.EventHandler;

import com.noReasonException.DirectoryManager.DirectoryController.DirectoryController;
import com.noReasonException.EventGeneratable.EventRequest.EventRequest;

/***
 * EventHandler , it represent a abstract action , for a {@link EventRequest} to be taken...
 *
 */
public abstract class EventHandler implements java.lang.Runnable {
    private EventRequest            pendingRequest = null;
    private DirectoryController     targetDirectoryController = null;
    /***
     *Event Handler Contructor ...
     * @param ev    The EventRequest
     * @param directoryControllerTarget The directory controller , witch this action takes place...
     */
    public EventHandler(EventRequest ev , DirectoryController directoryControllerTarget){
        this.pendingRequest=ev;
        this.targetDirectoryController=directoryControllerTarget;

    }
    /***
     * Get the Event , witch the action happen....
     * @return the Assosiated request
     */
    public EventRequest getPendingRequest() {
        return pendingRequest;
    }

    /***
     * Get the directory controller , witch this action takes place...
     * @return a {@link DirectoryController DirectoryController} Reference
     */
    public DirectoryController getTargetDirectoryController() {
        return targetDirectoryController;
    }

    abstract public void run();


}
