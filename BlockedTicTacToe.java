
public class BlockedTicTacToe 
{
	private int boardSize;
	private int inLine;
	private int maxLevels;
	private char [][] gameBoard;
	private int DICT_SIZE = 987;
	
	public BlockedTicTacToe(int boardSize, int inLine, int maxLevels)
	{
		this.boardSize = boardSize;
		this.inLine = inLine;
		this.maxLevels = maxLevels;
		gameBoard = new char[boardSize] [boardSize];
	}
	
	public TTTDictionary<TTTRecord> createDictionary()
	{
		TTTDictionary<TTTRecord> newBoard = new TTTDictionary<TTTRecord>(DICT_SIZE);
		return newBoard;
	}
	
	public int repeatedConfig(TTTDictionary configurations)
	{
		String boardConfig = gameBoard.toString();
		int score = -1;
		if(configurations.get(boardConfig) != null)
		{
			score = configurations.get(boardConfig).getScore();
		}
		return score;
	}
	
	public void insertConfig(TTTDictionary configurations, int score, int level)
	{
		String boardConfig = gameBoard.toString();
		TTTRecord newRecord = new TTTRecord(boardConfig, score, level);
		try {
			configurations.put(newRecord);
		} catch (DuplicatedKeyException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	
	public void storePlay(int row, int col, char symbol)
	{
		gameBoard[row] [col] = symbol;
	}
	
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
	
	public boolean wins(char symbol)
	{
		boolean won = false;
		for(int col = 0; col < boardSize; col ++)
		{
			int xCorrect = 0;
			int yCorrect = 0;
			
			for(int row = 0; row < boardSize; row ++)
			{
				if(gameBoard[row] [col] == symbol)
				{
					yCorrect ++;
					if(yCorrect == inLine)
					{
						won = true;
					}
				}
				else
				{
					yCorrect = 0;
				}
				
				if(gameBoard[col] [row] == symbol)
				{
					xCorrect ++;
					if(xCorrect == inLine)
					{
						won = true;
					}
				}
				else 
				{
					xCorrect = 0;
				}
			}
		}
		
		if(!won)
		{
			int limit = (boardSize - inLine) + 1;
			int rightUp = 0;
			int leftUp = 0;
			int leftDown = 0;
			int rightDown = 0;
			for(int i = 0; i < limit; i ++)
			{
				int q = boardSize - 1;
				for(int j = 0; j < boardSize - i; j ++)
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
						rightUp = 0;
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
						leftUp = 0;
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
						leftDown = 0;
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
						rightDown = 0;
					}
					q --;
				}
			}
			
		}
		return won;
	}
	
	public boolean isDraw()
	{
		boolean full = true;
		for(int col = 0; col < boardSize; col ++)
		{
			for(int row = 0; row < boardSize; row ++)
			{
				if(squareIsEmpty(row, col))
				{
					full = false;
				}
			}
		}
		return full;
	}
	
	public int evalBoard()
	{
		int value = 2;
		if(wins('o'))
		{
			value = 3;
		}
		else if(wins('x'))
		{
			value = 0;
		}
		else if(isDraw())
		{
			value = 1;
		}
		return value;
	}
	
	public String toString()
	{
		String result = "";
		for(int col = 0; col < boardSize; col ++)
		{
			for(int row = 0; row < boardSize; row ++)
			{
				result += gameBoard[row] [col] + "";
			}
		}
		return result;
	}
}
