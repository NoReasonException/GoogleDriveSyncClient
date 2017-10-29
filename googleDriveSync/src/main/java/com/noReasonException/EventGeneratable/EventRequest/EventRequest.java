package com.noReasonException.EventGeneratable.EventRequest;

import com.noReasonException.DirectoryManager.DirectoryController.DirectoryController;
import com.noReasonException.EventGeneratable.EventGenerators.InotifyWrapper.ModifiedType;

import java.util.Date;

/***
 *public class EventRequest...
 *@author noReasonException(Stefanos Stefanou)
 *
 *<p>
 *     Describe a standard Event Request for submit in the main event queue\
 *     +)Immutable..</p>
 *
 *
 */
public class EventRequest implements Comparable<EventRequest>{


    public int compareTo(EventRequest eventRequest) {
        if(this.dateCreated.getTime()==eventRequest.dateCreated.getTime())return 0;
        return this.dateCreated.getTime()>eventRequest.dateCreated.getTime()?1:-1;
    }

    private java.lang.String filePath;
    private java.util.Date   dateCreated=null;
    private com.noReasonException.DirectoryManager.DirectoryController.DirectoryController eventSource;
    private com.noReasonException.EventGeneratable.EventGenerators.InotifyWrapper.ModifiedType typeOfEvent=null;

    /***
     * Get file path , witch event occured
     * @return A fully constructed path
     */
    public java.lang.String getFilePath() {
        return this.filePath;
    }

    /***
     *
     * @return {@link DirectoryController The DirectoryController } witch the event occured...
     */
    public com.noReasonException.DirectoryManager.DirectoryController.DirectoryController getEventSource() {
        return eventSource;
    }

    /***
     *
     * @return {@link ModifiedType The type of Event } (example Creation of file , Deletion of file , e.t.c)
     */
    public ModifiedType getTypeOfEvent() {
        return typeOfEvent;
    }

    /***
     *
     * @param filePath      The path of file the request happened...
     * @param eventSource   The {@link DirectoryController The DirectoryController } who manages the directory that file exists in...
     */
    public EventRequest(java.lang.String filePath,ModifiedType type,DirectoryController eventSource) {
        this.filePath = filePath;
        this.typeOfEvent=type;
        this.eventSource = eventSource;
        this.dateCreated=new Date();
    }

    /***
     *
     * @return A formatted {@link java.lang.String String} , for debugging purposes
     */
    @Override
    public java.lang.String toString(){
        return "[EVENT OBJECT][Time : '"+this.dateCreated+"'][Name :'"+this.getFilePath()+"'][Type :' \t"+this.getTypeOfEvent()+"']";
    }
}
