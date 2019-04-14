package bm.game_states.user_play.canvas.preview;

import bm.BlockManiaPOC;
import bm.util.GlobalData;
import bm.util.blocks.Tile;
import bm.view.ControllerCanvasBase;

import javax.swing.*;
import java.awt.*;


// Ik ben de controller voor het canvas van de [userplay]-[preview].
//
// Alles wat voor de [userplay-preview] op het scherm moet komen
// kun je aan mij doorgeven, (via de diverse [draw]-methods),
// en je kunt doorgeven [wanneer] het op het scherm moet
// komen, (via de [render]-method).

public class ControllerCanvasPreviewUserPlayLabel extends ControllerCanvasBase
{
	private static ControllerCanvasPreviewUserPlayLabel instance;

	public static ControllerCanvasPreviewUserPlayLabel getInstance()
	{
		if (instance == null)
		{
			instance = new ControllerCanvasPreviewUserPlayLabel();
		}
		return instance;
	}

	private ControllerCanvasPreviewUserPlayLabel()
	{
		super(GlobalData.PREVIEW_WIDTH, GlobalData.PREVIEW_HEIGHT, Color.MAGENTA);
	}

	// De [render]-method mag alleen renderen.
	public void renderPreview()
	{
		BlockManiaPOC.getLblCanvasPreview().setIcon(new ImageIcon(image));
	}

}
