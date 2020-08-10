package com.si.practice.dbpractice.client.driver.request;


import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.io.Serializable;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class DriverWorkInfoRequest implements Serializable {


    private Long id;

    private Integer workStatus;
}
