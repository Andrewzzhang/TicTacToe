/**
 * Class that represents a BlockedTicTacToe game.
 * @author Zhaohe Zhang
 * Date: 2017/10/19
 */

public class BlockedTicTacToe 
{
	/* Attributes Declarations */
	private int boardSize;
	private int inLine;
	private int maxLevels;
	private char [][] gameBoard;
	private int DICT_SIZE = 987;
	
	/**
	 * Constructor creates a gameBoard with given parameter.
	 * @param boardSize the Size X Size of the gameBoard.
	 * @param inLine the number of symbol in one line to consider a win.
	 * @param maxLevels the level of the game.
	 */
	public BlockedTicTacToe(int boardSize, int inLine, int maxLevels)
	{
		this.boardSize = boardSize;
		this.inLine = inLine;
		this.maxLevels = maxLevels;
		gameBoard = new char[boardSize] [boardSize];
	}
	
	/**
	 * createDictionary method create and return a gameBoard.
	 * @return the created gameBoard.
	 */
	public TTTDictionary<TTTRecord> createDictionary()
	{
		TTTDictionary<TTTRecord> newBoard = new TTTDictionary<TTTRecord>(DICT_SIZE);
		return newBoard;
	}
	
	/**
	 * repeatedConfig method check if the configurations is duplicated.
	 * @param configurations to be checked.
	 * @return
	 */
	public int repeatedConfig(TTTDictionary configurations)
	{
		String boardConfig = gameBoard.toString(); // Get the String representation of this gameBoard.
		int score = -1; // The return value is -1 if the record is not found.
		if(configurations.get(boardConfig) != null)
		{
			score = configurations.get(boardConfig).getScore(); // The return value is the configuration's score if found.
		}
		return score;
	}
	
	/**
	 * insertConfig method insert the record into the given dictionary.
	 * @param configurations the dictionary for the record to insert.
	 * @param score the score of this record.
	 * @param level the level of this record.
	 */
	public void insertConfig(TTTDictionary configurations, int score, int level)
	{
		String boardConfig = gameBoard.toString(); // Get the String representation of this gameBoard.
		TTTRecord newRecord = new TTTRecord(boardConfig, score, level);
		try {
			configurations.put(newRecord);
		} catch (DuplicatedKeyException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	
	/**
	 * storePlay method store a step into the gameBoard.
	 * @param row the row of the position.
	 * @param col the column of the position.
	 * @param symbol the symbol determines its computer, player, block or empty.
	 */
	public void storePlay(int row, int col, char symbol)
	{
		gameBoard[row] [col] = symbol;
	}
	
	/**
	 * squareIsEmpty method check if the position is empty.
	 * @param row of the position.
	 * @param col of the position.
	 * @return true or false.
	 */
	public boolean squareIsEmpty(int row, int col)
	{
		if(gameBoard[row] [col] == ' ' || gameBoard[row] [col] == '\u0000')
		{
			return true;
		}
		else
		{
			return false;
		}
	}
	
	/**
	 * wins method determines if there is a winner.
	 * @param symbol determines computer or human.
	 * @return true or false.
	 */
	public boolean wins(char symbol)
	{
		boolean won = false;
		for(int col = 0; col < boardSize; col ++) // Check vertical and horizontal for a win.
		{
			int xCorrect = 0;
			int yCorrect = 0;
			
			for(int row = 0; row < boardSize; row ++)
			{
				if(gameBoard[row] [col] == symbol)
				{
					yCorrect ++;
					if(yCorrect == inLine) // If the amount of same symbol occurs reaches the given inLine, there is a winner.
					{
						won = true;
						break;
					}
				}
				else
				{
					yCorrect = 0; // Reset if the line breaks.
				}
				
				if(gameBoard[col] [row] == symbol)
				{
					xCorrect ++;
					if(xCorrect == inLine) // If the amount of same symbol occurs reaches the given inLine, there is a winner.
					{
						won = true;
						break;
					}
				}
				else 
				{
					xCorrect = 0; // Reset of the line breaks.
				}
			}
		}
		
		if(!won) // If there is not a vertical or horizontal win.
		{
			int limit = (boardSize - inLine) + 1; // The limit of index for the board to have enough position to win for a slop.
			int rightUp = 0; // The counter for moving from diagonal to right up corner.
			int leftUp = 0; // The counter for moving from diagonal to left up corner.
			int leftDown = 0; // The counter for moving from diagonal to left down corner.
			int rightDown = 0; // The counter for moving from diagonal to right down corner.
			
			for(int i = 0; i < limit; i ++) // The index begins from 0 to the limit.
			{
				int q = boardSize - 1; // q is the column index for moving from diagonal to right down corner.
				// Reset to 0 after enter comparison with no win.
				rightUp = 0;
				leftUp = 0;
				leftDown = 0;
				rightDown = 0;
				
				for(int j = 0; j < boardSize - i; j ++) // The index begins for 0 to the board edge.
				{
					if(gameBoard[j] [j + i] == symbol)
					{
						rightUp ++;
						if(rightUp == inLine)
						{
							won = true;
							break;
						}
					}
					else
					{
						rightUp = 0; // Reset if the line breaks.
					}
					
					if(gameBoard[j] [boardSize - 1 - j - i] == symbol)
					{
						leftUp ++;
						if(leftUp == inLine)
						{
							won = true;
							break;
						}
					}
					else
					{
						leftUp = 0; // Reset if the line breaks.
					}
					
					if(gameBoard[j + i] [j] == symbol)
					{
						leftDown ++;
						if(leftDown == inLine)
						{
							won = true;
							break;
						}
					}
					else
					{
						leftDown = 0; // Reset if the line breaks.
					}
					
					if(gameBoard[j + i] [q] == symbol)
					{
						rightDown ++;
						if(rightDown == inLine)
						{
							won = true;
							break;
						}
					}
					else
					{
						rightDown = 0; // Reset if the line breaks.
					}
					q --;
				}
			}
			
		}
		return won;
	}
	
	/**
	 * isDraw method check if no one win and no space left.
	 * @return true or false.
	 */
	public boolean isDraw()
	{
		boolean full = true;
		if(!wins('o') && !wins('x')) // Check if anyone wins.
		{
			for(int col = 0; col < boardSize; col ++) // Looping through all the spaces of the gameBoard.
			{
				for(int row = 0; row < boardSize; row ++)
				{
					if(squareIsEmpty(row, col)) // Check if the space is empty.
					{
						full = false;
					}
				}
			}
		}
		else
		{
			full = false;
		}
		return full;
	}
	
	/**
	 * evalBoard method judge the situation and return the value present the priority.
	 * @return an integer represent the priority.
	 */
	public int evalBoard()
	{
		if(wins('x'))
		{
			return 0;
		}
		else if(wins('o'))
		{
			return 3;
		}
		else if(isDraw())
		{
			return 1;
		}
		else
		{
			return 2;
		}
	}
	
	/**
	 * toString method generates a string representation of the gameBoard.
	 * @return the string representation.
	 */
	public String toString()
	{
		String result = "";
		for(int col = 0; col < boardSize; col ++) // Loop over the entire board.
		{
			for(int row = 0; row < boardSize; row ++)
			{
				result += gameBoard[row] [col] + "";
			}
		}
		return result;
	}
}
