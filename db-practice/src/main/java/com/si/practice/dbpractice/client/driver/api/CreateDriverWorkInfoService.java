package com.si.practice.dbpractice.client.driver.api;

import com.si.practice.dbpractice.client.driver.request.DriverWorkInfoRequest;

public interface CreateDriverWorkInfoService {


    Long create(DriverWorkInfoRequest driverWorkInfoRequest);
}
