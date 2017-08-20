package bm.block_handling;

import java.util.ArrayList;
import java.util.List;

import bm.block_handling.blocks.Block;
import bm.block_handling.blocks.Tile;

public class BlockProcessor
{
	private Block block;

	private List<Tile> newTiles = new ArrayList<>();
	private List<Tile> currentTiles = new ArrayList<>(); // Used to clear block.

	private static BlockProcessor instance;
	public static BlockProcessor getInstance()
	{
		if (instance == null)
		{
			instance = new BlockProcessor();
		}
		return instance;
	}

	private BlockProcessor()
	{
	}

	public boolean hasBlock()
	{
		return (block != null);
	}

	public void getBlockFromPreview()
	{
		block = ControllerPreview.getInstance().getBlock();
		block.setOffsetRow(calculateOffsetRow());

		currentTiles.clear();
		newTiles = block.getTiles();
	}

	private int calculateOffsetRow() // Compensate the block's initial row-offset.
	{
		List<Tile> tiles = block.getTiles(); // TODO : check ; is dit hetzelfde als 'Tile[] tiles = block.regularViews[0]' ?
		int offsetRow = tiles.get(0).getRow();

		for (Tile tile : tiles)
		{
			int row = tile.getRow();
			if (row < offsetRow)
			{
				offsetRow = row;
			}
		}

		offsetRow = 0 - offsetRow;
		return offsetRow;
	}

	public void removeBlock()
	{
		block = null;
	}

}
