package bm.util;

import java.awt.Color;

public abstract class GlobalData
{
	public final static String APPLICATION_NAME = "BlockManiaPOC";

	private static GameMode gameMode = GameMode.CLASSIC;

	public static enum GameMode
	{
		CLASSIC, EXTENDED
	}

	// public static String PATH_IMAGES, PATH_SOUNDS;

	public final static Color BACKGROUND_COLOR = Color.BLACK;

	public final static int BLOCK_SIZE = 20;

	public final static int COLS = 10;
	public final static int ROWS = 17;

	public final static int GAME_FIELD_WIDTH  = COLS * BLOCK_SIZE;
	public final static int GAME_FIELD_HEIGHT = ROWS * BLOCK_SIZE;

	public final static int TILE_COLOR_RED = 100;
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
