package com.lesson.maven.jpa;

import jakarta.persistence.EntityManager;
import jakarta.persistence.TypedQuery;
import jakarta.persistence.criteria.CriteriaBuilder;
import jakarta.persistence.criteria.CriteriaQuery;
import jakarta.persistence.criteria.Predicate;
import jakarta.persistence.criteria.Root;

import java.util.List;

public class ProductDao {
    private EntityManager entityManager;

    public ProductDao(EntityManager entityManager) {
        this.entityManager = entityManager;
    }

    public List<Product> allProducts(){
        TypedQuery<Product> query = entityManager.createNamedQuery("get_all", Product.class);
        List<Product> products = query.getResultList();
        return products;
    }

    public Product productByTitle(String title){
        TypedQuery<Product> query = entityManager.createNamedQuery("get_by_title", Product.class);
        query.setParameter("title_param", title);
        Product product = query.getSingleResult();

        /* Criteria API*/
        //"SELECT ...."
        CriteriaBuilder criteriaBuilder = entityManager.getCriteriaBuilder();
        CriteriaQuery<Product> criteriaQuery = criteriaBuilder.createQuery(Product.class);
        //FROM Product
        Root<Product> root = criteriaQuery.from(Product.class);
        //WHERE
        //import jakarta.persistence.criteria.Predicate;
        Predicate condition = criteriaBuilder.equal(root.get("title"), title);

        criteriaQuery.select(root).where(condition);

        TypedQuery<Product> queryCriteria = entityManager.createQuery(criteriaQuery);
        Product productCriteria = queryCriteria.getSingleResult();

        return product;
    }
}
