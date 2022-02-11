package ru.gb.hibernate;

import lombok.RequiredArgsConstructor;
import org.hibernate.SessionFactory;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;
import ru.gb.ProductDao;
import ru.gb.entity.Product;

import java.util.Collections;

@Repository
@RequiredArgsConstructor
public class HibernateDAO implements ProductDao {
    private final SessionFactory sessionFactory;

    @Override
    @Transactional(readOnly = true)
    public Iterable<Product> findAll() {
        return Collections.unmodifiableList(sessionFactory.
                getCurrentSession().
                createQuery("from Product p")
                .list());
    }

    @Override
    @Transactional(readOnly = true)
    public Product findById(long id) {
        return (Product) sessionFactory.getCurrentSession()
                .getNamedQuery("Product.findById")
                .setParameter("id", id)
                .uniqueResult();
    }

    @Override
    @Transactional(readOnly = true)
    public String findTitleById(long id) {
        return (String) sessionFactory.getCurrentSession()
                .getNamedQuery("Product.findTitleById")
                .setParameter("id", id)
                .uniqueResult();
    }
}
