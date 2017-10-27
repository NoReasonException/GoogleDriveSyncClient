package com.noReasonException.EventGeneratable.EventGenerators.RequestSubmitter;

import com.noReasonException.EventGeneratable.EventGenerator;
import com.noReasonException.EventGeneratable.EventRequest.EventRequest;
import com.noReasonException.RequestQueueController.RequestQueue.RequestQueue;

import java.util.Queue;

/***
 * RequestSubmitter implements Runnable
 * @author noReasonException (Stefanos Stefanou)
 * Holds an event generator , ask him all the time for new events , if there is events , it submits in the RequestQueue..
 * @known_bugs
 * +---------------------------------------------------------------------------------------------------------------------+
 * +    ID  Description                                                     Version             Status          Notes    |
 * +---------------------------------------------------------------------------------------------------------------------+
 */
public class RequestSubmitter implements java.lang.Runnable{
    private EventGenerator  generator       = null;                     ///The generator object...
    private Queue<EventRequest>   queueToSubmit    = null;                     ///The request Queue...
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
    private boolean submitRequestToQueue(EventRequest eventRequest) {   ///wrap over syncronized .submitEvent of RequestQueue !
        if(eventRequest==null || this.queueToSubmit==null)return false;
        this.queueToSubmit.add(eventRequest);
        return true;
    }
}
