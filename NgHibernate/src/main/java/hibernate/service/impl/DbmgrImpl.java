package hibernate.service.impl;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.cfg.Configuration;
import org.hibernate.query.Query;
import hibernate.service.interfaces.IDbmgr;
import sf.tquery.Iterators;
import sf.util.StringHelper;

import javax.persistence.metamodel.*;
import java.lang.reflect.Method;
import java.util.*;
import java.util.concurrent.atomic.AtomicInteger;

public class DbmgrImpl implements IDbmgr
{
	private static final SessionFactory baseSessionFactory;
	private static final SessionFactory wengdbSessionFactory;
	private static final Map<Class, Class> classMapping;

	static {
		try {
			baseSessionFactory = new Configuration().configure("hibernate/hibernate.base.cfg.xml").buildSessionFactory();
			wengdbSessionFactory = new Configuration().configure("hibernate/hibernate.wengdb.cfg.xml").buildSessionFactory();
			Map<Class, Class> map = new HashMap<>();
			map.put(hibernate.beans.base.RuleAttrEntity.class, hibernate.beans.wengdb.RuleAttrEntity.class);
			map.put(hibernate.beans.base.RuleAttrRelEntity.class, hibernate.beans.wengdb.RuleAttrRelEntity.class);
			map.put(hibernate.beans.base.RuleBusiRelEntity.class, hibernate.beans.wengdb.RuleBusiRelEntity.class);
			map.put(hibernate.beans.base.RuleBusiRelExpireEntity.class, hibernate.beans.wengdb.RuleBusiRelExpireEntity.class);
			map.put(hibernate.beans.base.RuleInstanceEntity.class, hibernate.beans.wengdb.RuleInstanceEntity.class);
			map.put(hibernate.beans.base.RuleMsgInfoEntity.class, hibernate.beans.wengdb.RuleMsgInfoEntity.class);
			map.put(hibernate.beans.base.RuleObjectEntity.class, hibernate.beans.wengdb.RuleObjectEntity.class);
			map.put(hibernate.beans.base.RuleParamValueEntity.class, hibernate.beans.wengdb.RuleParamValueEntity.class);
			map.put(hibernate.beans.base.RuleProdAttrMapingEntity.class, hibernate.beans.wengdb.RuleProdAttrMapingEntity.class);
			map.put(hibernate.beans.base.RuleRelatEntity.class, hibernate.beans.wengdb.RuleRelatEntity.class);
			map.put(hibernate.beans.base.RuleTemplateEntity.class, hibernate.beans.wengdb.RuleTemplateEntity.class);
			map.put(hibernate.beans.base.RuleTmplParamEntity.class, hibernate.beans.wengdb.RuleTmplParamEntity.class);
			map.put(hibernate.beans.base.RuleV2AccessEntity.class, hibernate.beans.wengdb.RuleV2AccessEntity.class);
			classMapping = Collections.unmodifiableMap(map);
		} catch (Throwable ex) {
			throw new ExceptionInInitializerError(ex);
		}
	}

	@Override
	public void SyncData() throws Exception
	{
		AtomicInteger modifyCount = new AtomicInteger();
		try (Session baseSession = baseSessionFactory.openSession();
		     Session wengdbSession = wengdbSessionFactory.openSession())
		{

			final Metamodel baseSessionMetamodel = baseSession.getMetamodel();
			final Metamodel wengdbSessionMetamodel = wengdbSession.getMetamodel();

			for (Map.Entry<Class, Class> entry : classMapping.entrySet()) {
				final EntityType baseEntity = baseSessionMetamodel.entity(entry.getKey());
				final Query baseSessionQuery = baseSession.createQuery("from " + baseEntity.getName(), entry.getKey());

				final EntityType wengdbEntity = wengdbSessionMetamodel.entity(entry.getValue());
				final Query wengdbSessionQuery = wengdbSession.createQuery("from " + wengdbEntity.getName(), entry.getValue());

				final List baseList = baseSessionQuery.list();
				final List wengdbList = wengdbSessionQuery.list();

				final Method wengdbIdJavaMember = (Method) wengdbEntity.getId(wengdbEntity.getIdType().getJavaType()).getJavaMember();
				final Method baseIdJavaMember = (Method) baseEntity.getId(baseEntity.getIdType().getJavaType()).getJavaMember();

				final List wengdbIdList = Iterators.get(wengdbList)
						.as(t -> wengdbIdJavaMember.invoke(t))
						.toList();

//				final List baseIdList = Iterators.get(baseList)
//						.as(t -> baseIdJavaMember.invoke(t))
//						.toList();
//
//				final List compareList = Iterators.get(baseList)
//						.where(t -> !wengdbIdList.contains(baseIdJavaMember.invoke(t)))
//						.toList();

				wengdbSession.beginTransaction();
				Iterators.get(baseList)
						.where(t -> !wengdbIdList.contains(baseIdJavaMember.invoke(t)))
						.as(t ->
						{
							final Object wengdbInstance = wengdbEntity.getJavaType().newInstance();
							for (Object baseAttr : baseEntity.getDeclaredAttributes()) {
								final String baseAttrName = ((SingularAttribute) baseAttr).getName();
								final Method baseAttrMethod = (Method) ((SingularAttribute) baseAttr).getJavaMember();
								final Object baseAttrValue = baseAttrMethod.invoke(t);

								final Method wengdbValueSetter = wengdbInstance.getClass().getMethod("set" + StringHelper.firstCharUpper(baseAttrName), baseAttrMethod.getReturnType());
								wengdbValueSetter.invoke(wengdbInstance, baseAttrValue);
							}
							return wengdbInstance;
						})
						.foreach(t ->
						{
							wengdbSession.save(t);
							modifyCount.getAndIncrement();
						});
				wengdbSession.getTransaction().commit();
			}
		}
		System.out.println("Modify Count:" + modifyCount.toString());
	}

	@Override
	public void test() throws Exception
	{
		try (Session wengdbSession = wengdbSessionFactory.openSession()) {
			final Metamodel wengdbSessionMetamodel = wengdbSession.getMetamodel();

			final Set<EmbeddableType<?>> embeddables = wengdbSessionMetamodel.getEmbeddables();
			final Set<ManagedType<?>> managedTypes = wengdbSessionMetamodel.getManagedTypes();
			final Set<EntityType<?>> entities = wengdbSessionMetamodel.getEntities();

			final EntityType wengdbEntity = wengdbSessionMetamodel.entity(hibernate.beans.wengdb.RuleAttrEntity.class);
			wengdbSession.createQuery("from "+wengdbEntity.getName()+" where attrId=1");

			int a = 0;
		}
	}
}
