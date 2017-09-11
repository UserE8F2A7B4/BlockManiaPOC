package bm.view;

import bm.block_handling.blocks.Tile;

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

	public void renderPreview()
	{

	}

	public void renderField(Tile[][] field)
	{
		CanvasFieldLabel.getInstance().render(field);
	}
}
