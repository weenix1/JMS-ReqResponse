import javax.jms.JMSException;

/**
 * Write a description of class Client here.
 *
 * @author Wolfgang Renz
 * @version May 2020
 */
public class Client
{
    public static void main(String [] args) throws JMSException
    {
        Caller caller= new Caller();
        String response= caller.responseToCall("Hello");
        System.out.println(response);
    }
}
