package bm;

import bm.BlockManiaPOC.UserInput;
import bm.block_handling.ControllerBlockHandling;
import bm.line_removal.ControllerLineRemoval;

public class ControllerMain
{
	private BlockManiaPOC gui;

	private ControllerBlockHandling controllerBlockHandling;
	private ControllerLineRemoval controllerLineRemoval;

	private GameState gameState;

	public static enum GameState
	{
		BLOCK_HANDLING, LINE_REMOVAL
	}

	private static ControllerMain instance;
	public static ControllerMain getInstance(BlockManiaPOC bm)
	{
		if (instance == null)
		{
			instance = new ControllerMain(bm);
		}
		return instance;
	}

	private ControllerMain(BlockManiaPOC bm)
	{
		gui = bm;

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
		UserInput input = gui.getUserRequest();

		switch (gameState)
		{
			case BLOCK_HANDLING  : controllerBlockHandling.handleGameTick(input) ; break;
			case LINE_REMOVAL    : controllerLineRemoval.handleGameTick() ; break;
			default              : assert false : "Invalid request"; break;
		}

	}


}
