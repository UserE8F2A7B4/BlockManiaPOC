package bm.game_states.user_play.canvas.preview;

import bm.BlockManiaPOC;
import bm.util.GlobalData;
import bm.util.blocks.Tile;
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

	public void renderPreview(Tile[][] field)
	{
		super.renderField(field);
		BlockManiaPOC.getLblCanvasPreview().setIcon(new ImageIcon(image));
	}

}
