import java.rmi.*;
import java.util.Date;


public interface ChatBot extends java.rmi.Remote {

    public String getChatBot(String s) throws java.rmi.RemoteException;
    public final static String LOOKUPNAME = "ChatBot";
}