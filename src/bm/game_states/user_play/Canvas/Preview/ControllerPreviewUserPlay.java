package bm.game_states.user_play.Canvas.Preview;

import bm.util.blocks.Block;
import bm.util.blocks.BlockPool;
import bm.util.blocks.Tile;

public class ControllerPreviewUserPlay
{
	private static ControllerPreviewUserPlay instance;

	private Block block;
	// private Tile[][] field;

	private final static int ROWS = 9;
	private final static int COLS = 9;

	public static ControllerPreviewUserPlay getInstance()
	{
		if (instance == null)
		{
			instance = new ControllerPreviewUserPlay();
		}
		return instance;
	}

	private ControllerPreviewUserPlay()
	{
		loadRandomBlock();
	}

	public Block getBlock()
	{
		return block;
	}

	public boolean hasBlock()
	{
		return (block != null);
	}

	public void clearBlock()
	{
		block = null;
	}

	public void loadRandomBlock()
	{
		block = BlockPool.getRandomBlock();
		// block = BlockPool.getFixedBlock();

		setTiles();
	}

	public void loadNextBlock()
	{
		block = BlockPool.getNextBlock();
		setTiles();
	}

	private void setTiles()
	{
		Tile[][] field = new Tile[ROWS][COLS];
		for (Tile tile : block.getTiles())
		{
			field[tile.getRow()][tile.getCol()] = tile;
		}

		updateCanvas(field);
	}

	private void updateCanvas(Tile[][] field)
	{
		CanvasPreviewUserPlayText.getInstance().renderPreview(field);
		CanvasPreviewUserPlayLabel.getInstance().renderPreview(field);
	}

}
