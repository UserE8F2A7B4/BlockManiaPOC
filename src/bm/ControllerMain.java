package bm;

import bm.BlockManiaPOC.UserInput;
//import bm.block_handling.BlockProcessor;
//import bm.block_handling.ControllerBlockHandling;
//import bm.block_handling.ControllerField;
//import bm.block_handling.ControllerPreview;
//import bm.line_removal.ControllerLineRemoval;
//import javax.swing.*;

public class ControllerMain
{
	// private BlockManiaPOC gui;
	// private ControllerBlockHandling controllerBlockHandling;
	// private ControllerLineRemoval controllerLineRemoval;
	// private ControllerPreview controllerPreview;
	// private ControllerField controllerField;
	// private BlockProcessor blockProcessor;

	private UserInput request = UserInput.NONE;
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
		// controllerBlockHandling = ControllerBlockHandling.getInstance();
		// controllerLineRemoval = ControllerLineRemoval.getInstance();
		// controllerPreview = ControllerPreview.getInstance();
		// blockProcessor = BlockProcessor.getInstance();
		// controllerField = ControllerField.getInstance();
		// GameLoopTimer timer = GameLoopTimer.getInstance();

		gameState = GameState.BLOCK_HANDLING;
	}

	//	public void setReferenceToGUI(BlockManiaPOC bm)
	//	{
	//		gui = bm;
	//	}

	public synchronized void setUserRequest(UserInput req)
	{
		request = req;
	}

	public synchronized UserInput getUserRequest()
	{
		return request;
	}

	public void changeGameState(GameState newGameState)
	{
		gameState = newGameState;
	}

	public GameState getGameState()
	{
		return gameState;
	}

	public void updateScore()
	{
	}

}
