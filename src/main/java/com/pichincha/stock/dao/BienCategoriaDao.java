package com.pichincha.stock.dao;

import com.pichincha.stock.entity.BienCategoria;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface BienCategoriaDao extends JpaRepository<BienCategoria, Integer> {
}
