package bm.block_handling.blocks;

import bm.util.GlobalData;

// Een 'Tile' representeert een vakje op het game- of preview field.

public class Tile
{
	private int row, col;
	private int blockId;

	public Tile(int row, int col, int id)
	{
		this.row = row;
		this.col = col;
		this.blockId = id;
	}

	public void setRow(int r)
	{
		row = r;
	}

	public int getRow()
	{
		return row;
	}

	public int getCol()
	{
		return col;
	}

	public int getBlockId()
	{
		return blockId;
	}

	public boolean hasSameCoordsAs(Tile otherTile)
	{
		return (otherTile.row == this.row && otherTile.col == this.col);
	}

	@Override
	public boolean equals(Object obj)
	{
		boolean areEqual = false;
		if (obj != null && (obj instanceof Tile))
		{
			Tile tile = (Tile) obj;
			areEqual = (tile.blockId == this.blockId);
		}
		return areEqual;
	}

	@Override
	public int hashCode()
	{
		return (this.blockId + this.row + this.col);
	}

	public static Tile getDummyTileRed(int row, int col)
	{
		Tile tile = new Tile(row, col, GlobalData.TILE_COLOR_RED);
		return tile;
	}
	public static Tile getDummyTileWhite(int row, int col)
	{
		Tile tile = new Tile(row, col, GlobalData.TILE_COLOR_WHITE);
		return tile;
	}

	@Override
	public String toString()
	{
		return String.format("Tile / row/col : %s/%s ", this.row, this.col);
	}

}
