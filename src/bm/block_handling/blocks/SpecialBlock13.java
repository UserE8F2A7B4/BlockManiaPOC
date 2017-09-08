package bm.block_handling.blocks;

public class SpecialBlock13 extends Block
{
	private static int id = 32;

	public SpecialBlock13()
	{
		super.blockNumber = id;
	}

	{
		regularViews = new Tile[][]
				{
			{ new Tile(2,1,id), new Tile(2,2,id), new Tile(3,2,id), new Tile(3,3,id), new Tile(3,4,id) },
			{ new Tile(0,2,id), new Tile(1,1,id), new Tile(1,2,id), new Tile(2,1,id), new Tile(3,1,id) },
			{ new Tile(2,0,id), new Tile(2,1,id), new Tile(2,2,id), new Tile(3,2,id), new Tile(3,3,id) },
			{ new Tile(0,2,id), new Tile(1,2,id), new Tile(2,1,id), new Tile(2,2,id), new Tile(3,1,id) }
				};

				flippedViews = new Tile[][]
						{
					{ new Tile(2,2,id), new Tile(2,3,id), new Tile(3,0,id), new Tile(3,1,id), new Tile(3,2,id) },
					{ new Tile(0,2,id), new Tile(1,2,id), new Tile(1,3,id), new Tile(2,3,id), new Tile(3,3,id) },
					{ new Tile(2,2,id), new Tile(2,3,id), new Tile(2,4,id), new Tile(3,1,id), new Tile(3,2,id) },
					{ new Tile(0,2,id), new Tile(1,2,id), new Tile(2,2,id), new Tile(2,3,id), new Tile(3,3,id) }
						};
	}

}
