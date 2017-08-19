package bm.view;

public class CanvasField
{
	private static CanvasField instance;
	public static CanvasField getInstance()
	{
		if (instance == null)
		{
			instance = new CanvasField();
		}
		return instance;
	}

	private CanvasField()
	{
	}
}
