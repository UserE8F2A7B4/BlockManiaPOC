package bm.view.preview.label;

import bm.BlockManiaPOC;
import bm.util.GlobalData;
import bm.util.blocks.Tile;
import bm.view.UtilView;

import javax.swing.*;
import java.awt.*;
import java.awt.image.BufferedImage;

public class CanvasPreviewLabel
{
	private static CanvasPreviewLabel instance;

	private Graphics2D canvas;
	private BufferedImage image;

	public static CanvasPreviewLabel getInstance()
	{
		if (instance == null)
		{
			instance = new CanvasPreviewLabel();
		}
		return instance;
	}

	private CanvasPreviewLabel()
	{
		init();
	}

	private void init()
	{
		image = UtilView.createImage(GlobalData.PREVIEW_WIDTH, GlobalData.PREVIEW_HEIGHT);
		canvas = UtilView.createCanvas(image);

		canvas.setColor(Color.GREEN);
		canvas.fillRect(0,0, GlobalData.PREVIEW_WIDTH, GlobalData.PREVIEW_HEIGHT);

		canvas.setColor(Color.BLACK);
		canvas.drawString("View-preview", 2, 2);
	}

	private void clearField()
	{
		canvas.setColor(Color.GREEN);
		canvas.fillRect(0,0, GlobalData.PREVIEW_WIDTH, GlobalData.PREVIEW_HEIGHT);
	}

	private void drawTile(Tile tile)
	{
		if (tile != null)
		{
			UtilView.addTile(canvas, tile);
		}
	}

	public void renderPreview(Tile[][] field)
	{
		clearField();

		for (Tile[] row : field)
		{
			for (Tile tile : row)
			{
				drawTile(tile);
			}
		}

		BlockManiaPOC.getLblCanvasPreview().setIcon(new ImageIcon(image));
	}

}
