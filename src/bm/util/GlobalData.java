package bm.util;

import java.awt.Color;

public abstract class GlobalData
{
	public final static String APPLICATION_NAME = "BlockManiaPOC";

	private static GameMode gameMode = GameMode.CLASSIC;

	public enum GameMode
	{
		CLASSIC, EXTENDED
	}

	// public static String PATH_IMAGES, PATH_SOUNDS;

	public final static Color BACKGROUND_COLOR = Color.BLACK;

	public final static int BLOCK_SIZE = 20;

	public final static int ROW_COUNT = 17;
	public final static int COL_COUNT = 10;

	public final static int ROW_MIN = 0;
	public final static int COL_MIN = 0;

	public final static int ROW_MAX = ROW_COUNT - 1;
	public final static int COL_MAX = COL_COUNT - 1;

	public final static int GAME_FIELD_WIDTH  = COL_COUNT * BLOCK_SIZE;
	public final static int GAME_FIELD_HEIGHT = ROW_COUNT * BLOCK_SIZE;

	public final static int TILE_COLOR_RED = 100; // TODO waarom zijn dit niet gewoon Colors ?
	public final static int TILE_COLOR_WHITE = 101;


	//	public static void setGameMode(GameMode mode)
	//	{
	//		gameMode = mode;
	//	}

	public static GameMode getGameMode()
	{
		return gameMode;
	}

}
