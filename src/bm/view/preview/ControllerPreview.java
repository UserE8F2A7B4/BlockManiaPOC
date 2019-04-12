package bm.view.preview;

import java.util.List;

import bm.util.blocks.Block;
import bm.util.blocks.BlockPool;
import bm.util.blocks.Tile;
import bm.view.preview.text.CanvasPreviewText;

public class ControllerPreview
{
	private Block block;
	private Tile[][] field;

	private final static int ROWS = 9;
	private final static int COLS = 9;

	private static ControllerPreview instance;
	public static ControllerPreview getInstance()
	{
		if (instance == null)
		{
			instance = new ControllerPreview();
		}
		return instance;
	}

	private ControllerPreview()
	{
		loadNextBlock();
	}

	public Block getBlock()
	{
		field = new Tile[ROWS][COLS];
		CanvasPreviewText.getInstance().renderPreview(field);

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
		// block = BlockPool.getRandomBlock();
		block = BlockPool.getFixedBlock();
		render();
	}

	public void loadNextBlock()
	{
		block = BlockPool.getNextBlock();
		render();
	}

	private void render()
	{
		field = new Tile[ROWS][COLS];
		setTiles(block.getTiles());

		CanvasPreviewText.getInstance().renderPreview(field);
	}

	private void setTiles(List<Tile> tiles)
	{
		for (Tile tile : tiles)
		{
			field[tile.getRow()][tile.getCol()] = tile;
		}
	}

}
