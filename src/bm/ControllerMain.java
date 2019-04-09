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
		// gameState = GameState.LINE_REMOVAL;
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


		for (int i = 0 ; i < GlobalData.COL_COUNT ; i++)
		{
			tilesNew.add(new Tile(16, i,15));
			tilesNew.add(new Tile(14, i,15));
			tilesNew.add(new Tile(12, i,15));

//			tilesNew.add(new Tile(0, i,15));
//			tilesNew.add(new Tile(1, i,15));
//			tilesNew.add(new Tile(2, i,15));
		}

//		tilesNew.add(new Tile(15, 0,15));
//		tilesNew.add(new Tile(15, 1,15));
//		tilesNew.add(new Tile(15, 3,15));
//
//		tilesNew.add(new Tile(13, 3,15));
//		tilesNew.add(new Tile(13, 5,15));
//		tilesNew.add(new Tile(13, 6,15));

		ControllerField cf = ControllerField.getInstance();
		cf.tryToPlaceNewTilesOnField(tilesEmpty, tilesNew);

		changeGameState(GameState.LINE_REMOVAL);
	}

}
