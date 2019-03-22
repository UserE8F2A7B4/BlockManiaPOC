package bm.block_handling;

import bm.BlockManiaPOC.UserInput;
import bm.ControllerMain;
import bm.ControllerMain.GameState;

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

//	public void handleGameTick(UserInput input)
//	{
//		// System.err.println("ControllerBlockHandling.handleGameTick");
//		mainGameFlow(input);
//	}


//	private void mainGameFlow(UserInput input)
//	{
//		if (!controllerPreview.hasBlock())
//		{
//			controllerPreview.loadNextBlock();
//			// controllerPreview.loadRandomBlock();
//		}
//
//		if (blockProcessor.hasBlock())
//		{
//			if (isHetTijdVoorEenMoveDown()) // Wordt bepaald door de game-timer.
//			{
//
//			}
//			else if (input != UserInput.NONE)
//			{
//				handleUserInput(input);
//			}
//		}
//		else
//		{
//			if (!blockProcessor.tryToPlaceNewBlockOnField())
//			{
//				System.out.println("GAME OVER !");
//				// Er is niet genoeg ruimte op het speelveld om het nieuwe block te plaatsen ; GAME OVER !
//			}
//			else
//			{
//				controllerPreview.clearBlock();
//			}
//		}
//	}

//	private boolean isHetTijdVoorEenMoveDown()
//	{
//		// TODO Auto-generated method stub
//		return false;
//	}

	@SuppressWarnings("incomplete-switch")
//	private void handleUserInput(UserInput input)
//	{
//		switch (input)
//		{
//			case ROTATE:
//				blockProcessor.tryToRotateBlock();
//				break;
//			case FLIP:
//				blockProcessor.tryToFlipBlock();
//				break;
//			case MOVE_LEFT:
//				blockProcessor.tryToMoveBlockLeft();
//				break;
//			case MOVE_RIGHT:
//				blockProcessor.tryToMoveBlockRight();
//				break;
//			case MOVE_UP:
//				blockProcessor.tryToMoveBlockUp();
//				break;
//			case MOVE_DOWN:
//				blockProcessor.tryToMoveBlockDown();
//				break;
//		}
//	}

	public void changeGameState(GameState newGameState)
	{
		ControllerMain.getInstance().changeGameState(newGameState);
	}

}
