import java.rmi.*;
import java.util.*;
//java -Djava.security.policy=no.policy ChatClient
public class ChatClient {
  protected static ChatBot netConn = null;

  public static void main(String args[]) {
        
    System.setSecurityManager(new RMISecurityManager());

    try {

      netConn = (ChatBot)Naming.lookup(ChatBot.LOOKUPNAME);
	String answer = netConn.getChatBot(args[0]);
	 System.out.println("Respuesta de ChatBot: " + answer.toString( ));
    } 
    catch (Exception e) {
      System.out.println("Exception in main: " + e);
      e.printStackTrace( );
    }

  }

}
