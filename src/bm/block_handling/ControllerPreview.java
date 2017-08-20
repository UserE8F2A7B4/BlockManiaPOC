package bm.block_handling;

import bm.block_handling.blocks.Block;
import bm.block_handling.blocks.BlockPool;

public class ControllerPreview
{
	private Block block;

	private static ControllerPreview instance;
	public static ControllerPreview getInstance()
	{
		if (instance == null)
		{
			instance = new ControllerPreview();
		}
		return instance;
	}

	private ControllerPreview()
	{
	}

	public Block getBlock()
	{
		return block;
	}

	public boolean hasBlock()
	{
		return (block != null);
	}

	public void loadBlock()
	{
		block = BlockPool.getRandomBlock();
	}

}
