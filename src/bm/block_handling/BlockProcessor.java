package bm.block_handling;

import java.util.ArrayList;
import java.util.List;

import bm.block_handling.blocks.Block;
import bm.block_handling.blocks.Tile;

public class BlockProcessor
{
	private ControllerField controllerField;
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
		controllerField = ControllerField.getInstance();
	}

	public boolean hasBlock()
	{
		return (block != null);
	}

	public void removeBlock()
	{
		block = null;
	}

	// Level 2.

	public boolean tryToPlaceNewBlockOnField()
	{
		loadBlockFromPreview();

		if (controllerField.tryToPlaceNewTilesOnField(currentTiles, newTiles))
		{
			newTilesHaveBecomeCurrentTiles();
			return true;
		}
		else
		{
			return false;
		}
	}

	private void loadBlockFromPreview()
	{
		block = ControllerPreview.getInstance().getBlock();
		block.setOffsetRow(calculateOffsetRow());

		currentTiles.clear();
		newTiles = block.getTiles();
	}

	private void newTilesHaveBecomeCurrentTiles()
	{
		currentTiles = new ArrayList<>(newTiles);
		newTiles.clear();
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

	public boolean tryToMoveBlockLeft()
	{
		int offsetRowRequested = block.getOffsetRow();
		int offsetColumnRequested = block.getOffsetColumn() - 1;

		newTiles = block.getTiles(offsetRowRequested, offsetColumnRequested);
		if (!controllerField.areTilesWithinBoundaries(newTiles))
		{
			return false;
		}

		if (controllerField.tryToPlaceNewTilesOnField(currentTiles, newTiles))
		{
			newTilesHaveBecomeCurrentTiles();
			block.setOffsetColumn(offsetColumnRequested);
			return true;
		}
		else
		{
			return false;
		}
	}

}
