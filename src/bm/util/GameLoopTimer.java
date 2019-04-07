package bm.util;

import bm.FlowMain;

public class GameLoopTimer implements Runnable
{
	private FlowMain flow = FlowMain.getInstance();

	private final static int SLEEP_TIME = 50; // Maximum sleep-time. Default = 50.
	private long period;

	private Thread gameThread;
	private boolean keepGoing = false;

	private static final int NUMBER_OF_DELAYS_PER_YIELD = 16;

	private static GameLoopTimer instance;

	public static GameLoopTimer getInstance()
	{
		if (instance == null)
		{
			instance = new GameLoopTimer();
		}
		return instance;
	}

	private GameLoopTimer()
	{
	}

	public void init()
	{
		period = SLEEP_TIME * 1_000_000L; // Period between TimerLoop-triggers in nano-secs.
	}

	public void stop()
	{
		keepGoing = false;
	}

	public void start()
	{
		if (gameThread == null || !gameThread.isAlive())
		{
			keepGoing = true;
			gameThread = new Thread(this);
			gameThread.start();
		}
	}

	@Override
	public void run()
	{
		long beforeTime, afterTime, timeDiff, sleepTime;
		long overSleepTime = 0L;
		int noDelays = 0;
		// long gameStartTime = System.nanoTime();
		beforeTime = System.nanoTime();

		// boolean skip = false;

		keepGoing = true;
		while (keepGoing)
		{
			flow.handleGameTick();

			//    	if (!skip) // TODO : dit er later weer uithalen.
			//    	{
			//    		View.getInstance().paintScreen();
			//    		skip = !skip;
			//    	}

			//-- Create a steady, realtime-based game-puls :

			afterTime = System.nanoTime();
			timeDiff = afterTime - beforeTime;
			sleepTime = (period - timeDiff) - overSleepTime;

			//System.out.println("sleepTime : " + sleepTime/1000000L);

			if (sleepTime > 0) // There is some time left in this cycle.
			{
				try
				{
					Thread.sleep(sleepTime / 1000000L); // nano -> ms
				}
				catch (InterruptedException ex)
				{
					System.out.println("TimerLoop-sleep was interrupted.");
				}
				overSleepTime = (System.nanoTime() - afterTime) - sleepTime;
			}
			else // sleepTime <= 0; the frame took longer than the period.
			{
				overSleepTime = 0L;

				if (++noDelays >= NUMBER_OF_DELAYS_PER_YIELD)
				{
					Thread.yield(); // Give another thread a chance to run.
					System.out.println("Yield.");
					noDelays = 0;
				}
			}
			beforeTime = System.nanoTime();
		}
	}

}
