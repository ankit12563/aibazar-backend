package com.ankitregmi.ecommerce.repository;

import com.ankitregmi.ecommerce.entity.Product;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.repository.query.Param;
import org.springframework.web.bind.annotation.CrossOrigin;


//@CrossOrigin("http://localhost:4200")
public interface ProductRepository extends JpaRepository<Product, Long> {

    Page<Product> findByCategoryId(@Param("id") long id, Pageable pageable);
    //This method retrieves a paginated list of Product entities filtered by a given category ID using Spring Data JPA.
    //The method is annotated with @Param to specify the name of the parameter to be used in the query.
    //The method returns a Page object, which contains the list of Product entities and the pagination information.
    //The method is annotated with @CrossOrigin to allow cross-origin requests from the frontend.

    Page<Product> findByNameContaining(@Param("name") String name, Pageable pageable);

}
