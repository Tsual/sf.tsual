package sf.tquery.exceptions;

import sf.uds.interfaces.del.IDelegate;

public class TqueryDelegateEmptyException extends Exception
{
	IDelegate delegate;

	public TqueryDelegateEmptyException(IDelegate delegate)
	{
		this.delegate = delegate;
	}

	public IDelegate getDelegate()
	{
		return delegate;
	}

	@Override
	public String toString()
	{
		return String.format("委托{0}为空", delegate);
	}

	@Override
	public String getMessage()
	{
		return toString();
	}
}
