package com.si.practice.dbpractice.app.driver;

import com.si.practice.dbpractice.client.driver.api.QueryDriverWorkInfoService;
import com.si.practice.dbpractice.common.driver.DriverWorkInfoDTO;
import com.si.practice.dbpractice.domian.driver.DriverWorkInfoManager;

import javax.annotation.Resource;
import java.util.List;


public class QueryDriverWorkInfoServiceImpl implements QueryDriverWorkInfoService {

    @Resource
    private DriverWorkInfoManager driverWorkInfoManager;

    @Override
    public DriverWorkInfoDTO getLastOne() {

        return driverWorkInfoManager.getLastOne();
    }



    @Override
    public List<DriverWorkInfoDTO> listAll() {
        return null;
    }

    @Override
    public DriverWorkInfoDTO queryDriverWorkInfoById(Long driverId) {

        return driverWorkInfoManager.getDriverWorkInfoById(driverId);
    }
}
