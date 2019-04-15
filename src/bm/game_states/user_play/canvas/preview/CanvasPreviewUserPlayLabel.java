package bm.game_states.user_play.canvas.preview;

import bm.BlockManiaPOC;
import bm.util.GlobalData;
import bm.view.CanvasBase;

import javax.swing.*;
import java.awt.*;

public class CanvasPreviewUserPlayLabel extends CanvasBase
{
	private static CanvasPreviewUserPlayLabel instance;

	public static CanvasPreviewUserPlayLabel getInstance()
	{
		if (instance == null)
		{
			instance = new CanvasPreviewUserPlayLabel();
		}
		return instance;
	}

	private CanvasPreviewUserPlayLabel()
	{
		super(GlobalData.PREVIEW_WIDTH, GlobalData.PREVIEW_HEIGHT, Color.MAGENTA);
	}

	@Override
	public void render()
	{
		BlockManiaPOC.getLblCanvasPreview().setIcon(new ImageIcon(image));
	}

}
