package bm.block_handling;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import bm.block_handling.blocks.Tile;
import bm.util.GlobalData;
import bm.view.CanvasField;

public class ControllerField
{
	private Tile[][] field = new Tile[GlobalData.ROWS][GlobalData.COLS];

	private final static int ROW_MIN = 0;
	private final static int COL_MIN = 0;

	private static ControllerField instance;
	public static ControllerField getInstance()
	{
		if (instance == null)
		{
			instance = new ControllerField();
		}
		return instance;
	}

	private ControllerField()
	{
	}

	public boolean tryToPlaceNewTilesOnField(List<Tile> currentTiles, List<Tile> newTiles)
	{
		if (areLocationsEmpty(currentTiles, newTiles))
		{
			placeNewTilesOnField(currentTiles, newTiles);
			return true;
		}
		else
		{
			return false;
		}
	}

	private boolean areLocationsEmpty(List<Tile> currentTiles, List<Tile> newTiles)
	{
		boolean areEmpty = true;

		for (Tile newTile : newTiles)
		{
			if (!isLocationEmpty(newTile.getRow(), newTile.getCol(), currentTiles))
			{
				areEmpty = false;
				break;
			}
		}
		return areEmpty;
	}

	private boolean isLocationEmpty(int row, int col, List<Tile> currentTiles)
	{
		boolean isEmpty = false;
		boolean withinBoundaries = isWithinBoundaries(row, col);
		if (withinBoundaries)
		{
			Tile wantedTile = field[row][col];
			isEmpty = (wantedTile == null || isLocationOccupiedByAnyOfTheCurrentTiles(currentTiles, wantedTile));
		}
		return isEmpty;
	}

	private boolean isWithinBoundaries(int row, int col)
	{
		boolean withinBoundaries = (row >= ROW_MIN && row < GlobalData.ROWS && col >= COL_MIN && col < GlobalData.COLS);
		return withinBoundaries;
	}

	private boolean isLocationOccupiedByAnyOfTheCurrentTiles(List<Tile> currentTiles, Tile newTile)
	{
		boolean isOccupied = false;
		for (Tile currentTile : currentTiles)
		{
			if (newTile == currentTile)
			{
				isOccupied = true;
				break;
			}
		}
		return isOccupied;
	}

	private void placeNewTilesOnField(List<Tile> currentTiles, List<Tile> newTiles)
	{
		clearTiles(currentTiles);
		setTiles(newTiles);
		// TODO Update canvas.

		CanvasField.getInstance().renderField(field);
	}

	public void clearAllTiles()
	{
		for (int row = 0 ; row < field.length ; row++)
		{
			Arrays.fill(field[row], null);
		}
	}

	private void clearTiles(List<Tile> tiles)
	{
		for (Tile tile : tiles)
		{
			field[tile.getRow()][tile.getCol()] = null;
		}
	}

	private void setTiles(List<Tile> tiles)
	{
		for (Tile tile : tiles)
		{
			field[tile.getRow()][tile.getCol()] = tile;
		}
	}

	// Deze is nodig om te checken of het block gedraaid of gespiegeld kan gaan worden
	// en of het block de onderkant bereikt heeft.
	//
	//	private boolean areTilesWithinBoundaries(List<Tile> tiles)
	//	{
	//		// Check if all tiles are within the boundaries of the gamefield.
	//		boolean areWithin = true;
	//
	//		for (Tile tile : tiles)
	//		{
	//			if (!isWithinBoundaries(tile.getRow(), tile.getCol()))
	//			{
	//				areWithin = false;
	//				break;
	//			}
	//		}
	//		return areWithin;
	//	}

	public boolean hasCompletedRows()
	{
		List<Integer> completedRows = getCompletedRows();
		return !completedRows.isEmpty();
	}

	public List<Integer> getCompletedRows()
	{
		List<Integer> completedRows = new ArrayList<>(15); // '15' stands for '3x long block'.
		for (int row = GlobalData.ROWS - 1 ; row >= ROW_MIN ; row--) // Search from bottom to top.
		{
			if (!isLocationEmpty(row, COL_MIN) && !isLocationEmpty(row, GlobalData.COLS - 1) && isRowCompleted(row))
			{
				completedRows.add(row);
			}
		}
		return completedRows;
	}

	private boolean isRowCompleted(int row)
	{
		boolean completeRow = true;
		for (int col = COL_MIN ; col < GlobalData.COLS ; col++)
		{
			if (isLocationEmpty(row, col))
			{
				completeRow = false;
				break;
			}
		}
		return completeRow;
	}

	private boolean isLocationEmpty(int row, int col)
	{
		return (field[row][col] == null);
	}


}
