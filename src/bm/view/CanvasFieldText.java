package bm.view;

import javax.swing.JTextPane;

import bm.block_handling.blocks.Tile;

public class CanvasFieldText
{
	private JTextPane txtCanvasField;

	private static CanvasFieldText instance;
	public static CanvasFieldText getInstance()
	{
		if (instance == null)
		{
			instance = new CanvasFieldText();
		}
		return instance;
	}

	private CanvasFieldText()
	{
	}

	public void setCanvasField(JTextPane txtCanvas)
	{
		txtCanvasField = txtCanvas;
	}

	public void renderField(Tile[][] field)
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
			}
			sb.append("\n");
		}

		txtCanvasField.setText(sb.toString());
	}

}
