package com.si.practice.dbpractice.infrature.mysql.entity;


import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;
import java.util.Date;

@Data
@Table(name = "driver_work_info")
@Entity
@AllArgsConstructor
@NoArgsConstructor
public class DriverWorkInfoEntity {

    @Id
    private Long id;

    @Column(name = "work_status")
    private Integer workStatus;

    @Column(name = "version")
    private Integer version;

    @Column(name = "gmt_create")
    private Date gmtCreate;

    @Column(name = "gmt_update")
    private Date gmtUpdate;
}
