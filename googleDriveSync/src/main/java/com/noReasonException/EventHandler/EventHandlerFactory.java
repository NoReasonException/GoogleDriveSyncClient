package com.noReasonException.EventHandler;

import com.noReasonException.DirectoryManager.DirectoryController.DirectoryController;
import com.noReasonException.EventGeneratable.EventGenerators.InotifyWrapper.ModifiedType;
import com.noReasonException.EventGeneratable.EventRequest.EventRequest;
import com.noReasonException.EventHandler.EventHandler.EventHandler;
import com.noReasonException.EventHandler.EventHandler.EventHandlers.CreateEventHandler;
import com.noReasonException.EventHandler.EventHandler.EventHandlers.DeleteEventHandler;
import com.noReasonException.EventHandler.EventHandler.EventHandlers.ModifyEventHandler;
import org.w3c.dom.events.Event;

/***
 * EventHandlerFactory , use the build Method , to get the appropiate Runnable Handler , based on given event...
 * @author noReasonException(Stefanos Stefanou)
 * @version 0.0.1
 *
 */
public class EventHandlerFactory {
    /***
     *  Get the proper handler for a given {@link EventRequest Request}
     * <h3>Note:It may produce {@link NullPointerException NullPointerException}</h3>
     * @param ev The {@link EventRequest Request}
     * @param dc The {@link DirectoryController DirectoryController}
     * @return the appropriate Runnable {@link EventHandler handler} , to handle the given event.
     *         <br>In Case of unsupported event type , null it returned...
     *
     */
    public static EventHandler build(EventRequest ev,DirectoryController dc){
        if(ev.getTypeOfEvent()== ModifiedType.IN_CREATE)return new CreateEventHandler(ev,dc);
        else if(ev.getTypeOfEvent()== ModifiedType.IN_MODIFY)return new ModifyEventHandler(ev,dc);
        else if(ev.getTypeOfEvent()== ModifiedType.IN_DELETE || ev.getTypeOfEvent()== ModifiedType.IN_DELETE_SELF)return new DeleteEventHandler(ev,dc);
        return null;
    }
}
