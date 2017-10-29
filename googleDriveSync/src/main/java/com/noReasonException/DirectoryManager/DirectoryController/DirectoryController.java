package com.noReasonException.DirectoryManager.DirectoryController;


import com.noReasonException.DirectoryManager.DirectoryController.DirectoryControllers.DirectoryControlledFile.DirectoryControlledFile;
/***
 * DirectoryController Class...
 * @see DirectoryControlledFile
 * @see com.noReasonException.DirectoryManager.DirectoryController.DirectoryControllers.GoogleDriveDirectoryController
 * @see com.noReasonException.DirectoryManager.DirectoryController.DirectoryControllers.LocalDirectoryController
 * @see FileType
 * @author  noReasonException(Stefanos Stefanou)
 * @version 0.0.1
 *      Manages all operations , between an abstract location and this code...
 *
 * Known Bugs...
 * +---------------------------------------------------------------------------------------------------------------------+
 * +    ID  Description                                                     Version             Status          Notes    |
 * +                                                                                                                     |
 * +---------------------------------------------------------------------------------------------------------------------+
 * FIXME...
 * +---------------------------------------------------------------------------------------------------------------------+
 * +    ID  Description                                                     Version             Status          Notes    |
 * +    00  .create(java.lang.String) throws an IOException in case of err  0.0.1               PENDING           -      |
 * +    01  .delete(java.lang.String) throws an IOException in case of err  0.0.1               PENDING           -      |
 * +    02  .getFileFromPath(java.lang.String) throws an IOE in case of err 0.0.1               PENDING           -      |
 * +---------------------------------------------------------------------------------------------------------------------+
 */

abstract public class DirectoryController {

    /***
     *
     * @param   path -> The path of the abstract location to create a file...
     * @return  The Rererence to that File.../null otherwise..
     */
    public abstract DirectoryControlledFile create          (java.lang.String path);
    /***
     *
     * @param   path -> The path of the abstract location to delete a file...
     * @return  true in success/false on failure...
     */
    public abstract boolean                 delete          (java.lang.String path);
    /***
     *
     * @param   path -> The path of the abstract location to get a file reference...
     * @return  the reference in success/false otherwise..
     */
    public abstract DirectoryControlledFile getFileFromPath (java.lang.String path);

}