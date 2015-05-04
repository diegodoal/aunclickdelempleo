package models.entities;

import utils.Utils;

import java.util.Date;
import java.util.UUID;

/**
 * Created by Victor on 04/05/2015.
 */
public class Message {

    public String id;
    public String fromUser;
    public String toUser;
    public String subject;
    public String message;
    public String date;
    public boolean read;
    public boolean deletedBySender;
    public boolean deletedByReceiver;

    public Message(String fromUser, String toUser, String subject, String message){
        this.id = UUID.randomUUID().toString();
        this.date = Utils.formatDateToCustomPattern(new Date());
        this.fromUser = fromUser;
        this.toUser = toUser;
        this.subject = subject;
        this.message = message;
        this.read = false;
        this.deletedBySender = false;
        this.deletedByReceiver = false;
    }

}
