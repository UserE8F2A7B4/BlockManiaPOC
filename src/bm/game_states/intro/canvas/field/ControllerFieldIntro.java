package bm.game_states.intro.canvas.field;

import bm.util.GameTickHandling;
import bm.util.game_loop.GameLoopPause;

public class ControllerFieldIntro implements GameTickHandling
{
	private static ControllerFieldIntro instance;

	private GameLoopPause pause = new GameLoopPause(3);

	private CanvasIntroLabel ci;

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
		initCanvas();
		Y = 40;
	}

	public void initCanvas()
	{
		ci = CanvasIntroLabel.getInstance();
	}

	public void handleGameTick()
	{
		if (pause.hasExpired())
		{
			ci.clearField();
			ci.drawString("Press 'F9' to play.", 20, Y);
			Y += 10;

			if (Y == 150)
			{
				Y = 40;
			}

			updateCanvas();
		}
	}

	public void updateCanvas()
	{
		ci.renderIntro();
	}

}
