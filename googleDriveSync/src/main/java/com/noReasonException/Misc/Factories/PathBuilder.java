package com.noReasonException.Misc.Factories;

import com.google.api.services.drive.model.File;
import com.noReasonException.EventGeneratable.EventRequest.GoogleEventRequest;

import java.io.IOException;
import java.util.*;


/***
 * A utillity class , to manage differencies between Local and GoogleDrive Disc Locations...
 * @author noReasonException(Stefanos Stefanou)
 *
 * @version 0.0.1
 * Known Bugs...
 * +-------------------------------------------------------------------------------------------------------------------------------------+
 * +    ID  Description                                                                     Version             Status          Notes    |
 * +    00  buildPathFromGoogleDriveFile not work for multiple parents                      0.0.1               PENDING           -      |
 * +    01  IOE in deletion , cause -> cant find parent , in deletion , we cant have url?   0.0.1               PENDING           -      |
 * +-------------------------------------------------------------------------------------------------------------------------------------+
 * */
public class PathBuilder {
    /***
     *  Gets a fileID and constructs a path
     * @param   mainServiceObject   the {@link ServiceFactory ServiceFactoryObject} to communicate with google drive REST Api.
     * @param   fileID              the fileID of the file .
     * @return                      the constructed Full path
     * @throws IOException          in case of any connection error...
     */
    public static java.lang.String buildPathFromGoogleDriveFile(ServiceFactory mainServiceObject, java.lang.String fileID) throws IOException {//TODO FIX 00

        java.lang.StringBuilder retval = new java.lang.StringBuilder();                                                         //utillity to build the path...
        java.lang.String tempID;                                                                                                //temp folder ID
        File f = mainServiceObject.getServiceObject().files().get(fileID).setFields("name,parents").execute();                  //File Reference
        retval.append(f.getName() + "/");                                                                                       //add first name to StringBuilder..
        tempID = f.getParents().get(0);                                                                                         //get first parent...
        while (f != null) {                                                                                                     //while parents exists...
            f = mainServiceObject.getServiceObject().files().get(tempID).setFields("name,parents").execute();                   //get eatch parent...
            if(f.getParents()==null){
                retval.append("./"+f.getName());                                                                                              //end char...FIXME
                //if is root
                f=null;                                                                                                         //no other parents exist
            }
            else{
                tempID = f.getParents().get(0);                                                                                 //parent exists, so we get it for next iteration
                retval.append(f.getName() + "/");                                                                               //get name of parent...
            }
        }

        ArrayList<String> tmp=new ArrayList<String>();                  //temp to reverse...

        Collections.addAll(tmp,retval.toString().split("/"));        //Add everithing to temp collection

        Collections.reverse(tmp);                                       //reverse...
        retval=new StringBuilder();
        for (String temp:tmp) {
            retval.append(temp+"/");
        }


        return retval.toString();
    }

    /***
     *
     * @param serviceFactory the {@link ServiceFactory ServiceFactoryObject} to communicate with google drive REST Api.
     * @param file           the google file class
     * @return                      the constructed Full path
     * @throws IOException          in case of any connection error...
     */

    public static java.lang.String buildPathFromGoogleDriveFile(ServiceFactory serviceFactory,File file) throws IOException{
        return buildPathFromGoogleDriveFile(serviceFactory,file.getId());
    }
}
