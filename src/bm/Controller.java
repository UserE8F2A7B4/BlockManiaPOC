package bm;

public class Controller
{
	private ControllerBlockHandling controllerBlockHandling;
	private ControllerLineRemoval controllerLineRemoval;

	private GameState gameState;

	public static enum GameState
	{
		BLOCK_HANDLING, LINE_REMOVAL
	}

	private static Controller instance;
	public static Controller getInstance()
	{
		if (instance == null)
		{
			instance = new Controller();
		}
		return instance;
	}

	private Controller()
	{
		controllerBlockHandling = ControllerBlockHandling.getInstance();
		controllerLineRemoval = ControllerLineRemoval.getInstance();

		gameState = GameState.BLOCK_HANDLING;
	}

	public void changeGameState(GameState newGameState)
	{
		gameState = newGameState;
	}

	public void handleGameTick()
	{
		switch (gameState)
		{
			case BLOCK_HANDLING  : controllerBlockHandling.handleGameTick() ; break;
			case LINE_REMOVAL    : controllerLineRemoval.handleGameTick() ; break;
			default              : assert false : "Invalid request"; break;
		}

	}


}
