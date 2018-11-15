package sf.task;

import sf.task.TaskHost;
import sf.task.TaskHub;
import sun.misc.ThreadGroupUtils;

import java.util.Random;


public class Test
{
	static Runnable runnable;

	public static void main(String[] args) throws Exception
	{
		try (TaskHost taskHost = new TaskHost("default", 5, 250, 50L)) {
			for (int i = 0; i < 10; i++) {
				testHub(taskHost.newTaskHub(500L));
				System.out.println(taskHost.getThreadGroup().activeCount());
			}
		}
	}

	static void testHub(TaskHub hub)
	{
		for (int i = 0; i < 500; i++)
			hub.execute(() ->
			{
				Thread.sleep(Math.abs(new Random().nextLong()) % 50 + 1);
				//System.out.println(Thread.currentThread().getName() + "<<TaskFinish");
				return null;
			});
		hub.waitAll();
	}

}
