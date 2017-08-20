package bm.block_handling.blocks;

public abstract class Block
{
	//private Engine engine;
	protected int blockNumber;

	private static int VIEW_MIN = 0;
	private static int VIEW_MAX = 3;

	public Tile[][] regularViews;
	public Tile[][] flippedViews;

	private enum ViewMode
	{
		REGULAR, FLIPPED
	}
	private ViewMode currentViewMode;
	private int currentView;

	private Tile[] locationsWanted;
	private Tile[] locationsCurrent; // Used to clear block.

	private int viewWanted;
	private ViewMode viewModeWanted;

	private int offsetRow;
	private int offsetColumn;

	// private int trackNumber;

	public Block()
	{
		//engine = Engine.getInstance();
	}

	private void reset()
	{
		currentViewMode = ViewMode.REGULAR;
		currentView = 0;

		offsetRow = 0;
		offsetColumn = 0;

		locationsCurrent = new Tile[0];
	}

	public void enterPreview()
	{
		reset();
		offsetRow = -1;
		offsetColumn = 1;

		//Location[] locations = getLocations();
		// engine.addPreview(locations);

		Tile[] tiles = getLocations();

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

	// private void setTrackNumber(Tile[] tiles)
	// {
	// trackNumber++;
	// if (trackNumber == Integer.MAX_VALUE)
	// {
	// trackNumber = 1;
	// }
	//
	// for (Tile t : tiles)
	// {
	// t.setTrackNumber(trackNumber);
	// }
	// }

	private int getRowOffset()
	{
		Tile[] locations = regularViews[0];
		int rowOffset = locations[0].getRow();

		for (Tile loc : locations)
		{
			int row = loc.getRow();
			if (row < rowOffset)
			{
				rowOffset = row;
			}
		}
		return rowOffset;
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
		if (currentViewMode == ViewMode.REGULAR)
		{
			nextView = currentView + 1;
			if (nextView > VIEW_MAX)
			{
				nextView = VIEW_MIN;
			}
		}
		else if (currentViewMode == ViewMode.FLIPPED)
		{
			nextView = currentView - 1;
			if (nextView < VIEW_MIN)
			{
				nextView = VIEW_MAX;
			}
		}
		return nextView;
	}

	public Tile[] getLocations() // Used when entering the gamefield.
	{
		final int NO_ROW_OFFSET = 0;
		final int NO_COL_OFFSET = 0;
		return getLocations(NO_ROW_OFFSET, NO_COL_OFFSET, currentView, currentViewMode);
	}

	public Tile[] getLocations(int rowOffset, int colOffset) // Used with moving.
	{
		return getLocations(rowOffset, colOffset, currentView, currentViewMode);
	}

	private Tile[] getLocations(int wantedView) // Used with 'rotate'.
	{
		final int NO_ROW_OFFSET = 0;
		final int NO_COL_OFFSET = 0;
		return getLocations(NO_ROW_OFFSET, NO_COL_OFFSET, wantedView, currentViewMode);
	}

	private Tile[] getLocations(ViewMode viewMode) // Used with 'flip'.
	{
		final int NO_ROW_OFFSET = 0;
		final int NO_COL_OFFSET = 0;
		return getLocations(NO_ROW_OFFSET, NO_COL_OFFSET, currentView, viewMode);
	}

	private Tile[] getLocations(int rowOffset, int colOffset, int view, ViewMode viewMode)
	{
		Tile[] locationStart = (viewMode == ViewMode.REGULAR) ? regularViews[view] : flippedViews[view];
		int length = locationStart.length;

		Tile[] locationActual = new Tile[length];
		for (int i = 0 ; i < length ; i++)
		{
			Tile loc = locationStart[i];
			int row = loc.getRow() + offsetRow + rowOffset;
			int col = loc.getCol() + offsetColumn + colOffset;
			locationActual[i] = new Tile(row, col, blockNumber);
		}
		return locationActual;
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
