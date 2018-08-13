package gm;

import org.hibernate.HibernateException;
import org.hibernate.Metamodel;
import org.hibernate.query.Query;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.cfg.Configuration;
import sf.hibernate.beans.RuleInstanceEntity;

import javax.persistence.metamodel.EntityType;
import java.util.List;


public class Test
{
	private static final SessionFactory ourSessionFactory;

	static {
		try {
			ourSessionFactory = new Configuration().configure("hibernate/hibernate.cfg.xml").buildSessionFactory();
		} catch (Throwable ex) {
			throw new ExceptionInInitializerError(ex);
		}
	}

	public static Session getSession() throws HibernateException
	{
		return ourSessionFactory.openSession();
	}

	public static void main(final String[] args) throws Exception
	{
		try (Session session = getSession()) {
			final Metamodel metamodel = session.getSessionFactory().getMetamodel();
			final EntityType<RuleInstanceEntity> entity = metamodel.entity(RuleInstanceEntity.class.getName());
			final Query<RuleInstanceEntity> query = session.createQuery("from " + entity.getName(), RuleInstanceEntity.class);
			final List<RuleInstanceEntity> list = query.list();
			int a = 0;
		}
	}
}