package com.si.practice.dbpractice.common.order;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.io.Serializable;
import java.util.Date;


@Data
@AllArgsConstructor
@NoArgsConstructor
public class OrderDTO implements Serializable {


    private Long id;

    private String passenger;

    private Long prodId;

    private Integer status;

    private Long driverId;

    private Integer version;

    private Boolean deleted;

    private Date created;

    private Date updated;
}
