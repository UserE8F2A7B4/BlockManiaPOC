package bm.game_states.user_play.canvas.field;

import bm.BlockManiaPOC;
import bm.util.blocks.Tile;

public class CanvasFieldUserPlayText
{
	private static CanvasFieldUserPlayText instance;

	public static CanvasFieldUserPlayText getInstance()
	{
		if (instance == null)
		{
			instance = new CanvasFieldUserPlayText();
		}
		return instance;
	}

	private CanvasFieldUserPlayText()
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
