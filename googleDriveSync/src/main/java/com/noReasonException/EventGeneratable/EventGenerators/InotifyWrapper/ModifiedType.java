package com.noReasonException.EventGeneratable.EventGenerators.InotifyWrapper;

/***
 *  ModifiedType Enum...
 *  Describes the type of the event , in a specific file , is a simple wrapper over Inotify Flags in struct inotify_event !
 *  This enum provides and a static method for construct a Modified file using a integer maskbit.
 *
 */
public enum ModifiedType{
    IN_ACCESS           (0x00000001),	    /* File was accessed */
    IN_MODIFY           (0x00000002),	    /* File was modified */ // ----------------------------
    IN_ATTRIB           (0x00000004),	    /* Metadata changed */
    IN_CLOSE_WRITE      (0x00000008),   	/* Writtable file was closed */
    IN_CLOSE_NOWRITE    (0x00000010),       /* Unwrittable file closed */
    IN_OPEN             (0x00000020),	    /* File was opened */
    IN_MOVED_FROM       (0x00000040),	    /* File was moved from X */
    IN_MOVED_TO         (0x00000080),	    /* File was moved to Y */
    IN_CREATE           (0x00000100),	    /* Subfile was created *///------------------------------
    IN_DELETE           (0x00000200),	    /* Subfile was deleted *///----------------------
    IN_DELETE_SELF      (0x00000400),	    /* Self was deleted */
    IN_MOVE_SELF        (0x00000800);	    /* Self was moved */
    ModifiedType(int Mask){
        this.mask=Mask;
    }
    private int mask;
    public int getMask(){
        return this.mask;
    }
    public static ModifiedType constructModifiedTypeFromMask(int mask){
        if((mask&IN_ACCESS.getMask())!=0)return IN_ACCESS;
        if((mask&IN_MODIFY.getMask())!=0)return IN_MODIFY;
        if((mask&IN_ATTRIB.getMask())!=0)return IN_ATTRIB;
        if((mask&IN_CLOSE_WRITE.getMask())!=0)return IN_CLOSE_WRITE;
        if((mask&IN_CLOSE_NOWRITE.getMask())!=0)return IN_CLOSE_NOWRITE;
        if((mask&IN_OPEN.getMask())!=0)return IN_OPEN;
        if((mask&IN_MOVED_FROM.getMask())!=0)return IN_MOVED_FROM;
        if((mask&IN_MOVED_TO.getMask())!=0)return IN_MOVED_TO;
        if((mask&IN_CREATE.getMask())!=0)return IN_CREATE;
        if((mask&IN_DELETE.getMask())!=0)return IN_DELETE;
        if((mask&IN_DELETE_SELF.getMask())!=0)return IN_DELETE_SELF;
        if((mask&IN_MOVE_SELF.getMask())!=0)return IN_MOVE_SELF;
        return null;
    }
}
