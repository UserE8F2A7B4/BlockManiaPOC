package bm.general;

public class GameField
{
	private static GameField instance;
	public static GameField getInstance()
	{
		if (instance == null)
		{
			instance = new GameField();
		}
		return instance;
	}

	private GameField()
	{
	}
}
