package sf.task;

import sf.uds.interfaces.del.executable.IExec_0;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

public class Test
{
	public static void main(String[] args) throws Exception
	{
		final List<IExec_0> ex_list = Collections.synchronizedList(new ArrayList());
		final int[] ia = {0};
		IExec_0 it = new IExec_0<Void>()
		{
			@Override
			public Void execute() throws Exception
			{
				System.out.println(ia[0]++);
				Thread.sleep(50);
				return null;
			}
		};
		for (int i = 0; i < 1; i++) {
			ex_list.add(it);
		}
		final Object lock = new Object();

		final Runnable runnable1 = new Runnable()
		{
			@Override
			public void run()
			{
				while (true) {
					synchronized (lock) {
						lock.notify();
						for (IExec_0 exec : ex_list) {
							try {
								System.out.print("exec-1<<");
								exec.execute();
							} catch (Exception e) {
								e.printStackTrace();
							}
						}
						try {
							lock.wait();
						} catch (InterruptedException e) {
							e.printStackTrace();
						}
					}

				}
			}
		};

		final Runnable runnable2 = new Runnable()
		{
			@Override
			public void run()
			{
				while (true) {
					synchronized (lock) {
						lock.notify();
						for (IExec_0 exec : ex_list) {
							try {
								System.out.print("exec-2<<");
								exec.execute();
							} catch (Exception e) {
								e.printStackTrace();
							}
						}
						try {
							lock.wait();
						} catch (InterruptedException e) {
							e.printStackTrace();
						}
					}
				}
			}
		};

		Thread thread1 = new Thread(runnable1);
		Thread thread2 = new Thread(runnable2);

		thread1.start();
		thread2.start();

		thread1.join(1000);
		thread2.join(1000);

	}
}
