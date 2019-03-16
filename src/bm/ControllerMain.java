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

	public enum GameState
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

		// GameLoopTimer timer = GameLoopTimer.getInstance();

		gameState = GameState.BLOCK_HANDLING;
	}

	public void setReferenceToGUI(BlockManiaPOC bm)
	{
		gui = bm;
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
