package bm.game_states.user_play.canvas.preview;

import bm.util.CanvasHandling;
import bm.util.blocks.Block;
import bm.util.blocks.BlockPool;
import bm.util.blocks.Tile;

public class ControllerPreviewUserPlay implements CanvasHandling
{
	private static ControllerPreviewUserPlay instance;

	private CanvasPreviewUserPlayText ct;
	private CanvasPreviewUserPlayLabel cl;


	private Block block;

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
	}

	public void init()
	{
		initCanvas();
		loadRandomBlock();
	}

	@Override
	public void initCanvas()
	{
		ct = CanvasPreviewUserPlayText.getInstance();
		cl = CanvasPreviewUserPlayLabel.getInstance();
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

		ct.drawTilesAsText(field);
		cl.drawTiles(field);
		render();
	}

	@Override
	public void render()
	{
		ct.render();
		cl.render();
	}

}
