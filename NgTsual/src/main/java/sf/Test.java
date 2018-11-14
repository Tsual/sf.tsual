package sf;

import sf.task.TaskHost;
import sf.task.TaskHub;

import java.util.Random;

public class Test
{

	public static void main(String[] args) throws Exception
	{
		try (TaskHost taskHost = new TaskHost(5)) {
			final TaskHub taskHub = taskHost.newTaskHub();
			for (int i = 0; i < 500; i++)
				taskHub.execute(() ->
				{
					Thread.sleep(Math.abs(new Random().nextLong()) % 300 + 1);
					System.out.println(Thread.currentThread().getName() + "<<TaskFinish");
					return null;
				});
			taskHub.waitAll();
		}
	}
}
