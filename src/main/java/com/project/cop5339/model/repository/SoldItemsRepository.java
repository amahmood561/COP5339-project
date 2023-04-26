package com.project.cop5339.model.repository;


import com.project.cop5339.model.SoldItems;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface SoldItemsRepository extends JpaRepository<SoldItems, Long> {
}
