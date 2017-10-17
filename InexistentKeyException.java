/**
 * 
 * @author Zhaohe Zhang
 *
 *
 */
public class InexistentKeyException extends Exception
{
	/**
	 * Sets up this exception with an appropriate message.
	 * @param message
	 */
	public InexistentKeyException(String message)
	{
		super(message + " ins inexist!");
	}
}
