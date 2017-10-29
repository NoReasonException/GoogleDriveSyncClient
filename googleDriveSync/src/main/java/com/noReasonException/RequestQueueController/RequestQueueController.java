package com.noReasonException.RequestQueueController;

import com.noReasonException.DirectoryManager.DirectoryController.DirectoryController;
import com.noReasonException.DirectoryManager.DirectoryManager;
import com.noReasonException.EventGeneratable.EventRequest.EventRequest;
import com.noReasonException.RequestQueueController.RequestQueue.RequestQueue;

public class RequestQueueController {
    private com.noReasonException.RequestQueueController.RequestQueue.RequestQueue requests ;
    private EventRequest pendingEventRequest = null;

    public RequestQueueController(RequestQueue requests) {
        this.requests = requests;
    }

    public int mainloop(){
        while(true){//FIXME : when pendingEvent==null -> RequestQueueController thread wait(
            this.pendingEventRequest=requests.poll();
            if(this.pendingEventRequest!=null){
                ((DirectoryManager)DirectoryManager.getInstance()).broadcastEvent(pendingEventRequest);
                System.out.println("Broadcasted...");
            }
            else{
                //.wait()
            }
            try{
                Thread.sleep(10);

            }catch (InterruptedException e){

            }
        }
    }



}
