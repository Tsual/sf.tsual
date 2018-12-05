/*
 * Copyright (c) 2018. Lorem ipsum dolor sit amet, consectetur adipiscing elit.
 * Morbi non lorem porttitor neque feugiat blandit. Ut vitae ipsum eget quam lacinia accumsan.
 * Etiam sed turpis ac ipsum condimentum fringilla. Maecenas magna.
 * Proin dapibus sapien vel ante. Aliquam erat volutpat. Pellentesque sagittis ligula eget metus.
 * Vestibulum commodo. Ut rhoncus gravida arcu.
 */

package sf.task;

/**
 * worker
 */
class TaskWorker extends Thread
{
	Task task;

	public TaskWorker(Runnable target)
	{
		super(target);
	}

	public TaskWorker(ThreadGroup group, Runnable target)
	{
		super(group, target);
	}

	public TaskWorker(String name)
	{
		super(name);
	}

	public TaskWorker(ThreadGroup group, String name)
	{
		super(group, name);
	}

	public TaskWorker(Runnable target, String name)
	{
		super(target, name);
	}

	public TaskWorker(ThreadGroup group, Runnable target, String name)
	{
		super(group, target, name);
	}

	public TaskWorker(ThreadGroup group, Runnable target, String name, long stackSize)
	{
		super(group, target, name, stackSize);
	}

	@Override
	public String toString(){
		return getName();
	}
}
