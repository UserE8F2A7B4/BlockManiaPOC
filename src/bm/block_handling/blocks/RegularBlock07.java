package bm.block_handling.blocks;

public class RegularBlock07 extends Block
{
	private static int id = 16;

	public RegularBlock07()
	{
		super.blockNumber = id;
	}

	{
		regularViews = new Tile[][]
				{
			{ new Tile(2,1,id), new Tile(2,2,id), new Tile(3,1,id), new Tile(3,2,id) },
			{ new Tile(2,1,id), new Tile(2,2,id), new Tile(3,1,id), new Tile(3,2,id) },
			{ new Tile(2,1,id), new Tile(2,2,id), new Tile(3,1,id), new Tile(3,2,id) },
			{ new Tile(2,1,id), new Tile(2,2,id), new Tile(3,1,id), new Tile(3,2,id) },
				};

				flippedViews = new Tile[][]
						{
					{ new Tile(2,1,id), new Tile(2,2,id), new Tile(3,1,id), new Tile(3,2,id) },
					{ new Tile(2,1,id), new Tile(2,2,id), new Tile(3,1,id), new Tile(3,2,id) },
					{ new Tile(2,1,id), new Tile(2,2,id), new Tile(3,1,id), new Tile(3,2,id) },
					{ new Tile(2,1,id), new Tile(2,2,id), new Tile(3,1,id), new Tile(3,2,id) },
						};
	}

}
