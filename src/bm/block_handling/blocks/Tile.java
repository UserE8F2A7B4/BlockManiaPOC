package bm.block_handling.blocks;

// Een 'Tile' representeert een vakje op het game- of preview field.

public class Tile
{
	private int row, col;
	private int id; // Wordt gebruikt om de kleur te bepalen.

	public Tile(int row, int col, int id)
	{
		this.row = row;
		this.col = col;
		this.id = id;
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

	public int getId()
	{
		return id;
	}

	@Override
	public boolean equals(Object obj)
	{
		boolean areEqual = false;
		if (obj != null && (obj instanceof Tile))
		{
			Tile tile = (Tile) obj;
			areEqual = (tile.row == this.row && tile.col == this.col);
		}
		return areEqual;
	}

	@Override
	public int hashCode()
	{
		return (this.id + this.row + this.col);
	}

	@Override
	public String toString()
	{
		return String.format("Tile / row/col : %s/%s ", this.row, this.col);
	}

}
