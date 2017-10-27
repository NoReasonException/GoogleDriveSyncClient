package com.noReasonException.EventGeneratable.EventGenerators;

import com.noReasonException.DirectoryController.DirectoryController;
import com.noReasonException.EventGeneratable.EventGenerator;
import com.noReasonException.EventGeneratable.EventGenerators.InotifyWrapper.InotifyWrapper;
import com.noReasonException.EventGeneratable.EventGenerators.InotifyWrapper.ModifiedType;
import com.noReasonException.EventGeneratable.EventRequest.EventRequest;

import java.io.IOException;

/***
 * InotifyWrapperEventGenerator
 * Wraps the InotifyWrapper and constructs EventRequests for submitting in request queue....
 * @author noReasonException(Stefanos Stefanou)
 *              The event generator for local file system using Linux Filesystem notificator (Inotify)
 * @version 0.0.1
 * Known Bugs...
 * +---------------------------------------------------------------------------------------------------------------------+
 * +    ID  Description                                                     Version             Status          Notes    |
 *
 * +---------------------------------------------------------------------------------------------------------------------+
 * */
public class InotifyWrapperEventGenerator extends InotifyWrapper implements EventGenerator {
    com.noReasonException.DirectoryController.DirectoryController                        DirectoryController=null;

    /***
     *
     * @param Path                      Path to watch...
     * @param LocalDirectoryController  Path Directory Controller...
     */
    public InotifyWrapperEventGenerator(java.lang.String Path,DirectoryController LocalDirectoryController){
        super(Path,true);
        this.DirectoryController=LocalDirectoryController;
    }

    /**
     *
     * @return true if all okay , false otherwise...
     */
    public boolean waitForEvent(){
        return (this.waitForFileEvent()==0);
    }

    /***
     *
     * @return The path directory controller...
     */
    public com.noReasonException.DirectoryController.DirectoryController getDirectoryController(){
        return this.DirectoryController;
    }

    /***
     *
     * @return Construct the EventRequest and return it !
     */
    public com.noReasonException.EventGeneratable.EventRequest.EventRequest getLastEventOccured(){
        try{
            ModifiedType typeOfEvent=ModifiedType.constructModifiedTypeFromMask(this.getLastModifiedType());
            byte NameOfModifiedFile[]=this.getLastModifiedFile();
            if(typeOfEvent==null || NameOfModifiedFile==null)return null;//FIXME

            EventRequest retval=new EventRequest(new java.lang.String(NameOfModifiedFile),typeOfEvent,this.DirectoryController);

            return retval;
        }catch (IOException e){

            return null;
        }
    }

}
