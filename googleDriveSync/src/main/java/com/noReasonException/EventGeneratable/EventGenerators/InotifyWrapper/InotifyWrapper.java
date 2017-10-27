package com.noReasonException.EventGeneratable.EventGenerators.InotifyWrapper;
/***
 * Known Bugs
 * 1) no look current directory.....SOLVED
 * 2) in upper case letters , break SOLVED
 * 3)strlen in initializeInotify return invalid len ....SOLVED
 * 4)memory Leak (buffer not freed in waitForFileEvent...)
 */

import java.io.IOException;

/***
 * Class InotifyNative , A wrapper to InotifySystem
 */
public class InotifyWrapper {
    private boolean bad=false;
    private native int          initializeInotify(byte path[]);                 //called from constructor
    private native int          initializeInotify_onCreateFileCreateNewWatchDecriptorFeature_enabled(byte path[]);
    public  native int          waitForFileEvent();                             //block until something happen (read() syscall blocks)
    public  native byte []      getLastModifiedFile() throws IOException;       //get path of last modified file
    public  native int          getLastModifiedType();                          //get type of event for last modified file !
    static{
        Runtime.getRuntime().loadLibrary("InotifyNative");
    }
    public InotifyWrapper(java.lang.String pathToWatch,boolean onCreateWatchDirectory){
        if(onCreateWatchDirectory){
            if(this.initializeInotify_onCreateFileCreateNewWatchDecriptorFeature_enabled(pathToWatch.getBytes())!=0){
                this.bad=true;
            }
        }
        else{
            if(this.initializeInotify(pathToWatch.getBytes())!=0){
                this.bad=true;
            }
        }
    }
    public boolean isBad(){
        return this.bad;
    }
    public static void main(String args[]) throws IOException{
        InotifyWrapper wr = new InotifyWrapper("../",true);
        if(wr.isBad()){
            throw new IllegalArgumentException();
        }
        wr.waitForFileEvent();
        System.out.print(new String(wr.getLastModifiedFile()));
    }





}