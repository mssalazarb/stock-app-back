package com.pichincha.stock.dao;

import com.pichincha.stock.entity.AuditoriaBien;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

/**
 * @author mssalazarb
 * @version 1
 */
@Repository
public interface AuditoriaBienDao extends JpaRepository<AuditoriaBien, Integer> {
}
