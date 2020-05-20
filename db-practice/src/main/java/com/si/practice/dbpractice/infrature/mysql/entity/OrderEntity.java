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
@Table(name = "demand_order")
@Entity
@AllArgsConstructor
@NoArgsConstructor
public class OrderEntity {

    @Id
    private Long id;

    @Column(name = "passenger")
    private String passenger;

    @Column(name = "prod_id")
    private Long prodId;

    @Column(name = "status")
    private Integer status;

    @Column(name = "driver_id")
    private Long driverId;

    @Column(name = "version")
    private Integer version;

    @Column(name = "deleted")
    private Boolean deleted;

    @Column(name = "created")
    private Date created;

    @Column(name = "updated")
    private Date updated;


}
