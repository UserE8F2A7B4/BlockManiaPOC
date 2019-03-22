package bm;

import bm.BlockManiaPOC.UserInput;
import bm.ControllerMain.GameState;

public class FlowMain
{
	private ControllerMain cm;
	private static FlowMain instance;

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
		cm = ControllerMain.getInstance();
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

	private void flowBlockHandling()
	{
		if (cm.staat_er_een_block_op_de_preview())
		{
			if (cm.staat_er_een_block_op_het_veld())
			{
				if (cm.is_het_tijd_voor_een_move_down())
				{
					if (cm.probeer_actie_uit_te_voeren_op_het_block(UserInput.MOVE_DOWN))
					{
						cm.updateScore();
					}
      	else
					{
						cm.verwijder_het_block_uit_het_veld();
						if (cm.zijn_er_volledig_gevulde_regels_ontstaan())
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
							if (cm.probeer_actie_uit_te_voeren_op_het_block(UserInput.MOVE_DOWN))
							{
								cm.updateScore();
							}
          		else
							{
								cm.verwijder_het_block_uit_het_veld();
								if (cm.zijn_er_volledig_gevulde_regels_ontstaan())
								{
									cm.changeGameState(GameState.LINE_REMOVAL);
								}
							}
						}
						else // Move left or right or flip or rotate.
						{
							cm.probeer_actie_uit_te_voeren_op_het_block(input);
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
				if (cm.probeer_het_previewblock_te_verplaatsen_naar_het_veld())
				{
					// Het previewblock is nu verplaatst naar het veld.
				}
				else
				{
					// Er is [niet] genoeg ruimte op het veld om het previewblock te plaatsen ; GAME OVER !..
					cm.handleGameOver();
				}
			}
		}
		else // Er is [geen] block aanwezig op de Preview.
		{
			cm.plaats_een_nieuw_willekeurig_block_op_de_preview();
		}
	}

	private void flowLineRemoval()
	{
		if (cm.is_animatie_01_nog_bezig())
		{
			cm.update_animatie_01();
		}
		else if (cm.is_animatie_02_nog_bezig())
		{
			cm.update_animatie_02();
		}
		else
		{
			cm.verwijder_de_volledige_regels();
			cm.verplaats_de_overgebleven_regels_naar_beneden();
			cm.updateScore();
			cm.changeGameState(GameState.BLOCK_HANDLING);
		}
	}

}
