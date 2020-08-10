package com.si.practice.dbpractice.client.driver.api;

import com.si.practice.dbpractice.common.driver.DriverWorkInfoDTO;

import java.util.List;

public interface QueryDriverWorkInfoService {

    DriverWorkInfoDTO getLastOne();

    List<DriverWorkInfoDTO> listAll();

    DriverWorkInfoDTO queryDriverWorkInfoById(Long driverId);
}
