package bm.util.blocks;

public class RegularBlock01 extends Block
{
	private static int id = 10;

	public RegularBlock01()
	{
		super.blockId = id;
	}

	{
		regularViews = new Tile[][]
				{
			{ new Tile(1,1,id), new Tile(1,2,id), new Tile(2,2,id), new Tile(2,3,id), },
			{ new Tile(1,2,id), new Tile(2,1,id), new Tile(2,2,id), new Tile(3,1,id), },
			{ new Tile(1,1,id), new Tile(1,2,id), new Tile(2,2,id), new Tile(2,3,id), },
			{ new Tile(1,2,id), new Tile(2,1,id), new Tile(2,2,id), new Tile(3,1,id)  }
				};

				flippedViews = new Tile[][]
						{
					{ new Tile(1,2,id), new Tile(1,3,id), new Tile(2,1,id), new Tile(2,2,id), },
					{ new Tile(1,2,id), new Tile(2,2,id), new Tile(2,3,id), new Tile(3,3,id), },
					{ new Tile(1,2,id), new Tile(1,3,id), new Tile(2,1,id), new Tile(2,2,id), },
					{ new Tile(1,2,id), new Tile(2,2,id), new Tile(2,3,id), new Tile(3,3,id)  }
						};
	}

}
