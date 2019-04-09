package bm.line_removal;

import java.util.Collections;
import java.util.List;

import bm.ControllerMain;
import bm.ControllerMain.GameState;
import bm.block_handling.ControllerField;
import bm.block_handling.blocks.Tile;
import bm.util.GameLoopPause;
import bm.util.GlobalData;

public class ControllerLineRemoval
{
	private ControllerField cf;

	private RemovalMode mode;
	private enum RemovalMode
	{
		IDLE, ANIMATION_STEP_ONE, ANIMATION_STEP_TWO, REMOVE_LINES, DONE
	}

	private List<Integer> rowsToRemoveList;
	private int rowsToRemoveCount;

	private int rowCurrent, colCurrent;
	private int rowIndex;

	private GameLoopPause pauseAnimation = new GameLoopPause(2); // Skip 10 game-ticks.

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
	}

	public void init()
	{
		cf = ControllerField.getInstance();
		mode = RemovalMode.IDLE;
	}

	public void handleGameTick()
	{
		//		if (pauseAnimation.isPausing())
		//		{
		//			System.err.println("SKIP --- ControllerLineRemoval.handleGameTick");
		//			return;
		//		}
		//
		//		System.err.println("HANDLE --- ControllerLineRemoval.handleGameTick");

		if (mode == RemovalMode.IDLE)
		{
			startAnimation();
		}
		else if (mode == RemovalMode.ANIMATION_STEP_ONE)
		{
			doAnimationStepOne();
		}
		else if (mode == RemovalMode.ANIMATION_STEP_TWO)
		{
			doAnimationStepTwo();
		}
		else if (mode == RemovalMode.REMOVE_LINES)
		{
			removeLines();
		}
		else if (mode == RemovalMode.DONE)
		{
			ControllerMain.getInstance().changeGameState(GameState.BLOCK_HANDLING);
			mode = RemovalMode.IDLE;
		}
	}

	private void startAnimation()
	{
		initAnimation();
		mode = RemovalMode.ANIMATION_STEP_ONE;
	}

	private void initAnimation()
	{
		rowsToRemoveList = cf.getCompletedRows();
		rowsToRemoveCount = rowsToRemoveList.size();

		rowCurrent = rowsToRemoveList.get(rowIndex);
	}

	private void doAnimationStepOne()
	{
		rowCurrent = rowsToRemoveList.get(rowIndex);
		Tile tile = Tile.getDummyTileRed(rowCurrent, colCurrent);
		cf.setTile(tile,true);

		colCurrent++;
		if (colCurrent >= GlobalData.COL_COUNT)
		{
			colCurrent = 0;
			rowIndex++;

			if (rowIndex >= rowsToRemoveCount)
			{
				rowIndex = 0;
				mode = RemovalMode.ANIMATION_STEP_TWO;
			}
		}
	}

	private void doAnimationStepTwo()
	{
		rowCurrent = rowsToRemoveList.get(rowIndex);
		cf.clearTile(rowCurrent, colCurrent, true);

		colCurrent++;
		if (colCurrent >= GlobalData.COL_COUNT)
		{
			colCurrent = 0;
			rowIndex++;

			if (rowIndex >= rowsToRemoveCount)
			{
				rowIndex = 0;
				Collections.sort(rowsToRemoveList);
				mode = RemovalMode.REMOVE_LINES;
			}
		}
	}

	private void removeLines()
	{
		rowCurrent = rowsToRemoveList.get(rowIndex);
		cf.verplaatsBovenliggendeTegelsNaarBeneden(rowCurrent);

		rowIndex++;
		if (rowIndex >= rowsToRemoveCount)
		{
			mode = RemovalMode.DONE;
		}
	}

}
