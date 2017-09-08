package bm.view;

import javax.swing.JTextPane;

import bm.block_handling.blocks.Tile;

public class CanvasField
{
	private JTextPane txtCanvasPreview, txtCanvasField;

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

	public void setCanvasPreview(JTextPane txtCanvas)
	{
		txtCanvasPreview = txtCanvas;
	}

	public void renderPreview(Tile[][] field)
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

		txtCanvasPreview.setText(sb.toString());
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
