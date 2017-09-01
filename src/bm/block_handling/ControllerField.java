package bm.block_handling;

import java.util.List;

import bm.block_handling.blocks.Tile;
import bm.util.GlobalData;

public class ControllerField
{
	private Tile[][] field = new Tile[GlobalData.ROWS][GlobalData.COLS];

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
		if (isErRuimteOmDeNieuweTilesTePlaatsen(currentTiles, newTiles))
		{
			placeNewTilesOnField(currentTiles, newTiles);
			return true;
		}
		else
		{
			return false;
		}
	}

	private boolean isErRuimteOmDeNieuweTilesTePlaatsen(List<Tile> currentTiles, List<Tile> newTiles)
	{
		// TODO Auto-generated method stub
		return false;
	}

	private void placeNewTilesOnField(List<Tile> currentTiles, List<Tile> newTiles)
	{
		// Verwijder currentTiles.
		// Plaats newTiles.
	}

	public boolean areTilesWithinBoundaries(List<Tile> tiles)
	{
		// TODO Auto-generated method stub
		return false;
	}

}
