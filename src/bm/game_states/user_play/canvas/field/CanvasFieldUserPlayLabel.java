package bm.game_states.user_play.canvas.field;

import bm.BlockManiaPOC;
import bm.util.GlobalData;
import bm.util.blocks.Tile;
import bm.view.CanvasBase;

import javax.swing.*;
import java.awt.*;

public class CanvasFieldUserPlayLabel extends CanvasBase
{
	private static CanvasFieldUserPlayLabel instance;

	public static CanvasFieldUserPlayLabel getInstance()
	{
		if (instance == null)
		{
			instance = new CanvasFieldUserPlayLabel();
		}
		return instance;
	}

	private CanvasFieldUserPlayLabel()
	{
		super(GlobalData.FIELD_WIDTH, GlobalData.FIELD_HEIGHT, Color.GREEN);
	}

	public void renderField(Tile[][] field)
	{
		super.renderField(field);
		BlockManiaPOC.getLblCanvasField().setIcon(new ImageIcon(image));
	}

}
