package bm.game_states.user_play.block_handling;

import bm.game_states.user_play.Canvas.Field.ControllerFieldUserPlay;
import bm.game_states.user_play.Canvas.Preview.ControllerPreviewUserPlay;
import bm.util.blocks.Block;
import bm.util.blocks.Block.RotationMode;
import bm.util.blocks.Tile;

import java.util.ArrayList;
import java.util.List;

public class BlockProcessor
{
	private ControllerFieldUserPlay controllerField;
	private ControllerPreviewUserPlay controllerPreview;

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
		controllerField = ControllerFieldUserPlay.getInstance();
		controllerPreview = ControllerPreviewUserPlay.getInstance();
	}

	public boolean hasBlock()
	{
		return (block != null);
	}

	public void removeBlock()
	{
		block = null;
	}

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
			// System.out.println("Cannot Place New Block.");
			return false;
		}
	}

	private void newTilesHaveBecomeCurrentTiles()
	{
		currentTiles = new ArrayList<>(newTiles);
		newTiles.clear();
	}

	private void loadBlockFromPreview()
	{
		block = controllerPreview.getBlock();
		controllerPreview.clearBlock();

		int offsetRowBase = calculateOffsetRow();
		block.setOffsetRowBase(offsetRowBase );

		currentTiles.clear();
		newTiles = block.getTiles();
	}

	private int calculateOffsetRow() // Compensate the block's initial row-offset.
	{
		List<Tile> tiles = block.getTiles();
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

	//	private int calculateOffsetCol()
	//	{
	//		int offsetCol = (GlobalData.COL_COUNT - getBlockWidth()) / 2; // To center the block.
	//		return offsetCol;
	//	}

	private int getBlockWidth()
	{
		List<Tile> tiles = block.getTiles();

		int minCol = tiles.get(0).getCol();
		int maxCol = minCol;

		for (Tile loc : tiles)
		{
			int col = loc.getCol();
			if (col < maxCol)
			{
				minCol = col;
			}
			if (col > maxCol)
			{
				maxCol = col;
			}
		}
		return (maxCol - minCol + 2);
	}

	public boolean tryToMoveBlockLeft()
	{
		newTiles = block.getTilesWithOffset(0, -1);
		if (tryToPlaceNewTilesOnField())
		{
			block.decrementOffsetColumn();
			return true;
		}
		else
		{
			// System.out.println("Cannot move Left.");
			return false;
		}
	}

	public boolean tryToMoveBlockRight()
	{
		newTiles = block.getTilesWithOffset(0, 1);
		if (tryToPlaceNewTilesOnField())
		{
			block.incrementOffsetColumn();
			return true;
		}
		else
		{
			// System.out.println("Cannot move Right.");
			return false;
		}
	}

	public boolean tryToMoveBlockUp()
	{
		newTiles = block.getTilesWithOffset(-1, 0);

		if (tryToPlaceNewTilesOnField())
		{
			block.decrementOffsetRow();
			return true;
		}
		else
		{
			// System.out.println("Cannot move Up.");
			return false;
		}
	}

	public boolean tryToMoveBlockDown()
	{
		newTiles = block.getTilesWithOffset(1, 0);

		if (tryToPlaceNewTilesOnField())
		{
			block.incrementOffsetRow();
			return true;
		}
		else
		{
			return false;
		}
	}

	public boolean tryToRotateBlock()
	{
		int rotationIndexRequested = block.getNextRotationIndex();
		newTiles = block.getTilesWithRotationIndex(rotationIndexRequested);
		if (tryToPlaceNewTilesOnField())
		{
			block.setRotationIndex(rotationIndexRequested);
			return true;
		}
		else
		{
			// System.out.println("Cannot Rotate.");
			return false;
		}
	}

	public boolean tryToFlipBlock()
	{
		RotationMode rotationModeRequested = block.getNextRotationMode();
		newTiles = block.getTilesWithRotationMode(rotationModeRequested);
		if (tryToPlaceNewTilesOnField())
		{
			block.setRotationMode(rotationModeRequested);
			return true;
		}
		else
		{
			// System.out.println("Cannot Flip.");
			return false;
		}
	}

	private boolean tryToPlaceNewTilesOnField()
	{
		if (controllerField.tryToPlaceNewTilesOnField(currentTiles, newTiles))
		{
			newTilesHaveBecomeCurrentTiles();
			return true;
		}
		else
		{
			// System.out.println("Cannot Place New Tiles.");
			return false;
		}
	}

}
