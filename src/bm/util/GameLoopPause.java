package bm.util;

//***********************************************************************
//
//Vertraging en toch een vaste frame-rate :
//Deze class fungeert als een soort 'sleep' in een (snel repeterende) 
//game-thread zonder deze thread zelf af te remmen.
//
//GameLoopPause pause01 = new GameLoopPause(10); // Skip 10 game-ticks.
//
//if (pause01.hasExpired())
// doSomething();
//
//***********************************************************************

public class GameLoopPause
{
	private int counter;
	private int ticksToPause;

	public GameLoopPause(int numberOfTicksToPause)
	{
		ticksToPause = numberOfTicksToPause;
		counter = ticksToPause;
	}

	public boolean hasExpired()
	{
		counter--;
		if (counter == 0)
		{
			reset();
			return true;
		}
		else
		{
			return false;
		}
	}

	public void reset()
	{
		counter = ticksToPause;
	}

}
