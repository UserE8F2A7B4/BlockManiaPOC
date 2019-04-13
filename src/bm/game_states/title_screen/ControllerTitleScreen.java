package bm.game_states.title_screen;

import bm.util.game_loop.GameLoopPause;

public class ControllerTitleScreen
{
	private static ControllerTitleScreen instance;

	private GameLoopPause pause = new GameLoopPause(3);

	private CanvasTitleScreen tsc;

	private int Y;

	public static ControllerTitleScreen getInstance()
	{
		if (instance == null)
		{
			instance = new ControllerTitleScreen();
		}
		return instance;
	}

	private ControllerTitleScreen()
	{
	}

	public void init()
	{
		tsc = CanvasTitleScreen.getInstance();
		Y = 40;
	}

	public void handleGameTick()
	{
		if (pause.hasExpired())
		{
			tsc.clearField();
			tsc.drawString("Press 'F9' to play.", 20, Y);
			Y += 10;

			if (Y == 150)
			{
				Y = 40;
			}
		}
	}

}
