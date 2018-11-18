package sf.task;

import java.lang.reflect.Field;
import java.security.AccessController;
import java.security.PrivilegedAction;
import java.util.HashMap;
import java.util.Map;
import java.util.Timer;
import java.util.TimerTask;

import static java.security.AccessController.doPrivileged;

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
