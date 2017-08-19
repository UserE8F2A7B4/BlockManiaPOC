package bm.view;

public class ControllerView
{
	private static ControllerView instance;
	public static ControllerView getInstance()
	{
		if (instance == null)
		{
			instance = new ControllerView();
		}
		return instance;
	}

	private ControllerView()
	{
	}
}
