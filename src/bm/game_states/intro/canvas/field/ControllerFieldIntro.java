package bm.game_states.intro.canvas.field;

import bm.util.game_loop.GameLoopPause;

public class ControllerFieldIntro
{
	private static ControllerFieldIntro instance;

	private GameLoopPause pause = new GameLoopPause(3);

	private CanvasIntroLabel tsc;

	private int Y;

	public static ControllerFieldIntro getInstance()
	{
		if (instance == null)
		{
			instance = new ControllerFieldIntro();
		}
		return instance;
	}

	private ControllerFieldIntro()
	{
	}

	public void init()
	{
		tsc = CanvasIntroLabel.getInstance();
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

			updateCanvas();
		}
	}

	private void updateCanvas()
	{
		tsc.renderIntro();
	}

}
