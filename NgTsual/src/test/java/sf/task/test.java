package sf.task;

import java.util.Timer;
import java.util.TimerTask;

public class test
{
	static ThreadLocal tl = new ThreadLocal();

	public static void main(String[] args) throws Exception
	{
		Timer timer = new Timer();
		for (int i = 0; i < 100; i++)
			timer.schedule(new TimerTask()
			{
				@Override
				public void run()
				{
					System.out.println("ss");
				}
			}, 1000);

	}
}
