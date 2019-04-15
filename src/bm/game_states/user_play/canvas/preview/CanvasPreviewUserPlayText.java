package bm.game_states.user_play.canvas.preview;

import bm.BlockManiaPOC;
import bm.view.CanvasBase;

public class CanvasPreviewUserPlayText extends CanvasBase
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

	@Override
	public void render()
	{
		BlockManiaPOC.getTxtCanvasPreview().setText(canvasText.toString());
	}

}
