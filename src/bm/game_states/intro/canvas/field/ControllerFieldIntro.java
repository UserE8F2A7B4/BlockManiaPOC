package bm.game_states.intro.canvas.field;

import bm.util.CanvasHandling;
import bm.util.game_loop.GameLoopPause;

public class ControllerFieldIntro implements CanvasHandling
{
	private static ControllerFieldIntro instance;

	private GameLoopPause pause = new GameLoopPause(3);

	private ControllerCanvasIntroLabel ci;

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

	@Override
	public void initCanvas()
	{
		ci = ControllerCanvasIntroLabel.getInstance();
	}

	public void handleGameTick()
	{
		if (pause.hasExpired())
		{
			ci.clearCanvas();
			ci.drawString("Press 'F9' to play.", 20, Y);
			Y += 10;

			if (Y == 150)
			{
				Y = 40;
			}

			render();
		}
	}

	@Override
	public void render()
	{
		ci.renderIntro();
	}

}
