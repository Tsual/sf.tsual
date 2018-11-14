package sf.task;

import sf.uds.interfaces.del.executable.IExec_0;

import java.util.*;


public class ThreadPoolTest
{
	static final Queue<IExec_0> tasks = new LinkedList<>();
	static final Object tasks_lock = new Object();
	static final Map<String,Integer> map=new HashMap<>();
	static final Object map_lock = new Object();

	static synchronized void out(Object str)
	{
		System.out.print(str);
	}

	static synchronized void outl(Object str)
	{
		System.out.println(str);
	}


	public static void main(String[] args) throws InterruptedException
	{
		for (int i = 0; i < 500; i++) {
			int finalI = i;
			tasks.offer(() ->
			{
				outl(tasks.size());
				Thread.sleep(Math.abs(new Random().nextLong()) % 300 + 1);
				return null;
			});
		}

		Object lock = new Object();

		final Runnable thread_exec_shell = () ->
		{
			while (true) {
				try {
					IExec_0 exec = null;
					final String th_name = Thread.currentThread().getName();
					synchronized (lock) {
						out(th_name + "<<Excuting<<");
						synchronized (tasks_lock) {
							if (tasks.size() > 0) {
								exec = tasks.poll();
								lock.notifyAll();
							}
						}
						if (exec == null) lock.wait();
					}

					if (exec != null) {
						try {
							exec.execute();
						} catch (Exception e) {
							e.printStackTrace();
						}
					}

					synchronized (map_lock) {
						if (!map.containsKey(th_name))
							map.put(th_name, 1);
						else {
							map.put(th_name, map.get(th_name) + 1);
						}
					}
				} catch (InterruptedException e) {
					e.printStackTrace();
				}
			}
		};

		List<Thread> ths = new ArrayList<>();
		for (int i = 0; i < 5; i++) {
			Thread th = new Thread(thread_exec_shell, "Thread-" + i);
			ths.add(th);
		}

		for (Thread thread : ths) {
			thread.start();
		}



		Thread.sleep(5000);

		for (int i = 0; i < 500; i++) {
			int finalI = i;
			tasks.offer(() ->
			{
				outl(finalI);
				Thread.sleep(Math.abs(new Random().nextLong()) % 50 + 1);
				return null;
			});
		}
		synchronized (lock) {
			lock.notifyAll();
		}



		for (Thread thread : ths) {
			thread.join();
		}


	}
}
