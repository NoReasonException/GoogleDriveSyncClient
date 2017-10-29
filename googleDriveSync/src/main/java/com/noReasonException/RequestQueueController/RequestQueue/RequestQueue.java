package com.noReasonException.RequestQueueController.RequestQueue;

import com.noReasonException.EventGeneratable.EventRequest.EventRequest;

import java.util.*;


/***
 * public class RequestQueue extends LinkedList<EventRequest>..
 *
 * @author noReasonException(Stefanos Stefanou)
 *  A simple thread-safe Queue for submitting the Event Requests...
 * @version 0.0.1
 * Known Bugs...
 * +-------------------------------------------------------------------------------------------------------------------------------------+
 * +    ID  Description                                                                     Version             Status          Notes    |
 * +-------------------------------------------------------------------------------------------------------------------------------------+
 * */
public class RequestQueue extends PriorityQueue<EventRequest> {
    @Override
    public boolean add(EventRequest eventRequest){
        synchronized (this){
            System.out.println(Thread.currentThread().getName()+" put in queue the .. { "+eventRequest+"}");
            return super.add(eventRequest);
        }
    }
    @Override
    public EventRequest poll(){
        synchronized (this){
            return super.poll();
        }
    }
    @Override
    public boolean offer(EventRequest eventRequest) {
        synchronized (this){
            System.out.println(Thread.currentThread().getName()+" put in queue the .. { "+eventRequest+"}");
            return super.offer(eventRequest);
        }
    }
    @Override
    public EventRequest remove() {
        synchronized (this){
            //System.out.println(Thread.currentThread().getName()+" put in queue the .. { "+eventRequest+"}");
            return super.remove();
        }
    }
    @Override
    public EventRequest element() {
        synchronized (this){
            //System.out.println(Thread.currentThread().getName()+" put in queue the .. { "+eventRequest+"}");
            return super.element();
        }
    }
    @Override
    public EventRequest peek() {
        synchronized (this) {
            //System.out.println(Thread.currentThread().getName()+" put in queue the .. { "+eventRequest+"}");
            return super.peek();
        }
    }

}