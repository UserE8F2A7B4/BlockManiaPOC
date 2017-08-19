package bm;

import bm.block_handling.ControllerBlockHandling;
import bm.line_removal.ControllerLineRemoval;

public class ControllerMain
{
	private ControllerBlockHandling controllerBlockHandling;
	private ControllerLineRemoval controllerLineRemoval;

	private GameState gameState;

	public static enum GameState
	{
		BLOCK_HANDLING, LINE_REMOVAL
	}

	private static ControllerMain instance;
	public static ControllerMain getInstance()
	{
		if (instance == null)
		{
			instance = new ControllerMain();
		}
		return instance;
	}

	private ControllerMain()
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
		// TODO : check for user-input (?).

		// UserInput request = gui.getUserRequest();

		switch (gameState)
		{
			case BLOCK_HANDLING  : controllerBlockHandling.handleGameTick() ; break;
			case LINE_REMOVAL    : controllerLineRemoval.handleGameTick() ; break;
			default              : assert false : "Invalid request"; break;
		}

	}


}
