package bm;

import bm.BlockManiaPOC.UserInput;
import bm.ControllerMain.GameState;
import bm.block_handling.ControllerBlockHandling;
import bm.line_removal.ControllerLineRemoval;

public class FlowMain
{
	private static FlowMain instance;

	private ControllerMain cm;
	private ControllerBlockHandling cb;
	private ControllerLineRemoval cl;

	public static FlowMain getInstance()
	{
		if (instance == null)
		{
			instance = new FlowMain();
		}
		return instance;
	}

	private FlowMain()
	{
	}

	public void init()
	{
		cm = ControllerMain.getInstance();

		cb = ControllerBlockHandling.getInstance();
		cb.init();

		cl = ControllerLineRemoval.getInstance();
		cl.init();
	}

	public void handleGameTick()
	{
			switch (cm.getGameState())
			{
				case BLOCK_HANDLING : flowBlockHandling() ; break;
				case LINE_REMOVAL   : flowLineRemoval() ; break;
				default             : assert false : "Invalid game-state"; break;
			}
	}


	boolean linesCreated;

	private void flowBlockHandling()
	{
		System.out.println("game tick block handling");


		if (!linesCreated)
		{
			linesCreated = true;
			cm.testRowRemoval();
			return;
		}


		if (!cb.staat_er_een_block_op_de_preview())
		{
			cb.plaats_een_nieuw_willekeurig_block_op_de_preview();
		}

		if (cb.staat_er_een_block_op_het_veld())
		{
			if (cb.is_het_tijd_voor_een_move_down())
			{
				if (cb.probeer_actie_uit_te_voeren_op_het_block(UserInput.MOVE_DOWN))
				{
					cm.updateScore();
				}
			else
				{
					cb.verwijder_het_block_uit_het_veld();
					if (cb.zijn_er_volledig_gevulde_regels_ontstaan())
					{
						cm.changeGameState(GameState.LINE_REMOVAL);
					}
				}
			}
			else
			{
				final UserInput input = cm.getUserRequest();
				if (input != UserInput.NONE)
				{
					if (input == UserInput.MOVE_DOWN)
					{
						if (cb.probeer_actie_uit_te_voeren_op_het_block(UserInput.MOVE_DOWN))
						{
							cm.updateScore();
						}
						else
						{
							cb.verwijder_het_block_uit_het_veld();
							if (cb.zijn_er_volledig_gevulde_regels_ontstaan())
							{
								cm.changeGameState(GameState.LINE_REMOVAL);
							}
						}
					}
					else // Move left or right or flip or rotate.
					{
						cb.probeer_actie_uit_te_voeren_op_het_block(input);
					}
				}
				else // Er is [geen] user-input.
				{
					// Geen actie.
				}
			}
		}
		else // Er is [geen] block aanwezig op het veld.
		{
			if (cb.probeer_het_previewblock_te_verplaatsen_naar_het_veld())
			{
				// Het previewblock is nu verplaatst naar het veld.
			}
			else
			{
				// Er is [niet] genoeg ruimte op het veld om het previewblock te plaatsen ; GAME OVER !..
				cb.handleGameOver();
			}
		}
	}

	private void flowLineRemoval()
	{
		//		System.out.println("game tick line removal");
		//
		//		if (cl.is_animatie_01_nog_bezig())
		//		{
		//			cl.update_animatie_01();
		//		}
		//		else if (cl.is_animatie_02_nog_bezig())
		//		{
		//			cl.update_animatie_02();
		//		}
		//		else
		//		{
		//			cl.verwijder_de_volledige_regels();
		//			cl.verplaats_de_overgebleven_regels_naar_beneden();
		//
		//			cm.updateScore();
		//			cm.changeGameState(GameState.BLOCK_HANDLING);
		//		}

		cl.handleGameTick();
	}

}
