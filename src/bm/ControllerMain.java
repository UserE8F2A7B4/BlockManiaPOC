package bm;

import bm.BlockManiaPOC.UserInput;
import bm.block_handling.ControllerField;
import bm.block_handling.blocks.Tile;
import bm.util.GlobalData;
import java.util.ArrayList;
import java.util.List;

public class ControllerMain
{
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
	}

	public void init()
	{
		gameState = GameState.BLOCK_HANDLING;
	}

	public synchronized void setUserRequest(UserInput req)
	{
		// System.out.println(String.format("UserRequest : %s ", req));
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

	public void testRowRemoval()
	{
		// Maak een row aan op het veld.

		List<Tile> tilesEmpty = new ArrayList<>();
		List<Tile> tilesNew = new ArrayList<>();

		for (int i = 0 ; i < GlobalData.COLS ; i++)
		{
			tilesNew.add(new Tile(4, i,15));
		}

		for (int i = 0 ; i < GlobalData.COLS ; i++)
		{
			tilesNew.add(new Tile(5, i,15));
		}

		for (int i = 0 ; i < GlobalData.COLS ; i++)
		{
			tilesNew.add(new Tile(7, i,15));
		}

		ControllerField cf = ControllerField.getInstance();
		cf.tryToPlaceNewTilesOnField(tilesEmpty, tilesNew);

		changeGameState(GameState.LINE_REMOVAL);

	}


}
