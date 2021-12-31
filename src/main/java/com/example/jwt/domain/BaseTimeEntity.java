package com.example.jwt.domain;

import org.springframework.data.annotation.CreatedDate;
import org.springframework.data.annotation.LastModifiedDate;
import org.springframework.data.jpa.domain.support.AuditingEntityListener;
import org.springframework.format.annotation.DateTimeFormat;

import javax.persistence.EntityListeners;
import javax.persistence.MappedSuperclass;
import java.util.Date;

@MappedSuperclass
@EntityListeners(AuditingEntityListener.class)
public class BaseTimeEntity {

    @CreatedDate
    @DateTimeFormat(pattern = "yyyy-mm-dd hh:mm:ss.s")
    private Date createdDate;

    @LastModifiedDate
    @DateTimeFormat(pattern = "yyyy-mm-dd hh:mm:ss.s")
    private Date updatedDate;

}
