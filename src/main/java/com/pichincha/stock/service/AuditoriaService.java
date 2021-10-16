package com.pichincha.stock.service;

import com.pichincha.stock.dao.AuditoriaBienDao;
import com.pichincha.stock.entity.AuditoriaBien;
import lombok.RequiredArgsConstructor;
import lombok.extern.apachecommons.CommonsLog;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;

@Service
@RequiredArgsConstructor
@CommonsLog
public class AuditoriaService {

    private final AuditoriaBienDao auditoriaBienDao;

    public void registerBienAudit(Integer id, String preData, String postData) {
        var audit = new AuditoriaBien();
        try {
            audit.setIdBien(id);
            audit.setPreData(preData);
            audit.setPostData(postData);
            audit.setFecha(LocalDateTime.now());

            auditoriaBienDao.save(audit);
        } catch (Exception e) {
            log.error("Error transform object to json");
        }
    }
}
