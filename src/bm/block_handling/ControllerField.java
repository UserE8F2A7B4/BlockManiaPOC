package bm.block_handling;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import bm.block_handling.blocks.Tile;
import bm.util.GlobalData;
import bm.view.CanvasFieldText;
import bm.view.ControllerView;

public class ControllerField
{
	private Tile[][] field = new Tile[GlobalData.ROW_COUNT][GlobalData.COL_COUNT];

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
		boolean withinBoundaries = (row >= GlobalData.ROW_MIN && row < GlobalData.ROW_COUNT && col >= GlobalData.COL_MIN && col < GlobalData.COL_COUNT);
		return withinBoundaries;
	}

	private boolean isLocationOccupiedByAnyOfTheCurrentTiles(List<Tile> currentTiles, Tile newTile)
	{
		boolean isOccupied = false;
		for (Tile currentTile : currentTiles)
		{
			if (currentTile.hasSameCoordsAs(newTile))
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

		updateCanvas();
	}

	private void updateCanvas()
	{
		CanvasFieldText.getInstance().renderField(field);
		ControllerView.getInstance().renderField(field);
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
			clearTile(tile.getRow(), tile.getCol(), false);
		}
	}

	public void clearTile(int row, int col, boolean updateCanvas)
	{
		field[row][col] = null;

		if (updateCanvas)
		{
			updateCanvas();
		}
	}

	private void setTiles(List<Tile> tiles)
	{
		for (Tile tile : tiles)
		{
			setTile(tile, false);
		}

		updateCanvas();
	}

	public void setTile(Tile tile, boolean updateCanvas)
	{
		field[tile.getRow()][tile.getCol()] = tile;

		if (updateCanvas)
		{
			updateCanvas();
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
		for (int row = GlobalData.ROW_MAX ; row >= GlobalData.ROW_MIN ; row--) // Search from bottom to top.
		{
			if (!isLocationEmpty(row, GlobalData.COL_MIN) && !isLocationEmpty(row, GlobalData.COL_MAX) && isRowCompleted(row))
			{
				completedRows.add(row);
			}
		}
		return completedRows;
	}

	private boolean isRowCompleted(int row)
	{
		boolean completeRow = true;
		for (int col = GlobalData.COL_MIN ; col < GlobalData.COL_COUNT ; col++)
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

	public void verplaatsBovenliggendeTegelsNaarBeneden(int rowTarget)
	{
		for (int row = rowTarget - 1 ; row >= GlobalData.ROW_MIN ; row--) // Search from bottom to top.
		{
			for (int col = 0 ; col < GlobalData.COL_MAX ; col++)
			{
				Tile tile = field[row][col];
				if (tile != null)
				{
					verplaatsTegelNaarBeneden(tile);
				}
			}
			updateCanvas();
		}
	}

	private void verplaatsTegelNaarBeneden(Tile tile)
	{
		field[tile.getRow()][tile.getCol()] = null;
		tile.setRow(tile.getRow() + 1);
		field[tile.getRow()][tile.getCol()] = tile;
	}

}
