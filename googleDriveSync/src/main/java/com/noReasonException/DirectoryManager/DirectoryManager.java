package com.noReasonException.DirectoryManager;

import com.noReasonException.DirectoryManager.DirectoryController.DirectoryController;
import com.noReasonException.EventGeneratable.EventRequest.EventRequest;
import com.noReasonException.EventHandler.EventHandlerFactory;
import java.util.LinkedList;

/*** <p>
 * Directory Manager Class...
 * @see DirectoryController
 * @see EventRequest
 * @see EventHandlerFactory
 * @see com.noReasonException.EventHandler.EventHandler.EventHandler
 * @author  noReasonException(Stefanos Stefanou)
 * @version 0.0.1
 *      Manages all Directory controllers , and offers services as
 *                  1)Broadcast Event (Sync Event ) in all Directory Controllers...
 *                  2)Broadcast Event to specific Directory Controller
 *      Is a siglenton class , only one instance per running daemon is required...
 * Known Bugs...
 * +---------------------------------------------------------------------------------------------------------------------+
 * +    ID  Description                                                     Version             Status          Notes    |
 * +    00  If a thread throws exception , the pending event is not saved   0.0.1               PENDING           -      |                                                                                                    |
 * +---------------------------------------------------------------------------------------------------------------------+
 * FIXME...
 * +---------------------------------------------------------------------------------------------------------------------+
 * +    ID  Description                                                     Version             Status          Notes    |
 * +    00  .broadcastEvent() -> Missing UncaughtExceptionHandler           0.0.1               PENDING           -      |
 * +---------------------------------------------------------------------------------------------------------------------+</p>
 */
public class DirectoryManager extends LinkedList<DirectoryController> {

    private static DirectoryManager instance=null;
    static{
        DirectoryManager.instance=new DirectoryManager();
    }

    /***
     * private no-arg constructor , to get the instance of DirectoryManager , use {@link #getInstance() .getInstance() }
     */
    private DirectoryManager(){}
    /**
     * Get the DirectoryManager
     * @return The siglenton object
     */
    public static DirectoryManager getInstance(){
        return DirectoryManager.instance;
    }

    /***
     * <P>BroadCast an {@link } into all DirectoryControllers</P>
     * @param ev The EventRequest to broadcast in all submitted DirectoryControllers
     *
     */
    public void broadcastEvent(EventRequest ev){
        for (DirectoryController dc : this) {
            new Thread(EventHandlerFactory.build(ev,dc)).start();
        }
    }

    /***
     *
     * @param ev The event to broadcast
     * @param dc The submitted Directory controller to broadcast..
     * @return false in case of un-submitted DirectoryController object<br>
     *         true otherwise
     */
    public boolean broadcastEvent(EventRequest ev,DirectoryController dc){
        if(!this.contains(dc))return false;
        new Thread(EventHandlerFactory.build(ev,dc)).start();
        return false;
    }

}
