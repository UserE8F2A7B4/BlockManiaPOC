package bm.view.field;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import bm.game_states.user_play.ControllerUserPlay;
import bm.util.blocks.Tile;
import bm.util.GlobalData;
import bm.view.field.text.CanvasFieldText;
import bm.view.ControllerView;

public class ControllerField
{
	private Tile[][] field = new Tile[GlobalData.ROW_COUNT][GlobalData.COL_COUNT];
	private Tile[][] fieldPrevious = new Tile[GlobalData.ROW_COUNT][GlobalData.COL_COUNT];

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
		// System.out.println("placeNewTilesOnField");

		clearTiles(currentTiles);
		setTiles(newTiles);
	}

	private void updateCanvas()
	{
		CanvasFieldText.getInstance().renderField(field);
		ControllerView.getInstance().renderField(field);

		// Er is iets gewijzigd aan het veld, dus :
		
		// Vergelijk [fieldPrevious] met [field] en sla de verschillen op.
		// Copieer de inhoud van [field] naar [fieldPrevious].

		vergelijkFields();
		copyField();
	}
	
	private void vergelijkFields()
	{
		System.out.println("---");

		for (int row = 0 ; row < GlobalData.ROW_COUNT ; row++)
		{
			for (int col = 0; col < GlobalData.COL_COUNT; col++)
			{
				Tile tilePrevious = fieldPrevious[row][col];
				Tile tileCurrent = field[row][col];

				if (tileCurrent == null && tilePrevious == null)
				{
					continue;
				}

				final long gameTickCounter = ControllerUserPlay.gameTickCounter;

				if (tileCurrent != null && tilePrevious != null)
				{
					if (tileCurrent.getBlockId() != tilePrevious.getBlockId())
					{
						System.out.println(String.format("Result : r:%s / c:%s / b:%s / t:%s ", row, col, tileCurrent.getBlockId(), gameTickCounter ));
					}
				}
				else if (tileCurrent == null)
				{
					System.out.println(String.format("Result : r:%s / c:%s / b:%s / t:%s ", row, col, -1, gameTickCounter ));
				}
				else
				{
					System.out.println(String.format("Result : r:%s / c:%s / b:%s / t:%s ", row, col, tileCurrent.getBlockId(), gameTickCounter ));
				}
			}
		}
	}

	private void copyField()
	{
		for (int row = 0 ; row < GlobalData.ROW_COUNT ; row++)
		{
			System.arraycopy(field[row], 0, fieldPrevious[row], 0, GlobalData.COL_COUNT);
		}
	}

	public void clearAllTiles()
	{
		for (int row = 0 ; row < GlobalData.ROW_COUNT ; row++)
		{
			Arrays.fill(field[row], null);
		}
		updateCanvas();
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

	public boolean hasCompletedRows()
	{
		List<Integer> completedRows = getCompletedRows();
		return !completedRows.isEmpty();
	}

	public List<Integer> getCompletedRows()
	{
		List<Integer> completedRows = new ArrayList<>(15); // '15' stands for '3x long block'.
		for (int row = GlobalData.ROW_MAX ; row > GlobalData.ROW_MIN ; row--) // Search from bottom to top.
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
		boolean tilesFound = false;

		for (int row = rowTarget - 1 ; row > GlobalData.ROW_MIN ; row--) // Search from bottom to top.
		{
			for (int col = 0 ; col < GlobalData.COL_COUNT ; col++)
			{
				Tile tile = field[row][col];
				if (tile != null)
				{
					verplaatsTegelNaarBeneden(tile);
					tilesFound = true;
				}
			}

			if (tilesFound)
			{
				updateCanvas();
			}
		}
	}

	private void verplaatsTegelNaarBeneden(Tile tile)
	{
		field[tile.getRow()][tile.getCol()] = null;
		tile.setRow(tile.getRow() + 1);
		field[tile.getRow()][tile.getCol()] = tile;
	}

}
