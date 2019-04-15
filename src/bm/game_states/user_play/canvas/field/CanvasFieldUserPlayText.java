package bm.game_states.user_play.canvas.field;

import bm.BlockManiaPOC;
import bm.view.CanvasBase;

public class CanvasFieldUserPlayText extends CanvasBase
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

	@Override
	public void render()
	{
		BlockManiaPOC.getTxtCanvasField().setText(canvasText.toString());
	}

}
