package bm.game_states.user_play;

import bm.util.GlobalData;
import bm.util.blocks.Tile;
import bm.game_states.user_play.Canvas.Field.ControllerFieldUserPlay;

import bm.game_states.user_play.line_removal.ControllerLineRemoval;

import java.util.ArrayList;
import java.util.List;

public class ControllerUserPlay
{
	private static ControllerUserPlay instance;

	public static long gameTickCounter;

	private GameStateUserPlay gameStateUserPlay;

	private FlowUserPlay flowUserPlay;
	private ControllerLineRemoval cl;

	public enum GameStateUserPlay
	{
		BLOCK_HANDLING, LINE_REMOVAL
	}

	public static ControllerUserPlay getInstance()
	{
		if (instance == null)
		{
			instance = new ControllerUserPlay();
		}
		return instance;
	}

	private ControllerUserPlay()
	{
	}

	public void init()
	{
		flowUserPlay = FlowUserPlay.getInstance();
		flowUserPlay.init();

		cl = ControllerLineRemoval.getInstance();
		cl.init();

		gameStateUserPlay = GameStateUserPlay.BLOCK_HANDLING;
	}

	public void handleGameTick()
	{
		gameTickCounter++;

		switch (gameStateUserPlay)
		{
			case BLOCK_HANDLING : flowUserPlay.handleGameTick() ; break;
			case LINE_REMOVAL   : cl.handleGameTick(); break;
			default             : throw new RuntimeException("Invalid game-State-UserPlay.");
		}
	}

	public void changeGameState(GameStateUserPlay newState)
	{
		gameStateUserPlay = newState;
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

		ControllerFieldUserPlay cf = ControllerFieldUserPlay.getInstance();
		cf.tryToPlaceNewTilesOnField(tilesEmpty, tilesNew);

		changeGameState(GameStateUserPlay.LINE_REMOVAL);
	}

}
