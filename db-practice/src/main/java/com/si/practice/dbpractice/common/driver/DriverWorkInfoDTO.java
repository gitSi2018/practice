package com.si.practice.dbpractice.common.driver;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.Date;


@Data
@AllArgsConstructor
@NoArgsConstructor
public class DriverWorkInfoDTO {

    private Long id;

    private Integer workStatus;

    private Integer version;

    private Date gmtCreate;

    private Date gmtUpdate;
}
