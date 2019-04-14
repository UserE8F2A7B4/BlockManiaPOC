package bm.game_states.demo_play.canvas.field;

public class ControllerFieldDemoPlay
{
	private static ControllerFieldDemoPlay instance;

	public static ControllerFieldDemoPlay getInstance()
	{
		if (instance == null)
		{
			instance = new ControllerFieldDemoPlay();
		}
		return instance;
	}

	public void init()
	{

	}


}
