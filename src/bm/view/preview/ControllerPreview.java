package bm.view.preview;

import bm.util.blocks.Block;
import bm.util.blocks.BlockPool;
import bm.util.blocks.Tile;
import bm.view.preview.label.CanvasPreviewLabel;
import bm.view.preview.text.CanvasPreviewText;

public class ControllerPreview
{
	private static ControllerPreview instance;

	private Block block;
	// private Tile[][] field;

	private final static int ROWS = 9;
	private final static int COLS = 9;

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
		CanvasPreviewText.getInstance().renderPreview(field);
		CanvasPreviewLabel.getInstance().renderPreview(field);
	}

}
