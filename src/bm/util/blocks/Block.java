package bm.util.blocks;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public abstract class Block
{
	protected int blockId;

	private static final int ROTATION_INDEX_MIN = 0;
	private static final int ROTATION_INDEX_MAX = 3;

	private static final int ROW_OFFSET_NONE = 0;
	private static final int COL_OFFSET_NONE = 0;

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
		return getTiles(ROW_OFFSET_NONE, COL_OFFSET_NONE, rotationIndex, rotationMode);
	}

	public List<Tile> getTilesWithOffset(int offsetRowRequested, int offsetColumnRequested) // Used with 'Move'.
	{
		return getTiles(offsetRowRequested, offsetColumnRequested, rotationIndex, rotationMode);
	}

	public List<Tile> getTilesWithRotationIndex(int rotationIndexRequested) // Used with 'Rotate'.
	{
		return getTiles(ROW_OFFSET_NONE, COL_OFFSET_NONE, rotationIndexRequested, rotationMode);
	}

	public List<Tile> getTilesWithRotationMode(RotationMode rotationModeRequested) // Used with 'Flip'.
	{
		return getTiles(ROW_OFFSET_NONE, COL_OFFSET_NONE, rotationIndex, rotationModeRequested);
	}

	private List<Tile> getTiles(int offsetRowRequested, int offsetColumnRequested, int rotationIndexRequested, RotationMode viewModeRequested)
	{
		List<Tile> tiles = (viewModeRequested == RotationMode.REGULAR) ? Arrays.asList(regularViews[rotationIndexRequested]) : Arrays.asList(flippedViews[rotationIndexRequested]);
		List<Tile> tilesWithOffset = new ArrayList<>(tiles.size());

		// System.out.println(String.format("offsetRowBase / offsetRow : %s / %s ", offsetRowBase, offsetRow));

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
