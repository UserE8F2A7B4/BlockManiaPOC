package bm.block_handling.blocks;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public abstract class Block
{
	protected int blockNumber;

	private static int VIEW_MIN = 0;
	private static int VIEW_MAX = 3;

	Tile[][] regularViews;
	Tile[][] flippedViews;

	private enum ViewMode
	{
		REGULAR, FLIPPED
	}
	private ViewMode viewMode; // 'Regular' or 'Flipped'.
	private int rotationIndex; // 'Not rotated', '1-step-rotated', etc.

	private int offsetRow;
	private int offsetColumn;

	public Block()
	{
		viewMode = ViewMode.REGULAR;
		rotationIndex = 0;

		offsetRow = 0;
		offsetColumn = 0;
	}

	//	public boolean canEnterGame()
	//	{
	//		reset();
	//
	//		rowOffsetCurrent = 0 - getRowOffset(); // Compensate the block's initial row-offset.
	//		colOffsetCurrent = (GlobalData.COLS - getBlockWidth()) / 2; // Center the block.
	//
	//		locationsWanted = getLocations();
	//		return engine.areLocationsEmpty(locationsWanted, locationsCurrent);
	//	}

	//	public Command enterGame()
	//	{
	//		engine.setLocations(locationsWanted);
	//		locationsCurrent = locationsWanted;
	//
	//		// setTrackNumber(locationsCurrent);
	//
	//		Command command = new Command();
	//		command.commands.add(GameCommand.ADD_BLOCK);
	//		command.tiles.add(locationsCurrent);
	//		return command;
	//	}

	//	private int getRowOffset()
	//	{
	//		Tile[] locations = regularViews[0];
	//		int rowOffset = locations[0].getRow();
	//
	//		for (Tile loc : locations)
	//		{
	//			int row = loc.getRow();
	//			if (row < rowOffset)
	//			{
	//				rowOffset = row;
	//			}
	//		}
	//		return rowOffset;
	//	}

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

	//	public boolean canMoveLeft()
	//	{
	//		locationsWanted = getLocations(0, -1);
	//		return engine.isWithinBoundaries(locationsWanted) && engine.areLocationsEmpty(locationsWanted, locationsCurrent);
	//	}

	//	public Command moveLeft()
	//	{
	//		engine.clearLocations(locationsCurrent);
	//		engine.setLocations(locationsWanted);
	//
	//		Command command = createReplaceCommand(locationsCurrent, locationsWanted);
	//
	//		locationsCurrent = locationsWanted;
	//		colOffsetCurrent--;
	//
	//		return command;
	//	}

	//	public boolean canMoveRight()
	//	{
	//		locationsWanted = getLocations(0, 1);
	//		return engine.isWithinBoundaries(locationsWanted) && engine.areLocationsEmpty(locationsWanted, locationsCurrent);
	//	}

	//	public Command moveRight()
	//	{
	//		engine.clearLocations(locationsCurrent);
	//		engine.setLocations(locationsWanted);
	//
	//		Command command = createReplaceCommand(locationsCurrent, locationsWanted);
	//
	//		locationsCurrent = locationsWanted;
	//		colOffsetCurrent++;
	//
	//		return command;
	//	}

	//	public boolean canMoveUp()
	//	{
	//		locationsWanted = getLocations(-1, 0);
	//		return engine.isWithinBoundaries(locationsWanted) && engine.areLocationsEmpty(locationsWanted, locationsCurrent);
	//	}

	//	public Command moveUp()
	//	{
	//		engine.clearLocations(locationsCurrent);
	//		engine.setLocations(locationsWanted);
	//
	//		Command command = createReplaceCommand(locationsCurrent, locationsWanted);
	//
	//		locationsCurrent = locationsWanted;
	//		rowOffsetCurrent--;
	//
	//		return command;
	//	}

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

	private int getNextView()
	{
		int nextView = 0;
		if (viewMode == ViewMode.REGULAR)
		{
			nextView = rotationIndex + 1;
			if (nextView > VIEW_MAX)
			{
				nextView = VIEW_MIN;
			}
		}
		else if (viewMode == ViewMode.FLIPPED)
		{
			nextView = rotationIndex - 1;
			if (nextView < VIEW_MIN)
			{
				nextView = VIEW_MAX;
			}
		}
		return nextView;
	}

	public List<Tile> getTiles() // Used when entering the gamefield.
	{
		final int NO_ROW_OFFSET = 0;
		final int NO_COL_OFFSET = 0;
		return getTiles(NO_ROW_OFFSET, NO_COL_OFFSET, rotationIndex, viewMode);
	}

	public List<Tile> getTiles(int offsetRowRequested, int offsetColumnRequested) // Used with 'Move'.
	{
		return getTiles(offsetRowRequested, offsetColumnRequested, rotationIndex, viewMode);
	}

	private List<Tile> getTiles(int rotationIndexRequested) // Used with 'Rotate'.
	{
		final int NO_ROW_OFFSET = 0;
		final int NO_COL_OFFSET = 0;
		return getTiles(NO_ROW_OFFSET, NO_COL_OFFSET, rotationIndexRequested, viewMode);
	}

	private List<Tile> getTiles(ViewMode viewModeRequested) // Used with 'Flip'.
	{
		final int NO_ROW_OFFSET = 0;
		final int NO_COL_OFFSET = 0;
		return getTiles(NO_ROW_OFFSET, NO_COL_OFFSET, rotationIndex, viewModeRequested);
	}

	private List<Tile> getTiles(int offsetRowRequested, int offsetColumnRequested, int rotationIndexRequested, ViewMode viewModeRequested)
	{
		List<Tile> tiles = (viewModeRequested == ViewMode.REGULAR) ? Arrays.asList(regularViews[rotationIndexRequested]) : Arrays.asList(flippedViews[rotationIndexRequested]);
		List<Tile> tilesWithOffset = new ArrayList<>(tiles.size());

		for (Tile tile : tiles)
		{
			int row = tile.getRow() + offsetRow + offsetRowRequested;
			int col = tile.getCol() + offsetColumn + offsetColumnRequested;
			tilesWithOffset.add(new Tile(row, col, blockNumber));
		}

		return tilesWithOffset;
	}

	public int getOffsetRow()
	{
		return offsetRow;
	}

	public void setOffsetRow(int offsetRow)
	{
		this.offsetRow = offsetRow;
	}

	public int getOffsetColumn()
	{
		return offsetColumn;
	}

	public void setOffsetColumn(int offsetColumn)
	{
		this.offsetColumn = offsetColumn;
	}

	//	private Command createReplaceCommand(Tile[] current, Tile[] wanted)
	//	{
	//		Command command = new Command();
	//		command.commands.add(GameCommand.REMOVE_BLOCK);
	//		command.tiles.add(current);
	//		command.commands.add(GameCommand.ADD_BLOCK);
	//		command.tiles.add(wanted);
	//		return command;
	//	}

	// Let the ControllerUserPlay handle the drawing.
	// public void drawItem()
	// {
	// canvas.setColor(Color.ORANGE);
	// canvas.fillRect(0, 0, View.CANVAS_WIDTH, View.CANVAS_HEIGHT);
	//
	// canvas.setColor(Color.red);
	// canvas.fillRect(X, Y, 20, 20);
	//
	// controller.displayImage(image);
	// }

}
