package bm.block_handling;

public class ControllerBlockHandling
{
	private static ControllerBlockHandling instance;
	public static ControllerBlockHandling getInstance()
	{
		if (instance == null)
		{
			instance = new ControllerBlockHandling();
		}
		return instance;
	}

	private ControllerBlockHandling()
	{
	}

	public void handleGameTick()
	{
	}

}
