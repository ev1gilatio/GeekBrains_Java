package ru.gb.springboot.service;

import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;
import ru.gb.springboot.dao.DAO;
import ru.gb.springboot.model.Product;
import ru.gb.springboot.utils.Status;

import java.util.List;

@Service
@RequiredArgsConstructor
@Transactional
public class ProductService {
    private final DAO dao;

    @Transactional(propagation = Propagation.NEVER)
    public long count() {
        System.out.println(dao.count());
        return dao.count();
    }

    public List<Product> findAllActive() {
        return dao.findAllByStatus(Status.ACTIVE);
    }

    public List<Product> findAllActiveSortedByPrice(Sort.Direction direction) {
        return dao.findAllByStatus(Status.ACTIVE,
                Sort.by(direction, "price"));
    }

    public Product findById(Long id) {
        return dao.findById(id).get();
    }

    public Product save(Product product) {
        return dao.save(product);
    }

    public void disableById(Long id) {
        dao.findById(id).ifPresent(p -> {
            p.setStatus(Status.DISABLE);
            dao.save(p);
        });
    }
}
