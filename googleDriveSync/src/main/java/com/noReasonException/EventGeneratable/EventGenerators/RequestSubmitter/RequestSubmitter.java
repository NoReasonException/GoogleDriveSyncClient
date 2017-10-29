package com.noReasonException.EventGeneratable.EventGenerators.RequestSubmitter;

import com.noReasonException.EventGeneratable.EventGenerator;
import com.noReasonException.EventGeneratable.EventRequest.EventRequest;
import com.noReasonException.RequestQueueController.RequestQueue.RequestQueue;

import java.util.Queue;

/***
 * RequestSubmitter , is a runnable thread , to submits the generated events , from an {@link EventGenerator EventGenerator} object..
 * @author noReasonException (Stefanos Stefanou)
 * @version 0.0.1
 *Known Bugs...
 * +---------------------------------------------------------------------------------------------------------------------+
 * +    ID  Description                                                     Version             Status          Notes    |
 * +    00  In case of error at submitting , the EventRequest is lost...    0.0.1               PENDING           -      |
 * +---------------------------------------------------------------------------------------------------------------------+
 *  *FIXME...
 * +---------------------------------------------------------------------------------------------------------------------+
 * +    ID  Description                                                     Version             Status          Notes    |
 * +    00  submitRequestToQueue , replace boolean to Exception....         0.0.1               PENDING           -      |
 * +---------------------------------------------------------------------------------------------------------------------+
 */
public class RequestSubmitter implements java.lang.Runnable{
    private EventGenerator  generator       = null;                     ///The generator object...
    private Queue<EventRequest>   queueToSubmit    = null;                     ///The request Queue...

    /***
     *  Constructor of RequestSubmitter
     * @param generator       the {@link EventGenerator EventGenerator Object}
     * @param queueToSubmit   the {@link RequestQueue Queue} to submit the generated events...
     */
    public RequestSubmitter(EventGenerator generator,Queue<EventRequest> queueToSubmit){
        this.generator=generator;
        this.queueToSubmit=queueToSubmit;
    }
    public void run() {
        EventRequest req ;                                              ///Temponary EventRequest Reference...
        while(true){                                                    ///spin-style loop...
            this.generator.waitForEvent();                              ///Wait for new events...
            req=this.generator.getLastEventOccured();                   /// if waitForEvent returns , means that at least one request exists...
            while(req!=null){                                           ///take all requests...
                if(!this.submitRequestToQueue(req)){                    ///if submission error occurs, then print an error message FIXME
                    System.out.print("REQUEST QUEUE ERR");
                }

                req=this.generator.getLastEventOccured();               ///Get next event occured....

            }
        }
    }

    /***
     *
     * @param   eventRequest , the request to submit
     * @return  true if everything is fine , false otherwise...
     */
    private boolean submitRequestToQueue(EventRequest eventRequest) {   ///wrap over syncronized .submitEvent of RequestQueue !
        if(eventRequest==null || this.queueToSubmit==null)return false;
        if(!this.queueToSubmit.offer(eventRequest)){
            return false;
        }
        return true;
    }
}
