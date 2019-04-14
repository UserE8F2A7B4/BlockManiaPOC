package bm.game_states.user_play.canvas.preview;

import bm.BlockManiaPOC;
import bm.util.blocks.Tile;

public class ControllerCanvasPreviewUserPlayText
{
	private static ControllerCanvasPreviewUserPlayText instance;

	private StringBuilder sb = new StringBuilder();

	public static ControllerCanvasPreviewUserPlayText getInstance()
	{
		if (instance == null)
		{
			instance = new ControllerCanvasPreviewUserPlayText();
		}
		return instance;
	}

	private ControllerCanvasPreviewUserPlayText()
	{
	}

	public void drawTiles(Tile[][] field)
	{
		sb.setLength(0);

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
	}

	public void renderPreview()
	{
		BlockManiaPOC.getTxtCanvasPreview().setText(sb.toString());
	}


}
