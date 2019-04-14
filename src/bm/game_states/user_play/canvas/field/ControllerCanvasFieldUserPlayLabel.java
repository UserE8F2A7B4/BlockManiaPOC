package bm.game_states.user_play.canvas.field;

import bm.BlockManiaPOC;
import bm.util.GlobalData;
import bm.util.blocks.Tile;
import bm.view.ControllerCanvasBase;

import javax.swing.*;
import java.awt.*;

public class ControllerCanvasFieldUserPlayLabel extends ControllerCanvasBase
{
	private static ControllerCanvasFieldUserPlayLabel instance;

	public static ControllerCanvasFieldUserPlayLabel getInstance()
	{
		if (instance == null)
		{
			instance = new ControllerCanvasFieldUserPlayLabel();
		}
		return instance;
	}

	private ControllerCanvasFieldUserPlayLabel()
	{
		super(GlobalData.FIELD_WIDTH, GlobalData.FIELD_HEIGHT, Color.GREEN);
	}

	@Override
	public void drawTiles(Tile[][] field)
	{
		super.drawTiles(field);
		BlockManiaPOC.getLblCanvasField().setIcon(new ImageIcon(image));
	}

}
