package bm.block_handling;

import bm.BlockManiaPOC.UserInput;

public class ControllerBlockHandling
{
	private ControllerPreview controllerPreview;
	private BlockProcessor blockProcessor;

	private static ControllerBlockHandling instance;
	public static ControllerBlockHandling getInstance()
	{
		if (instance == null)
		{
			instance = new ControllerBlockHandling();
		}
		return instance;
	}

	private ControllerBlockHandling()
	{
		controllerPreview = ControllerPreview.getInstance();
		blockProcessor = BlockProcessor.getInstance();
	}

	public void handleGameTick(UserInput input)
	{
		System.err.println("ControllerBlockHandling.handleGameTick");
		mainGameFlow(input);
	}

	// Level 1.

	private void mainGameFlow(UserInput input)
	{
		if (!controllerPreview.hasBlock())
		{
			controllerPreview.loadBlock();
		}

		if (blockProcessor.hasBlock())
		{
			if (isHetTijdVoorEenMoveDown())
			{

			}
			else if (input != UserInput.NONE)
			{
				handleUserInput(input);
			}
		}
		else
		{
			if (!blockProcessor.tryToPlaceNewBlockOnField())
			{
				System.out.println("GAME OVER !");
				// Niet genoeg ruimte op het speelveld om het nieuwe block te plaatsen ; GAME OVER !
			}
		}
	}

	private boolean isHetTijdVoorEenMoveDown()
	{
		// TODO Auto-generated method stub
		return false;
	}

	@SuppressWarnings("incomplete-switch")
	private void handleUserInput(UserInput input)
	{
		switch (input)
		{
			case ROTATE:
				blockProcessor.tryToRotateBlock();
				break;
			case FLIP:
				blockProcessor.tryToFlipBlock();
				break;
			case MOVE_LEFT:
				blockProcessor.tryToMoveBlockLeft();
				break;
			case MOVE_RIGHT:
				blockProcessor.tryToMoveBlockRight();
				break;
			case MOVE_UP:
				blockProcessor.tryToMoveBlockUp();
				break;
			case MOVE_DOWN:
				blockProcessor.tryToMoveBlockDown();
				break;
		}
	}

}
