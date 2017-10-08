package bm.view;

import bm.block_handling.blocks.Tile;

public class ControllerView
{
	private CanvasFieldLabel canvasField;

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
		canvasField = CanvasFieldLabel.getInstance();
	}

	public void renderPreview()
	{
	}

	public void renderField(Tile[][] field)
	{
		canvasField.clearField();
		for (int row = 0 ; row < field.length ; row++)
		{
			for (int col = 0 ; col < field[row].length ; col++)
			{
				canvasField.drawTile(field[row][col]);
			}
		}
		canvasField.render();
	}

}
