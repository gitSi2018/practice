package com.si.practice.dbpractice.app.driver;

import com.si.practice.dbpractice.client.driver.api.UpdateDriverWorkInfoService;
import com.si.practice.dbpractice.client.driver.request.DriverWorkInfoRequest;
import com.si.practice.dbpractice.common.driver.DriverWorkInfoDTO;
import com.si.practice.dbpractice.domian.driver.DriverWorkInfoManager;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.BeanUtils;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;


@Slf4j
@Service
public class UpdateDriverWorkInfoServiceImpl implements UpdateDriverWorkInfoService {

    @Resource
    private DriverWorkInfoManager driverWorkInfoManager;

    @Override
    public boolean update(DriverWorkInfoRequest request) {

        DriverWorkInfoDTO workInfoDTO =  new DriverWorkInfoDTO();
        BeanUtils.copyProperties(request, workInfoDTO);
        return driverWorkInfoManager.updateWorkInfoWithPrimaryId(workInfoDTO);
    }
}
