package bm.view;

import javax.swing.JTextPane;

import bm.BlockManiaPOC;
import bm.block_handling.blocks.Tile;

public class CanvasPreviewText
{
	private static CanvasPreviewText instance;
	public static CanvasPreviewText getInstance()
	{
		if (instance == null)
		{
			instance = new CanvasPreviewText();
		}
		return instance;
	}

	private CanvasPreviewText()
	{
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

		BlockManiaPOC.getTxtCanvasPreview().setText(sb.toString());
	}
}
