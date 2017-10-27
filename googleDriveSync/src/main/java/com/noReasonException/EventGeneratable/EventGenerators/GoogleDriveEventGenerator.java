package com.noReasonException.EventGeneratable.EventGenerators;

import com.google.api.services.drive.Drive;
import com.google.api.services.drive.model.Change;
import com.google.api.services.drive.model.ChangeList;
import com.google.api.services.drive.model.File;
import com.noReasonException.DirectoryController.DirectoryController;
import com.noReasonException.DirectoryController.DirectoryControllers.GoogleDriveDirectoryController;
import com.noReasonException.EventGeneratable.EventGenerator;
import com.noReasonException.EventGeneratable.EventGenerators.InotifyWrapper.ModifiedType;
import com.noReasonException.EventGeneratable.EventRequest.EventRequest;
import com.noReasonException.EventGeneratable.EventRequest.GoogleEventRequest;
import com.noReasonException.Misc.Factories.PathBuilder;
import com.noReasonException.Misc.Factories.ServiceFactory;

import java.io.IOException;
import java.util.List;
/***
 * public class GoogleDriveEventGenerator implements EventGenerator...
 * @author noReasonException(Stefanos Stefanou)
 *              The event generator for google drive API
 * @version 0.0.1
 * Known Bugs...
 * +---------------------------------------------------------------------------------------------------------------------+
 * +    ID  Description                                                     Version             Status          Notes    |
 * +    00  waitForFileEvent submits wrong ModifiedType(Debug Purposes)     0.0.1               PENDING           -      |
 * +    01  Unwritten IOException handler in constructor                    0.0.1               PENDING           -      |
 * +    02  Uses explicity additional File Get in getModifiedType..         0.0.1               PENDING           -      |
 * +---------------------------------------------------------------------------------------------------------------------+
 * */
public class GoogleDriveEventGenerator implements EventGenerator {
    private Drive                           serviceObject           =null;                                                          //the main google api service object
    private ServiceFactory                  serviceFactory          =null;                                                          //The service factory utillity
    private java.lang.String                startToken =null;                                                                       //the starting token for return events...
    private DirectoryController             directoryController     =null;                                                          //the google drive directory controller...
    private boolean                         isInitailizedProperly   =false;                                                         //flag if the start token request has failed...
    private java.util.Queue<EventRequest>   requestQueue            =new java.util.LinkedList<EventRequest>();                      //local event request queue

    private ModifiedType getModifiedTypeFromChangeResource(Change changeResource){
        if(changeResource.getRemoved())return ModifiedType.IN_DELETE;
        try{
            File fs= this.serviceObject.files().get(changeResource.getFileId()).setFields("trashed,createdTime").execute();
            if(fs.getTrashed()){
                return ModifiedType.IN_DELETE_SELF;
            }
            if(changeResource.getTime().equals(fs.getCreatedTime())){
                return ModifiedType.IN_CREATE;
            }
            if(changeResource.getTime().equals(fs.getModifiedTime())){
                return ModifiedType.IN_MODIFY;
            }

            return ModifiedType.IN_CREATE;
        }catch (IOException e){
            e.printStackTrace();
            return ModifiedType.IN_MODIFY;
        }
    }

    /***
     * contructor of google drive event generator...
     * @param serviceFactoryObject                                                                                                  //the main ServiceFactory object @see ServiceFactory
     * @param GoogleDriveDirectoryController                                                                                        //the google drive directory controller @see GoogleDriveDirectoryController
     */
    public GoogleDriveEventGenerator(ServiceFactory serviceFactoryObject, GoogleDriveDirectoryController GoogleDriveDirectoryController){
        this.serviceObject=serviceFactoryObject.getServiceObject();
        this.serviceFactory=serviceFactoryObject;

        try{
            this.startToken =this.serviceObject.changes().getStartPageToken().execute().getStartPageToken();
            this.isInitailizedProperly=true;
        }catch (IOException e){
            //FIXME
        }
    }

    /***
     * Blocks untill a request return at least one event....
     * @return false if startToken request is failed...
     *         true if everything is fine...
     */
    public boolean waitForEvent() {
        if (!this.isInitailizedProperly) return false;
        java.lang.String currToken = this.startToken;
        while (requestQueue.isEmpty()) {
            try {
                Drive.Changes.List currRequest = this.serviceObject.changes().list(currToken);
                ChangeList currList = currRequest.execute();
                List<Change> changes = currList.getChanges();
                for (Change ch : changes) {
                    this.requestQueue.add(new GoogleEventRequest(PathBuilder.buildPathFromGoogleDriveFile(this.serviceFactory,ch.getFileId()), this.getModifiedTypeFromChangeResource(ch), this.directoryController,ch.getFileId()));
                    System.out.println("Detect...");
                }
                currToken = currList.getNextPageToken();
                if (currToken == null) {
                    this.startToken=currToken = currList.getNewStartPageToken();
                }
            } catch (IOException e) {
                e.printStackTrace();
                System.out.print("err");
            }
        }

        return false;
    }

    /***
     *
     * @return The Generators directory Controller...
     */
    public com.noReasonException.DirectoryController.DirectoryController getDirectoryController(){
        return this.directoryController;
    }

    /**
     *
     * @return the most old event happened first (due to google api documentation...)
     */
    public com.noReasonException.EventGeneratable.EventRequest.EventRequest getLastEventOccured(){
        return requestQueue.poll();
    }

}
