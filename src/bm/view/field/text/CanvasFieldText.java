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

		for (Tile[] row : field)
		{
			for (Tile tile : row)
			{
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
