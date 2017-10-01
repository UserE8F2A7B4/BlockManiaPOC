package bm.block_handling.blocks;

public class SpecialBlock04 extends Block
{
	private static int id = 23;

	public SpecialBlock04()
	{
		super.blockId = id;
	}

	{
		regularViews = new Tile[][]
				{
			{ new Tile(2,1,id), new Tile(2,2,id), new Tile(3,1,id) },
			{ new Tile(2,1,id), new Tile(2,2,id), new Tile(3,2,id) },
			{ new Tile(2,2,id), new Tile(3,1,id), new Tile(3,2,id) },
			{ new Tile(2,1,id), new Tile(3,1,id), new Tile(3,2,id) }
				};

				flippedViews = new Tile[][]
						{
					{ new Tile(2,1,id), new Tile(2,2,id), new Tile(3,2,id) },
					{ new Tile(2,1,id), new Tile(2,2,id), new Tile(3,1,id) },
					{ new Tile(2,1,id), new Tile(3,1,id), new Tile(3,2,id) },
					{ new Tile(2,2,id), new Tile(3,1,id), new Tile(3,2,id) }
						};
	}

}
