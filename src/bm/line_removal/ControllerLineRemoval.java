package bm.line_removal;

import java.util.List;

import bm.ControllerMain.GameState;
import bm.block_handling.ControllerBlockHandling;
import bm.block_handling.ControllerField;
import bm.block_handling.blocks.Tile;
import bm.util.GlobalData;

public class ControllerLineRemoval
{
	private ControllerField controllerField;

	private RemovalMode mode;
	private static enum RemovalMode
	{
		IDLE, INIT, ANIMATION_STEP_ONE, ANIMATION_STEP_TWO, DONE
	}

	private List<Integer> rowsToRemove;
	private int row, col;
	private int rowNumber;

	private static ControllerLineRemoval instance;
	public static ControllerLineRemoval getInstance()
	{
		if (instance == null)
		{
			instance = new ControllerLineRemoval();
		}
		return instance;
	}

	private ControllerLineRemoval()
	{
		controllerField = ControllerField.getInstance();
		mode = RemovalMode.IDLE;
	}

	public void handleGameTick()
	{
		System.err.println("ControllerLineRemoval.handleGameTick");

		if (mode == RemovalMode.IDLE)
		{
			init();
			mode = RemovalMode.ANIMATION_STEP_ONE;
		}
		else if (mode == RemovalMode.ANIMATION_STEP_ONE)
		{
			doAnimationStepOne();
		}
		else if (mode == RemovalMode.ANIMATION_STEP_TWO)
		{
			doAnimationStepTwo();
		}
		else if (mode == RemovalMode.DONE)
		{
			ControllerBlockHandling.getInstance().changeGameState(GameState.BLOCK_HANDLING);
			mode = RemovalMode.IDLE;
		}
	}

	private void init()
	{
		rowsToRemove = controllerField.getCompletedRows();
		rowNumber = rowsToRemove.get(rowNumber);
		col = 0;
	}

	private void doAnimationStepOne()
	{
		Tile tile = Tile.getDummyTileRed(rowNumber, col);
		ControllerField.getInstance().setTile(tile);

		col++;
		if (col >= GlobalData.COLS)
		{
			row++;
			if (row >= rowsToRemove.size())
			{
				mode = RemovalMode.ANIMATION_STEP_TWO;
			}
		}
	}

	private void doAnimationStepTwo()
	{

	}

}
