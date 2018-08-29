package hibernate;


import sf.hibernate.service.HibernateServiceFactory;
import sf.hibernate.service.interfaces.IDbmgr;

public class Test2
{
	public static void main(String[] args) throws Exception
	{
		HibernateServiceFactory.getService(IDbmgr.class).SyncData();
	}
}
