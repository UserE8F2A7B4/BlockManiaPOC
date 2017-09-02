package bm.block_handling;

import java.util.List;

import bm.block_handling.blocks.Tile;
import bm.util.GlobalData;

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

}
