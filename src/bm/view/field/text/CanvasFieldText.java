package bm.view.field.text;

import bm.BlockManiaPOC;
import bm.util.blocks.Tile;

public class CanvasFieldText
{
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

		BlockManiaPOC.getTxtCanvasField().setText(sb.toString());
	}

}
