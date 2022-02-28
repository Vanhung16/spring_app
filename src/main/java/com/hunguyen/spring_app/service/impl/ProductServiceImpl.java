package com.hunguyen.spring_app.service.impl;

import com.hunguyen.spring_app.domain.Product;
import com.hunguyen.spring_app.repository.ProductRepository;
import com.hunguyen.spring_app.service.ProductService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.IncorrectResultSizeDataAccessException;
import org.springframework.data.domain.Example;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.data.repository.query.FluentQuery;
import org.springframework.stereotype.Service;

import javax.persistence.EntityNotFoundException;
import java.util.List;
import java.util.Optional;
import java.util.function.Function;

@Service
public class ProductServiceImpl implements ProductService {
    @Autowired
    ProductRepository productRepository;

    @Override
    public List<Product> findAll() {
        return productRepository.findAll();
    }

    @Override
    public List<Product> findAll(Sort sort) {
        return productRepository.findAll(sort);
    }

    @Override
    public List<Product> findAllById(Iterable<Long> longs) {
        return productRepository.findAllById(longs);
    }

    @Override
    public <S extends Product> List<S> saveAll(Iterable<S> entities) {
        return productRepository.saveAll(entities);
    }

    /**
     * Flushes all pending changes to the database.
     */
    @Override
    public void flush() {
        productRepository.flush();
    }

    /**
     * Saves an entity and flushes changes instantly.
     *
     * @param entity entity to be saved. Must not be {@literal null}.
     * @return the saved entity
     */
    @Override
    public <S extends Product> S saveAndFlush(S entity) {
        return productRepository.saveAndFlush(entity);
    }

    /**
     * Saves all entities and flushes changes instantly.
     *
     * @param entities entities to be saved. Must not be {@literal null}.
     * @return the saved entities
     * @since 2.5
     */
    @Override
    public <S extends Product> List<S> saveAllAndFlush(Iterable<S> entities) {
        return productRepository.saveAllAndFlush(entities);
    }

    /**
     * Deletes the given entities in a batch which means it will create a single query. This kind of operation leaves JPAs
     * first level cache and the database out of sync. Consider flushing the {@link EntityManager} before calling this
     * method.
     *
     * @param entities entities to be deleted. Must not be {@literal null}.
     * @deprecated Use {@link #deleteAllInBatch(Iterable)} instead.
     */
    @Override
    @Deprecated
    public void deleteInBatch(Iterable<Product> entities) {
        productRepository.deleteInBatch(entities);
    }

    /**
     * Deletes the given entities in a batch which means it will create a single query. This kind of operation leaves JPAs
     * first level cache and the database out of sync. Consider flushing the {@link EntityManager} before calling this
     * method.
     *
     * @param entities entities to be deleted. Must not be {@literal null}.
     * @since 2.5
     */
    @Override
    public void deleteAllInBatch(Iterable<Product> entities) {
        productRepository.deleteAllInBatch(entities);
    }

    /**
     * Deletes the entities identified by the given ids using a single query. This kind of operation leaves JPAs first
     * level cache and the database out of sync. Consider flushing the {@link EntityManager} before calling this method.
     *
     * @param longs the ids of the entities to be deleted. Must not be {@literal null}.
     * @since 2.5
     */
    @Override
    public void deleteAllByIdInBatch(Iterable<Long> longs) {
        productRepository.deleteAllByIdInBatch(longs);
    }

    /**
     * Deletes all entities in a batch call.
     */
    @Override
    public void deleteAllInBatch() {
        productRepository.deleteAllInBatch();
    }

    /**
     * Returns a reference to the entity with the given identifier. Depending on how the JPA persistence provider is
     * implemented this is very likely to always return an instance and throw an
     * {@link EntityNotFoundException} on first access. Some of them will reject invalid identifiers
     * immediately.
     *
     * @param aLong must not be {@literal null}.
     * @return a reference to the entity with the given identifier.
     * @see EntityManager#getReference(Class, Object) for details on when an exception is thrown.
     * @deprecated use {@link JpaRepository#getById(ID)} instead.
     */
    @Override
    @Deprecated
    public Product getOne(Long aLong) {
        return productRepository.getOne(aLong);
    }

    /**
     * Returns a reference to the entity with the given identifier. Depending on how the JPA persistence provider is
     * implemented this is very likely to always return an instance and throw an
     * {@link EntityNotFoundException} on first access. Some of them will reject invalid identifiers
     * immediately.
     *
     * @param aLong must not be {@literal null}.
     * @return a reference to the entity with the given identifier.
     * @see EntityManager#getReference(Class, Object) for details on when an exception is thrown.
     * @since 2.5
     */
    @Override
    public Product getById(Long aLong) {
        return productRepository.getById(aLong);
    }

    @Override
    public <S extends Product> List<S> findAll(Example<S> example) {
        return productRepository.findAll(example);
    }

    @Override
    public <S extends Product> List<S> findAll(Example<S> example, Sort sort) {
        return productRepository.findAll(example, sort);
    }

