package com.pichincha.stock.service.impl;

import com.pichincha.stock.dao.AuditoriaBienDao;
import com.pichincha.stock.entity.AuditoriaBien;
import com.pichincha.stock.service.AuditoriaService;
import lombok.extern.apachecommons.CommonsLog;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;

@CommonsLog
@Service
public class AuditoriaServiceImpl implements AuditoriaService {

    @Autowired
    private AuditoriaBienDao auditoriaBienDao;

    @Override
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
