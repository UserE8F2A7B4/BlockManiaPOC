package bm.block_handling.blocks;

public class RegularBlock06 extends Block
{
	private static int id = 15;

	public RegularBlock06()
	{
		super.blockId = id;
	}

	{
		regularViews = new Tile[][]
				{
			{ new Tile(1,2,id), new Tile(1,3,id), new Tile(2,1,id), new Tile(2,2,id), },
			{ new Tile(1,1,id), new Tile(2,1,id), new Tile(2,2,id), new Tile(3,2,id), },
			{ new Tile(1,2,id), new Tile(1,3,id), new Tile(2,1,id), new Tile(2,2,id), },
			{ new Tile(1,2,id), new Tile(2,2,id), new Tile(2,3,id), new Tile(3,3,id)  }
				};

				flippedViews = new Tile[][]
						{
					{ new Tile(1,1,id), new Tile(1,2,id), new Tile(2,2,id), new Tile(2,3,id), },
					{ new Tile(1,3,id), new Tile(2,2,id), new Tile(2,3,id), new Tile(3,2,id), },
					{ new Tile(1,1,id), new Tile(1,2,id), new Tile(2,2,id), new Tile(2,3,id), },
					{ new Tile(1,2,id), new Tile(2,1,id), new Tile(2,2,id), new Tile(3,1,id)  }
						};
	}

}
