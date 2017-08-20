package bm.block_handling.blocks;



public class RegularBlock05 extends Block
{
  private static int id = 14;
  
  public RegularBlock05()
  {
    super.blockNumber = id;
  }
  
  {
    regularViews = new Tile[][] 
    {
      { new Tile(1,2,id), new Tile(2,1,id), new Tile(2,2,id), new Tile(2,3,id), },
      { new Tile(1,2,id), new Tile(2,2,id), new Tile(2,3,id), new Tile(3,2,id), },
      { new Tile(2,1,id), new Tile(2,2,id), new Tile(2,3,id), new Tile(3,2,id), },
      { new Tile(1,2,id), new Tile(2,1,id), new Tile(2,2,id), new Tile(3,2,id)  }
    };

    flippedViews = new Tile[][] 
    {
      { new Tile(1,2,id), new Tile(2,1,id), new Tile(2,2,id), new Tile(2,3,id), },
      { new Tile(1,2,id), new Tile(2,1,id), new Tile(2,2,id), new Tile(3,2,id), },
      { new Tile(2,1,id), new Tile(2,2,id), new Tile(2,3,id), new Tile(3,2,id), },
      { new Tile(1,2,id), new Tile(2,2,id), new Tile(2,3,id), new Tile(3,2,id)  }
    }; 
  } 
  
}
