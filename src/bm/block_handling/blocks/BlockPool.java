package bm.block_handling.blocks;

import java.awt.Color;
import java.util.Random;

import bm.util.GlobalData;
import bm.util.GlobalData.GameMode;

public abstract class BlockPool
{
	private static Random wheel = new Random();

	private static int index;

	private final static int MAX_INDEX_STANDARD_BLOCKS = 6;  // The number of standard blocks (zero-based).
	private final static int MAX_INDEX_ALL_BLOCKS      = 19; // The number of all blocks (zero-based).

	public static Block getFixedBlock()
	{
		return getBlock(6);
	}

	public static Block getRandomBlock()
	{
		int lowest = 0;
		int highest = (GlobalData.getGameMode() == GameMode.CLASSIC) ? MAX_INDEX_STANDARD_BLOCKS : MAX_INDEX_ALL_BLOCKS;

		int random = wheel.nextInt(highest - lowest + 1) + lowest; // Returns a number between 'lowest' and 'highest', (both inclusive).

		System.out.println(String.format("random : %s ", random));

		Block randomBlock = getBlock(random);

		return randomBlock;
	}

	public static Block getNextBlock()
	{
		index++;
		if (index > MAX_INDEX_STANDARD_BLOCKS) { index = 0; }

		Block nextBlock = getBlock(index);
		return nextBlock;
	}

	private static Block getBlock(int i)
	{
		Block b = null;
		switch (i)
		{
			case 0  : b = new RegularBlock01() ; break;
			case 1  : b = new RegularBlock02() ; break;
			case 2  : b = new RegularBlock03() ; break;
			case 3  : b = new RegularBlock04() ; break;
			case 4  : b = new RegularBlock05() ; break;
			case 5  : b = new RegularBlock06() ; break;
			case 6  : b = new RegularBlock07() ; break;

			case 7  : b = new SpecialBlock01() ; break;
			case 8  : b = new SpecialBlock02() ; break;
			case 9  : b = new SpecialBlock03() ; break;
			case 10 : b = new SpecialBlock04() ; break;
			case 11 : b = new SpecialBlock05() ; break;
			case 12 : b = new SpecialBlock06() ; break;
			case 13 : b = new SpecialBlock07() ; break;
			case 14 : b = new SpecialBlock08() ; break;
			case 15 : b = new SpecialBlock09() ; break;
			case 16 : b = new SpecialBlock10() ; break;
			case 17 : b = new SpecialBlock11() ; break;
			case 18 : b = new SpecialBlock12() ; break;
			case 19 : b = new SpecialBlock13() ; break;

			default : assert false ; break;
		}
		return b;
	}

	public static Color getTileColor(int blockId)
	{
		switch (blockId)
		{
			case 0  : return Color.PINK;
			case 1  : return Color.RED;
			case 2  : return Color.GREEN;
			case 3  : return Color.BLUE;
			case 4  : return Color.CYAN;
			case 5  : return Color.YELLOW;
			case 6  : return Color.MAGENTA;

			case GlobalData.TILE_COLOR_RED   : return Color.RED;   // Tbv removal-
			case GlobalData.TILE_COLOR_WHITE : return Color.WHITE; // animation.

			default : return Color.ORANGE;
		}
	}

}
