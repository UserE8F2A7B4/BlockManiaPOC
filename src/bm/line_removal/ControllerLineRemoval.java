package bm.line_removal;

import java.util.List;

import bm.ControllerMain;
import bm.ControllerMain.GameState;
import bm.block_handling.ControllerField;
import bm.block_handling.blocks.Tile;
import bm.util.GameLoopPause;
import bm.util.GlobalData;

public class ControllerLineRemoval
{
	private ControllerField controllerField;

	private RemovalMode mode;
	private enum RemovalMode
	{
		IDLE, ANIMATION_STEP_ONE, ANIMATION_STEP_TWO, ANIMATION_STEP_THREE, DONE
	}

	private List<Integer> rowsToRemoveList;
	private int rowsToRemoveCount;

	private int rowCurrent, colCurrent;
	private int rowIndex;

	private GameLoopPause pauseAnimation = new GameLoopPause(10); // Skip 10 game-ticks.

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

	//---

	public boolean is_animatie_01_nog_bezig()
	{
		return false;
	}

	public void update_animatie_01()
	{
	}

	public boolean is_animatie_02_nog_bezig()
	{
		return false;
	}

	public void update_animatie_02()
	{
	}

	public void verwijder_de_volledige_regels()
	{
	}

	public void verplaats_de_overgebleven_regels_naar_beneden()
	{
	}

	//---

	public void handleGameTick()
	{
		if (pauseAnimation.isPausing())
		{
			System.err.println("SKIP --- ControllerLineRemoval.handleGameTick");
			return;
		}

		System.err.println("HANDLE --- ControllerLineRemoval.handleGameTick");

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
		else if (mode == RemovalMode.DONE)
		{
			ControllerMain.getInstance().changeGameState(GameState.BLOCK_HANDLING);
			mode = RemovalMode.IDLE;
		}
	}

	private void startAnimation()
	{
		init();
		mode = RemovalMode.ANIMATION_STEP_ONE;
	}

	private void init()
	{
		rowsToRemoveList = controllerField.getCompletedRows();
		rowsToRemoveCount = rowsToRemoveList.size();
		rowIndex = 0;
		reset();
	}

	private void reset()
	{
		rowCurrent = rowsToRemoveList.get(rowIndex);
		colCurrent = 0;
	}

	private void doAnimationStepOne()
	{
		Tile tile = Tile.getDummyTileRed(rowCurrent, colCurrent);
		ControllerField.getInstance().setTile(tile,true);

		colCurrent++;
		if (colCurrent >= GlobalData.COLS)
		{
			rowIndex++;
			if (rowIndex >= rowsToRemoveCount)
			{
				reset();
				mode = RemovalMode.ANIMATION_STEP_TWO;
			}
		}
	}

	private void doAnimationStepTwo()
	{
		Tile tile = Tile.getDummyTileWhite(rowCurrent, colCurrent);
		ControllerField.getInstance().setTile(tile, true);

		colCurrent++;
		if (colCurrent >= GlobalData.COLS)
		{
			rowIndex++;
			if (rowIndex >= rowsToRemoveCount)
			{
				reset();
				mode = RemovalMode.ANIMATION_STEP_THREE;
			}
		}
	}

}
