package gitlet;

import java.io.Serializable;
import java.time.ZonedDateTime;
import java.time.format.DateTimeFormatter;
import java.util.Date;
import java.util.HashMap;
import java.text.SimpleDateFormat;


/** Represents a gitlet commit object.
 *  This is the implementation for a Gitlet commit object. It stores information like the commit
 *  message, UID, time, and the same data for the parent UID.
 *
 *  @author Adithya Sagar
 */
public class Commit implements Serializable {

    /** The message of this Commit. */
    private String message;
    /** A HashMap that stores the name and UID. */
    private HashMap<String, String> tracker;
    /** The UID (SHA1) of a commit. */
    private String UID;
    /** The UID (SHA1) of the parent commit. */
    private String parentUID;
    /** The timestamp of this Commit. */
    private String time;
    private String parent2;

    public Commit(String message, HashMap<String, String> tracker, String cParent) {
        this.message = message;
        this.tracker = tracker;
        this.parentUID = cParent;
        this.parent2 = null;

        if (this.parentUID == null) {
            SimpleDateFormat formatter = new SimpleDateFormat("EEE MMM d HH:mm:ss yyyy Z");
            this.time = formatter.format(new Date(0));
        } else {
            DateTimeFormatter formatter = DateTimeFormatter.ofPattern("EEE MMM d HH:mm:ss yyyy Z");
            this.time = formatter.format(ZonedDateTime.now());
        }
        UID = makeUID();

    }
    public Commit(String message, HashMap<String, String> tracker, String cParent,
                  String mergeParent) {
        this.message = message;
        this.tracker = tracker;
        this.parentUID = cParent;
        this.parent2 = mergeParent;

        if (this.parentUID == null) {
            SimpleDateFormat formatter = new SimpleDateFormat("EEE MMM d HH:mm:ss yyyy Z");
            this.time = formatter.format(new Date(0));
        } else {
            DateTimeFormatter formatter = DateTimeFormatter.ofPattern("EEE MMM d HH:mm:ss yyyy Z");
            this.time = formatter.format(ZonedDateTime.now());
        }
        UID = makeUID();

    }

    public String getMessage() {
        return this.message;
    }
    public String makeUID() {
        byte[] commitBlob = Utils.serialize(this);
        return Utils.sha1(commitBlob);
    }

    public String getTime() {
        return this.time;
    }
    public String getUID() {
        return this.UID;
    }
    public String getParentUID() {
        return this.parentUID;
    }
    public HashMap<String, String> getBlobs() {
        return this.tracker;
    }
    public String getMergeParent() {
        return this.parent2;
    }
}