    /**
     * Returns a {@link Page} of entities meeting the paging restriction provided in the {@code Pageable} object.
     *
     * @param pageable
     * @return a page of entities
     */
    @Override
    public Page<Product> findAll(Pageable pageable) {
        return productRepository.findAll(pageable);
    }

    /**
     * Saves a given entity. Use the returned instance for further operations as the save operation might have changed the
     * entity instance completely.
     *
     * @param entity must not be {@literal null}.
     * @return the saved entity; will never be {@literal null}.
     * @throws IllegalArgumentException in case the given {@literal entity} is {@literal null}.
     */
    @Override
    public <S extends Product> S save(S entity) {
        return productRepository.save(entity);
    }

    /**
     * Retrieves an entity by its id.
     *
     * @param aLong must not be {@literal null}.
     * @return the entity with the given id or {@literal Optional#empty()} if none found.
     * @throws IllegalArgumentException if {@literal id} is {@literal null}.
     */
    @Override
    public Optional<Product> findById(Long aLong) {
        return productRepository.findById(aLong);
    }

    /**
     * Returns whether an entity with the given id exists.
     *
     * @param aLong must not be {@literal null}.
     * @return {@literal true} if an entity with the given id exists, {@literal false} otherwise.
     * @throws IllegalArgumentException if {@literal id} is {@literal null}.
     */
    @Override
    public boolean existsById(Long aLong) {
        return productRepository.existsById(aLong);
    }

    /**
     * Returns the number of entities available.
     *
     * @return the number of entities.
     */
    @Override
    public long count() {
        return productRepository.count();
    }

    /**
     * Deletes the entity with the given id.
     *
     * @param aLong must not be {@literal null}.
     * @throws IllegalArgumentException in case the given {@literal id} is {@literal null}
     */
    @Override
    public void deleteById(Long aLong) {
        productRepository.deleteById(aLong);
    }

    /**
     * Deletes a given entity.
     *
     * @param entity must not be {@literal null}.
     * @throws IllegalArgumentException in case the given entity is {@literal null}.
     */
    @Override
    public void delete(Product entity) {
        productRepository.delete(entity);
    }

    /**
     * Deletes all instances of the type {@code T} with the given IDs.
     *
     * @param longs must not be {@literal null}. Must not contain {@literal null} elements.
     * @throws IllegalArgumentException in case the given {@literal ids} or one of its elements is {@literal null}.
     * @since 2.5
     */
    @Override
    public void deleteAllById(Iterable<? extends Long> longs) {
        productRepository.deleteAllById(longs);
    }

    /**
     * Deletes the given entities.
     *
     * @param entities must not be {@literal null}. Must not contain {@literal null} elements.
     * @throws IllegalArgumentException in case the given {@literal entities} or one of its entities is {@literal null}.
     */
    @Override
    public void deleteAll(Iterable<? extends Product> entities) {
        productRepository.deleteAll(entities);
    }

    /**
     * Deletes all entities managed by the repository.
     */
    @Override
    public void deleteAll() {
        productRepository.deleteAll();
    }

    /**
     * Returns a single entity matching the given {@link Example} or {@link Optional#empty()} if none was found.
     *
     * @param example must not be {@literal null}.
     * @return a single entity matching the given {@link Example} or {@link Optional#empty()} if none was found.
     * @throws IncorrectResultSizeDataAccessException if the Example yields more than one result.
     */
    @Override
    public <S extends Product> Optional<S> findOne(Example<S> example) {
        return productRepository.findOne(example);
    }

    /**
     * Returns a {@link Page} of entities matching the given {@link Example}. In case no match could be found, an empty
     * {@link Page} is returned.
     *
     * @param example must not be {@literal null}.
     * @param pageable can be {@literal null}.
     * @return a {@link Page} of entities matching the given {@link Example}.
     */
    @Override
    public <S extends Product> Page<S> findAll(Example<S> example, Pageable pageable) {
        return productRepository.findAll(example, pageable);
    }

    /**
     * Returns the number of instances matching the given {@link Example}.
     *
     * @param example the {@link Example} to count instances for. Must not be {@literal null}.
     * @return the number of instances matching the {@link Example}.
     */
    @Override
    public <S extends Product> long count(Example<S> example) {
        return productRepository.count(example);
    }

    /**
     * Checks whether the data store contains elements that match the given {@link Example}.
     *
     * @param example the {@link Example} to use for the existence check. Must not be {@literal null}.
     * @return {@literal true} if the data store contains elements that match the given {@link Example}.
     */
    @Override
    public <S extends Product> boolean exists(Example<S> example) {
        return productRepository.exists(example);
    }

    /**
     * Returns entities matching the given {@link Example} applying the {@link Function queryFunction} that defines the
     * query and its result type.
     *
     * @param example must not be {@literal null}.
     * @param queryFunction the query function defining projection, sorting, and the result type
     * @return all entities matching the given {@link Example}.
     * @since 2.6
     */
    @Override
    public <S extends Product, R> R findBy(Example<S> example, Function<FluentQuery.FetchableFluentQuery<S>, R> queryFunction) {
        return productRepository.findBy(example, queryFunction);
    }
}
