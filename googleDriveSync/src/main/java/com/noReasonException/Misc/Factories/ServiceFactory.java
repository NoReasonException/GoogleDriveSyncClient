package com.noReasonException.Misc.Factories;

import com.google.api.client.extensions.java6.auth.oauth2.AuthorizationCodeInstalledApp;
import com.google.api.client.extensions.jetty.auth.oauth2.LocalServerReceiver;
import com.google.api.client.googleapis.auth.oauth2.GoogleAuthorizationCodeFlow;
import com.google.api.client.googleapis.auth.oauth2.GoogleClientSecrets;
import com.google.api.client.googleapis.javanet.GoogleNetHttpTransport;
import com.google.api.client.json.jackson2.JacksonFactory;
import com.google.api.services.drive.Drive;
import com.google.api.services.drive.DriveScopes;
import com.noReasonException.DirectoryManager.DirectoryController.DirectoryControllers.GoogleDriveDirectoryController;
import com.noReasonException.DirectoryManager.DirectoryController.DirectoryControllers.LocalDirectoryController;
import com.noReasonException.DirectoryManager.DirectoryManager;
import com.noReasonException.EventGeneratable.EventGenerators.GoogleDriveEventGenerator;
import com.noReasonException.EventGeneratable.EventGenerators.InotifyWrapperEventGenerator;
import com.noReasonException.EventGeneratable.EventGenerators.RequestSubmitter.RequestSubmitter;
import com.noReasonException.RequestQueueController.RequestQueue.RequestQueue;
import com.noReasonException.RequestQueueController.RequestQueueController;

import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.util.*;
/***
 * public class ServiceFactory...
 * @author noReasonException(Stefanos Stefanou)
 *              Factory for easy handling google drive service actions...
 * @version 0.0.1
 * Known Bugs...
 * +-----------------------------------------------------------------------------------------------------------------+
 * +    ID  Description                                                 Version             Status          Notes    |
 * +                                                                                                                 |
 * +-----------------------------------------------------------------------------------------------------------------+
 * */
public class ServiceFactory  {
    private  com.google.api.services.drive.Drive                     ServiceObject   =   null;                               //Main Factories Object File
    private  com.google.api.client.auth.oauth2.Credential            AccessToken     =   null;                               //Access Token returned by API
    private  com.google.api.client.json.JsonFactory                  JSON_FACTORY    =   JacksonFactory.getDefaultInstance();//Main JSON Decoder class
    private  final  java.util.Set<java.lang.String>                  AccessScopes    =   DriveScopes.all();                  //Get full access to user data and files (only for productions , leave later!
    private  final  java.lang.String                                 ApplicationName =   "Google Drive Sync for linux";

    private  com.google.api.client.json.JsonFactory                  AccessToken_StoreFactory;
    private  com.google.api.client.http.HttpTransport                GoogleTrustedHttpSession;
    private  com.google.api.client.util.store.FileDataStoreFactory   CredentialsStore;





    public String getApplicationName() {
        return ApplicationName;
    }

    public ServiceFactory(){
        try{
            this.CredentialsStore         = new com.google.api.client.util.store.FileDataStoreFactory(new java.io.File(System.getProperty("user.home"),".googleDriveSunc/credentials"));        //Set up credentialFolder..
            this.GoogleTrustedHttpSession = GoogleNetHttpTransport.newTrustedTransport();                                                                                                           //create http transport
            this.getAccessToken();                                                                                                                                                                  //get Access Token...

        }catch (Exception ioexcept){//FIXME:ERROR ON USER_SPACE
            System.out.print("err");
        }
        finally {
            this.buildServiceObject();

        }
    }
    public com.google.api.services.drive.Drive          getServiceObject(){ return this.ServiceObject;  }
    public com.google.api.client.auth.oauth2.Credential getCredentials()  { return this.AccessToken;    }

    private void getAccessToken()throws IOException{

        InputStream                 consumerIDFile      = new FileInputStream(new java.io.File("res/client_secret.json"));               //Open consumer_id container file..
        GoogleClientSecrets         clientSecrets       = GoogleClientSecrets.load(JSON_FACTORY,new InputStreamReader(consumerIDFile));     //Decode .json file and construct GoogleClientSecrets object
        GoogleAuthorizationCodeFlow oathAuthenticateFlow=new GoogleAuthorizationCodeFlow.Builder(GoogleTrustedHttpSession,JSON_FACTORY,clientSecrets,AccessScopes)
                .setDataStoreFactory(CredentialsStore)
                .setAccessType("offline")
                .build();
        this.AccessToken=new AuthorizationCodeInstalledApp(oathAuthenticateFlow,new LocalServerReceiver()).authorize("user");

    }
    private void buildServiceObject(){
        this.ServiceObject=new Drive.Builder(GoogleTrustedHttpSession,JSON_FACTORY,AccessToken)
                .setApplicationName(this.ApplicationName)
                .build();
    }
    public static void main(String args[]) throws IOException,InterruptedException {
        ServiceFactory f = new ServiceFactory();
        RequestQueue mainEventQueue = new RequestQueue();
        RequestQueueController mainController = new RequestQueueController(mainEventQueue);

        LocalDirectoryController directoryController = new LocalDirectoryController();
        DirectoryManager.getInstance().add(directoryController);
        RequestSubmitter localSubmitter = new RequestSubmitter(new InotifyWrapperEventGenerator("./Test",directoryController),mainEventQueue);


        GoogleDriveDirectoryController GdirectoryController = new GoogleDriveDirectoryController();
        DirectoryManager.getInstance().add(GdirectoryController);
        RequestSubmitter cloudSubmitter = new RequestSubmitter(new GoogleDriveEventGenerator(f,GdirectoryController),mainEventQueue);
        System.out.println("Starting Threads...");
        new Thread (localSubmitter).start();
        new Thread (cloudSubmitter).start();
        System.out.println("START...");
        mainController.mainloop();

    }
}
