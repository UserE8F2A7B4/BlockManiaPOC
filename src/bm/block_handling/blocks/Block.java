package bm.block_handling.blocks;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public abstract class Block
{
	protected int blockId;

	private static final int ROTATION_INDEX_MIN = 0;
	private static final int ROTATION_INDEX_MAX = 3;

	Tile[][] regularViews;
	Tile[][] flippedViews;

	public enum RotationMode
	{
		REGULAR, FLIPPED
	}
	private RotationMode rotationMode; // 'Regular' or 'Flipped'.
	private int rotationIndex; // 'Not rotated', '1-step-rotated', etc.

	private int offsetRowBase, offsetRow;
	private int offsetColumnBase, offsetColumn;

	public Block()
	{
		rotationMode = RotationMode.REGULAR;
		rotationIndex = 0;

		offsetRow = 0;
		offsetColumn = 0;
	}

	private int getBlockWidth()
	{
		Tile[] locations = regularViews[0];
		int minCol = locations[0].getCol();
		int maxCol = minCol;

		for (Tile loc : locations)
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
		return (maxCol + 1 - minCol); // +1 because it's zero-based.
	}


	//	public boolean canMoveDown()
	//	{
	//		locationsWanted = getLocations(1, 0);
	//		boolean canMove = engine.isWithinBoundaries(locationsWanted) && engine.areLocationsEmpty(locationsWanted, locationsCurrent);
	//		if (!canMove)
	//		{
	//			engine.setLocations(locationsCurrent); // Het block blijft nu verder op deze positie staan.
	//		}
	//		return canMove;
	//	}

	//	public Command moveDown()
	//	{
	//		engine.clearLocations(locationsCurrent);
	//		engine.setLocations(locationsWanted);
	//
	//		Command command = createReplaceCommand(locationsCurrent, locationsWanted);
	//
	//		locationsCurrent = locationsWanted;
	//		rowOffsetCurrent++;
	//
	//		return command;
	//	}

	//	public boolean canRotate()
	//	{
	//		viewWanted = getNextView();
	//		locationsWanted = getLocations(viewWanted);
	//		return engine.isWithinBoundaries(locationsWanted) && engine.areLocationsEmpty(locationsWanted, locationsCurrent);
	//	}

	//	public Command rotate()
	//	{
	//		engine.clearLocations(locationsCurrent);
	//		engine.setLocations(locationsWanted);
	//
	//		Command command = createReplaceCommand(locationsCurrent, locationsWanted);
	//
	//		locationsCurrent = locationsWanted;
	//		currentView = viewWanted;
	//
	//		return command;
	//	}

	//	public boolean canFlip()
	//	{
	//		viewModeWanted = (currentViewMode == ViewMode.REGULAR) ? ViewMode.FLIPPED : ViewMode.REGULAR;
	//		locationsWanted = getLocations(viewModeWanted);
	//		return engine.isWithinBoundaries(locationsWanted) && engine.areLocationsEmpty(locationsWanted, locationsCurrent);
	//	}

	//	public Command flip()
	//	{
	//		engine.clearLocations(locationsCurrent);
	//		engine.setLocations(locationsWanted);
	//
	//		Command command = createReplaceCommand(locationsCurrent, locationsWanted);
	//
	//		locationsCurrent = locationsWanted;
	//		currentViewMode = viewModeWanted;
	//
	//		return command;
	//	}

	public RotationMode getNextRotationMode()
	{
		return (rotationMode == RotationMode.REGULAR) ? RotationMode.FLIPPED : RotationMode.REGULAR;
	}

	public void setRotationMode(RotationMode mode)
	{
		rotationMode = mode;
	}

	public int getNextRotationIndex()
	{
		int nextRotationIndex = 0;
		if (rotationMode == RotationMode.REGULAR)
		{
			nextRotationIndex = rotationIndex + 1;
			if (nextRotationIndex > ROTATION_INDEX_MAX)
			{
				nextRotationIndex = ROTATION_INDEX_MIN;
			}
		}
		else if (rotationMode == RotationMode.FLIPPED)
		{
			nextRotationIndex = rotationIndex - 1;
			if (nextRotationIndex < ROTATION_INDEX_MIN)
			{
				nextRotationIndex = ROTATION_INDEX_MAX;
			}
		}
		return nextRotationIndex;
	}

	public void setRotationIndex(int index)
	{
		rotationIndex = index;
	}


	public List<Tile> getTiles() // Used when entering the gamefield.
	{
		final int NO_ROW_OFFSET = 0;
		final int NO_COL_OFFSET = 0;
		return getTiles(NO_ROW_OFFSET, NO_COL_OFFSET, rotationIndex, rotationMode);
	}

	public List<Tile> getTilesWithOffset(int offsetRowRequested, int offsetColumnRequested) // Used with 'Move'.
	{
		return getTiles(offsetRowRequested, offsetColumnRequested, rotationIndex, rotationMode);
	}

	public List<Tile> getTilesWithRotationIndex(int rotationIndexRequested) // Used with 'Rotate'.
	{
		final int NO_ROW_OFFSET = 0;
		final int NO_COL_OFFSET = 0;
		return getTiles(NO_ROW_OFFSET, NO_COL_OFFSET, rotationIndexRequested, rotationMode);
	}

	public List<Tile> getTilesWithRotationMode(RotationMode rotationModeRequested) // Used with 'Flip'.
	{
		final int NO_ROW_OFFSET = 0;
		final int NO_COL_OFFSET = 0;
		return getTiles(NO_ROW_OFFSET, NO_COL_OFFSET, rotationIndex, rotationModeRequested);
	}

	private List<Tile> getTiles(int offsetRowRequested, int offsetColumnRequested, int rotationIndexRequested, RotationMode viewModeRequested)
	{
		List<Tile> tiles = (viewModeRequested == RotationMode.REGULAR) ? Arrays.asList(regularViews[rotationIndexRequested]) : Arrays.asList(flippedViews[rotationIndexRequested]);
		List<Tile> tilesWithOffset = new ArrayList<>(tiles.size());

		for (Tile tile : tiles)
		{
			int row = tile.getRow() + offsetRowBase + offsetRow + offsetRowRequested;
			int col = tile.getCol() + offsetColumnBase + offsetColumn + offsetColumnRequested;
			tilesWithOffset.add(new Tile(row, col, blockId));
		}

		return tilesWithOffset;
	}

	public void setOffsetRowBase(int offset)
	{
		this.offsetRowBase = offset;
	}

	public void incrementOffsetRow()
	{
		offsetRow++;
	}

	public void decrementOffsetRow()
	{
		offsetRow--;
	}

	public void setOffsetColumnBase(int offset)
	{
		this.offsetColumnBase = offset;
	}

	public void incrementOffsetColumn()
	{
		offsetColumn++;
	}

	public void decrementOffsetColumn()
	{
		offsetColumn--;
	}

}
