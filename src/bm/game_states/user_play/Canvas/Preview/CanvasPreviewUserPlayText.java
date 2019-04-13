package bm.game_states.user_play.Canvas.Preview;

import bm.BlockManiaPOC;
import bm.util.blocks.Tile;

public class CanvasPreviewUserPlayText
{
	private static CanvasPreviewUserPlayText instance;

	public static CanvasPreviewUserPlayText getInstance()
	{
		if (instance == null)
		{
			instance = new CanvasPreviewUserPlayText();
		}
		return instance;
	}

	private CanvasPreviewUserPlayText()
	{
	}

	public void renderPreview(Tile[][] field)
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

		BlockManiaPOC.getTxtCanvasPreview().setText(sb.toString());
	}
}
