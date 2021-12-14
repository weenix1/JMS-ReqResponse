import javax.jms.JMSException;

/**
 * Write a description of class Server here.
 *
 * @author Wolfgang Renz
 * @version May 2020
 */
public class Server
{
    public static void main(String [] args) throws JMSException
    {
        // Responder responder= new Responder((ref,call)-> (String)call+ " world");
        Responder responder= new Responder(call-> call+ " world!");
        responder.answer();
        System.out.println("responded.");
    }
}
