package com.traderobot.www.dbservice;




import com.querydsl.core.types.EntityPath;
import com.querydsl.core.types.Expression;
import com.querydsl.core.types.Predicate;
import com.querydsl.jpa.JPQLTemplates;
import com.querydsl.jpa.impl.JPAQuery;
import com.querydsl.jpa.impl.JPAQueryFactory;
import com.traderobot.www.model.GenericEntity;
import jakarta.annotation.PostConstruct;
import jakarta.persistence.EntityManager;
import jakarta.persistence.PersistenceContext;
import org.springframework.context.ApplicationContext;
import org.springframework.data.repository.support.Repositories;
import org.springframework.stereotype.Service;

import java.util.Optional;
import java.util.function.Function;

@Service
public class DataService {

    @PersistenceContext
    EntityManager em;

    private JPAQueryFactory jpaQueryFactory;

    @PostConstruct
    private void init() {
        jpaQueryFactory = new JPAQueryFactory(JPQLTemplates.DEFAULT, em);
    }

    public JPAQueryFactory queryFactory() {
        return jpaQueryFactory;
    }

    private final Repositories repositories;

    public DataService(ApplicationContext context) {
        this.repositories = new Repositories(context);
    }

    public <T, U> JPAQuery<T> selectFromWhere(EntityPath<T> entity, Class<U> cls, Function<U, Predicate> where) {
        return selectFrom(entity).where(where.apply((U) entity));
    }

    public <T> JPAQuery<T> selectFrom(EntityPath<T> entity) {
        return select(entity).from(entity);
    }

    public <T> JPAQuery<T> select(Expression<T> select) {
        return new JPAQuery<>(em).select(select);
    }

    public void save(GenericEntity<Long> entity) {
        if (entity == null) {
            throw new RuntimeException("Entity is null");
        }

        Optional<BaseRepository> repository = repositories
                .getRepositoryFor(entity.getClass())
                .filter(BaseRepository.class::isInstance)
                .map(BaseRepository.class::cast);

        if (repository.isEmpty()) {
            throw new RuntimeException("Not found repository for " + entity.getClass().getName());
        }

        repository.get().save(entity);
    }
    public EntityManager em() {
        return em;
    }

}
