/**
 * 
 * @author Zhaohe Zhang
 *
 * 
 */
public class DuplicatedKeyException extends Exception
{
	/**
	 * Sets up this exception with an appropriate message.
	 * @param message
	 */
	public DuplicatedKeyException(String message)
	{
		super(message + " is duplicate!");
	}
}
