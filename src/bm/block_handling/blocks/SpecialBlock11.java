package bm.block_handling.blocks;

public class SpecialBlock11 extends Block
{
	private static int id = 30;

	public SpecialBlock11()
	{
		super.blockId = id;
	}

	{
		regularViews = new Tile[][]
				{
			{ new Tile(2,1,id), new Tile(2,2,id), new Tile(3,0,id), new Tile(3,1,id), new Tile(3,2,id), new Tile(3,3,id) },
			{ new Tile(0,1,id), new Tile(1,1,id), new Tile(1,2,id), new Tile(2,1,id), new Tile(2,2,id), new Tile(3,1,id) },
			{ new Tile(2,0,id), new Tile(2,1,id), new Tile(2,2,id), new Tile(2,3,id), new Tile(3,1,id), new Tile(3,2,id) },
			{ new Tile(0,2,id), new Tile(1,1,id), new Tile(1,2,id), new Tile(2,1,id), new Tile(2,2,id), new Tile(3,2,id) }
				};

				flippedViews = new Tile[][]
						{
					{ new Tile(2,1,id), new Tile(2,2,id), new Tile(3,0,id), new Tile(3,1,id), new Tile(3,2,id), new Tile(3,3,id) },
					{ new Tile(0,2,id), new Tile(1,1,id), new Tile(1,2,id), new Tile(2,1,id), new Tile(2,2,id), new Tile(3,2,id) },
					{ new Tile(2,0,id), new Tile(2,1,id), new Tile(2,2,id), new Tile(2,3,id), new Tile(3,1,id), new Tile(3,2,id) },
					{ new Tile(0,1,id), new Tile(1,1,id), new Tile(1,2,id), new Tile(2,1,id), new Tile(2,2,id), new Tile(3,1,id) }
						};
	}

}
