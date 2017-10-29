package com.noReasonException.DirectoryManager.DirectoryController.DirectoryControllers.DirectoryControlledFile;

import com.noReasonException.DirectoryManager.DirectoryController.DirectoryController;

import java.io.File;
import java.net.URI;
/***
 * DirectoryControlledFile class...
 * @see DirectoryController
 * @author  noReasonException(Stefanos Stefanou)
 * @version 0.0.1
 *      Manages all operations between an file in abstract location . used by DirectoryController .
 * <h1>Dont ever pass in any kind of stream , a file whitch is instanceOf(DirectoryController) because maybe this file exists somewhere outside your hard drive
 * please , use the .getInputStream and .getOutputStream instead...</h1>
 *<h2>Never create your own DirectoryControlledFile , use methods from your DirectoryController to get a reference... </h2>
 * Known Bugs...
 * +---------------------------------------------------------------------------------------------------------------------+
 * +    ID  Description                                                     Version             Status          Notes    |
 * +---------------------------------------------------------------------------------------------------------------------+
 * FIXME...
 * +---------------------------------------------------------------------------------------------------------------------+
 * +    ID  Description                                                     Version             Status          Notes    |
 *
 * +---------------------------------------------------------------------------------------------------------------------+
 */
public abstract class DirectoryControlledFile extends java.io.File {
    /***
     * Note:Dont create your own DirectoryControlledFiles , use the {@link DirectoryController DirectoryController} class methods instead...
     * @param s  The path , where the file exists , in the abstract location
     *
     */
    public DirectoryControlledFile(String s) {
        super(s);
    }
    /***
     *  Note:Dont create your own DirectoryControlledFiles , use the {@link DirectoryController DirectoryController} class methods instead...
    *   @param s  The parent folder path,where the file exists...
    *   @param s1  The path , where the file exists , in the abstract location
    */
    public DirectoryControlledFile(String s, String s1) {
        super(s, s1);
    }
    /***
     *   Note:Dont create your own DirectoryControlledFiles , use the {@link DirectoryController DirectoryController} class methods instead...
     *   @param file  The parent folder java.io.File//FIXME -> file parameter , DirectoryControlledFile
     *   @param s  The path , where the file exists , in the abstract location
     *
     */
    public DirectoryControlledFile(File file, String s) {
        super(file, s);
    }
    /***
     *   Note:Dont create your own DirectoryControlledFiles , use the {@link DirectoryController DirectoryController} class methods instead...
     *   @param uri The path , where the file exists , in the abstract location
     *
     */
    public DirectoryControlledFile(URI uri) {
        super(uri);
    }
    /***
     *   Get the {@link DirectoryController DirectoryController} reference , where this object generated.
     *   @return the {@link DirectoryController DirectoryController} reference
     *
     */
    public abstract DirectoryController getDirectoryController();

    /***
     * Get a Input Stream to communicate with file..
     *
     * <h4>Note : Dont ever pass directly a DirectoryControlledFile into a InputStream Constructor , because sometimes this file dont even exist in your hard drive.
     * Use this method instead..</h4>
     * @return an {@link java.io.InputStream InputStream} to the file...
     *
     */
    public abstract java.io.InputStream     getInputStream();
    /***
     * Get a Output Stream to communicate with file..
     *
     * <h4>Note : Dont ever pass directly a DirectoryControlledFile into a OutputStream Constructor , because sometimes this file dont even exist in your hard drive.
     * Use this method instead..</h4>
     * @return an {@link java.io.OutputStream OutputStream} to the file...
     *
     */
    public abstract java.io.OutputStream    getOutputStream();
}
