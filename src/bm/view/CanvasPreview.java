package bm.view;

public class CanvasPreview
{
	private static CanvasPreview instance;
	public static CanvasPreview getInstance()
	{
		if (instance == null)
		{
			instance = new CanvasPreview();
		}
		return instance;
	}

	private CanvasPreview()
	{
	}
}
