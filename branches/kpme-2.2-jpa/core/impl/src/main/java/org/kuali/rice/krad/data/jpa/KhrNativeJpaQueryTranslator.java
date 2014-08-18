package org.kuali.rice.krad.data.jpa;

import javax.persistence.EntityManager;
import javax.persistence.TypedQuery;
import javax.persistence.criteria.*;

/**
 * Created by jjhanso on 8/15/14.
 */
public class KhrNativeJpaQueryTranslator extends NativeJpaQueryTranslator {
    /**
     * Creates a native JPA translator for queries.
     *
     * @param entityManager the entity manager to use for interacting with the database.
     */
    public KhrNativeJpaQueryTranslator(EntityManager entityManager) {
        super(entityManager);
    }

    public TypedQuery createQueryEffective(Class queryClazz, TranslationContext criteria, Predicate effectivePredicate) {

        //criteria.addPredicate(criteria.builder.and(criteria.getCriteriaPredicate(),effectivePredicate));
        criteria.addPredicate(criteria.builder.and(effectivePredicate));
        javax.persistence.criteria.CriteriaQuery jpaQuery = criteria.query;

        // it is important to not create an empty or predicate
        if (!criteria.predicates.isEmpty()) {
            jpaQuery = jpaQuery.where(criteria.getCriteriaPredicate());
        }
        return entityManager.createQuery(jpaQuery);
    }

    public CriteriaBuilder getCriteriaBuilder(TranslationContext context) {
        return context.builder;
    }

    public Root getQueryRoot(TranslationContext context) {
        return context.root;
    }
}
