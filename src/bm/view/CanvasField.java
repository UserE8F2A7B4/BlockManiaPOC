package bm.view;

import javax.swing.JTextArea;

import bm.block_handling.blocks.Tile;

public class CanvasField
{
	private JTextArea txtCanvas;

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

	public void setCanvas(JTextArea te)
	{
		txtCanvas = te;
	}

	public void render(Tile[][] field)
	{
		StringBuilder sb = new StringBuilder();

		for (int row = 0 ; row < field.length ; row++)
		{
			for (int col = 0 ; col < field[row].length ; col++)
			{
				Tile tile = field[row][col];

				if (tile == null)
				{
					sb.append("∙");
				}
				else
				{
					sb.append("■");
				}
				// txtCanvas.append ("nr : " + field[row][col] + "\n" );
			}
			sb.append("\n");
		}

		txtCanvas.setText(sb.toString());

	}



}
