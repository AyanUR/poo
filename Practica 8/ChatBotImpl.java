import java.rmi.*;
import java.rmi.server.*;
import java.net.*;
import java.util.*;


public class ChatBotImpl extends UnicastRemoteObject implements ChatBot {
  String Preguntas[];
  String Respuestas[];
  public ChatBotImpl() throws RemoteException {
    super();
  }
    
public String getChatBot(String s) throws RemoteException {
    Preguntas= new String[10];
	Preguntas[0]="Hola";
	Preguntas[1]="Cuantos_años_tienes?";
	Preguntas[2]="En_que_escuela_estudias?";
	Preguntas[3]="Cuantas_materias_llevas?";
	Preguntas[4]="Como_te_llamas?";
	Preguntas[5]="De_que_numero_calzas?";
	Preguntas[6]="En_que_ciudad_vives?";
	Preguntas[7]="Como_se_llama_tu_hermana?";
	Preguntas[8]="Que_deporte_haces?";
	Preguntas[9]="Que_te_gusta_hacer?";
	Respuestas= new String[10];
	Respuestas[0]="Que Tal";
	Respuestas[1]="18";
	Respuestas[2]="ESCOM";
	Respuestas[3]="6";
	Respuestas[4]="Axel";
	Respuestas[5]="6 1/2";
	Respuestas[6]="D.F.";
	Respuestas[7]="Atali";
	Respuestas[8]="Ninguno";
	Respuestas[9]="Tocas la guitarra";
	for(int i=0;i<Preguntas.length;i++)
	{
		if(s.equals(Preguntas[i]))
		{
			return Respuestas[i];
		}
	}
	return "No tengo respuesta para eso";
}

  public static void main(String args[]) {

    try {
      ChatBotImpl im = new ChatBotImpl();
      Naming.rebind(ChatBot.LOOKUPNAME, im);
	 System.out.println("ChatBot ready.");
    }
     catch (RemoteException re) {
      System.out.println("Exception in RemoteImpl.main: " + re);
      System.exit(1);
    }
    catch (MalformedURLException e) {
      System.out.println("MalformedURLException in RemoteImpl.main: " + e);
     System.exit(1);
    }

  }

}
