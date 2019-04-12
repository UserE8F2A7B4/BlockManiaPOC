package bm.view.preview.text;

import bm.BlockManiaPOC;
import bm.util.blocks.Tile;

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
