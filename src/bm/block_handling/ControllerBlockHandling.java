package bm.block_handling;

import bm.BlockManiaPOC;

import javax.swing.*;

public class ControllerBlockHandling
{
	private ControllerField controllerField;
	private ControllerPreview controllerPreview;
	private BlockProcessor blockProcessor;

	private static ControllerBlockHandling instance;
	public static ControllerBlockHandling getInstance()
	{
		if (instance == null)
		{
			instance = new ControllerBlockHandling();
		}
		return instance;
	}

	private ControllerBlockHandling()
	{
	}

	public void init()
	{
		controllerField = ControllerField.getInstance();
		controllerPreview = ControllerPreview.getInstance();
		blockProcessor = BlockProcessor.getInstance();
	}

//	public void changeGameState(GameState newGameState)
//	{
//		ControllerMain.getInstance().changeGameState(newGameState);
//	}

	public boolean staat_er_een_block_op_de_preview()
	{
		return controllerPreview.hasBlock();
	}

	public void plaats_een_nieuw_willekeurig_block_op_de_preview()
	{
		controllerPreview.loadRandomBlock();
		// controllerPreview.loadNextBlock();
	}

	public boolean probeer_het_previewblock_te_verplaatsen_naar_het_veld()
	{
		return blockProcessor.tryToPlaceNewBlockOnField();
	}

	public boolean staat_er_een_block_op_het_veld()
	{
		return blockProcessor.hasBlock();
	}

	public boolean is_het_tijd_voor_een_move_down()
	{
		return false;
	}

	public boolean probeer_actie_uit_te_voeren_op_het_block(BlockManiaPOC.UserInput input)
	{
		if (input == BlockManiaPOC.UserInput.MOVE_DOWN)
		{
			return blockProcessor.tryToMoveBlockDown();
		}
		else if (input == BlockManiaPOC.UserInput.MOVE_UP)
		{
			return blockProcessor.tryToMoveBlockUp();
		}
		else if (input == BlockManiaPOC.UserInput.MOVE_LEFT)
		{
			return blockProcessor.tryToMoveBlockLeft();
		}
		else if (input == BlockManiaPOC.UserInput.MOVE_RIGHT)
		{
			return blockProcessor.tryToMoveBlockRight();
		}
		else if (input == BlockManiaPOC.UserInput.FLIP)
		{
			return blockProcessor.tryToFlipBlock();
		}
		else if (input == BlockManiaPOC.UserInput.ROTATE)
		{
			return blockProcessor.tryToRotateBlock();
		}
		else
		{
			return false;
		}
	}

	public void verwijder_het_block_uit_het_veld()
	{
		blockProcessor.removeBlock();
	}

	public boolean zijn_er_volledig_gevulde_regels_ontstaan()
	{
		return controllerField.hasCompletedRows();
	}

	public void handleGameOver()
	{
		JOptionPane.showMessageDialog(null, "GAME OVER !..", "BlockMania", JOptionPane.INFORMATION_MESSAGE);
	}

}
