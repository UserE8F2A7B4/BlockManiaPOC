package bm.util.blocks;

public class RegularBlock02 extends Block
{
	private static int id = 11;

	public RegularBlock02()
	{
		super.blockId = id;
	}

	{
		regularViews = new Tile[][]
				{
			{ new Tile(2,0,id), new Tile(2,1,id), new Tile(2,2,id), new Tile(2,3,id), new Tile(2,4,id), },
			{ new Tile(0,2,id), new Tile(1,2,id), new Tile(2,2,id), new Tile(3,2,id), new Tile(4,2,id), },
			{ new Tile(2,0,id), new Tile(2,1,id), new Tile(2,2,id), new Tile(2,3,id), new Tile(2,4,id), },
			{ new Tile(0,2,id), new Tile(1,2,id), new Tile(2,2,id), new Tile(3,2,id), new Tile(4,2,id)  }
				};

				flippedViews = new Tile[][]
						{
					{ new Tile(2,0,id), new Tile(2,1,id), new Tile(2,2,id), new Tile(2,3,id), new Tile(2,4,id), },
					{ new Tile(0,2,id), new Tile(1,2,id), new Tile(2,2,id), new Tile(3,2,id), new Tile(4,2,id), },
					{ new Tile(2,0,id), new Tile(2,1,id), new Tile(2,2,id), new Tile(2,3,id), new Tile(2,4,id), },
					{ new Tile(0,2,id), new Tile(1,2,id), new Tile(2,2,id), new Tile(3,2,id), new Tile(4,2,id)  }
						};
	}

}
