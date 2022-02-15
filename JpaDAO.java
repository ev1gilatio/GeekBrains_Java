package ru.gb.springboot.dao;

import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;
import ru.gb.springboot.model.Product;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.TypedQuery;

@Repository
@Transactional
public class JpaDAO {

    @PersistenceContext
    private EntityManager entityManager;

    public Iterable<Product> findAll() {
        return entityManager.createQuery("select p from Product p")
                .getResultList();
    }

    public Product findById(Long id) {
        TypedQuery<Product> query =
                entityManager.createNamedQuery("Product.findById", Product.class);
        query.setParameter("id", id);

        return query.getSingleResult();
    }

    public void save(Product product) {
        if (product.getId() == null) {
            entityManager.persist(product);
        } else {
            entityManager.merge(product);
        }
    }

    public void deleteById(Long id) {
        Product product = findById(id);
        entityManager.remove(product);
    }
}
