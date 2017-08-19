package bm.line_removal;

public class ControllerLineRemoval
{
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

	public void handleGameTick()
	{
	}

}
