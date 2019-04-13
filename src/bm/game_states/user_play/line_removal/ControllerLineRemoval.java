package bm.game_states.user_play.line_removal;

import java.util.Collections;
import java.util.List;

import bm.game_states.user_play.ControllerUserPlay;
import bm.game_states.user_play.Canvas.Field.ControllerFieldUserPlay;
import bm.util.blocks.Tile;
import bm.util.game_loop.GameLoopPause;
import bm.util.GlobalData;

public class ControllerLineRemoval
{
	private static ControllerLineRemoval instance;

	private ControllerFieldUserPlay cf;

	private RemovalMode mode;
	private enum RemovalMode
	{
		IDLE, ANIMATION_STEP_ONE, ANIMATION_STEP_TWO, REMOVE_LINES, DONE
	}

	private List<Integer> rowsToRemoveList;
	private int rowsToRemoveCount;

	private int rowCurrent, colCurrent;
	private int rowIndex;

	private GameLoopPause pauseAnimation = new GameLoopPause(2);

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
		cf = ControllerFieldUserPlay.getInstance();
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
			ControllerUserPlay.getInstance().changeGameState(ControllerUserPlay.GameStateUserPlay.BLOCK_HANDLING);
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

		rowIndex = 0;
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

	//	private void doAnimationStepTwo()
	//	{
	//		rowCurrent = rowsToRemoveList.get(rowIndex);
	//		cf.clearTile(rowCurrent, colCurrent, true);
	//
	//		colCurrent--;
	//		if (colCurrent <= GlobalData.COL_MIN)
	//		{
	//			colCurrent = GlobalData.COL_MAX;
	//			rowIndex++;
	//
	//			if (rowIndex >= rowsToRemoveCount)
	//			{
	//				rowIndex = 0;
	//				Collections.sort(rowsToRemoveList);
	//				mode = RemovalMode.REMOVE_LINES;
	//			}
	//		}
	//	}

	private void removeLines()
	{
		rowCurrent = rowsToRemoveList.get(rowIndex);
		cf.verplaatsBovenliggendeTegelsNaarBeneden(rowCurrent);

		rowIndex++;
		if (rowIndex >= rowsToRemoveCount)
		{
			rowIndex = 0;
			mode = RemovalMode.DONE;
		}
	}

}
