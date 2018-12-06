package hibernate;


import hibernate.service.HibernateServiceFactory;
import hibernate.service.interfaces.IDbmgr;

public class Test2
{
	public static void main(String[] args) throws Exception
	{
		HibernateServiceFactory.getService(IDbmgr.class).SyncData();
	}
}
