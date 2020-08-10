package com.si.practice.dbpractice.domian.driver;


import com.si.practice.dbpractice.common.driver.DriverWorkInfoDTO;
import com.si.practice.dbpractice.infrature.mysql.entity.DriverWorkInfoEntity;
import com.si.practice.dbpractice.infrature.mysql.mapper.DriverWorkInfoBaseMapper;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.BeanUtils;
import org.springframework.stereotype.Component;
import org.springframework.util.CollectionUtils;

import javax.annotation.Resource;
import java.util.Comparator;
import java.util.Date;
import java.util.List;

@Slf4j
@Component
public class DriverWorkInfoManager {

    @Resource
    private DriverWorkInfoBaseMapper driverWorkInfoBaseMapper;

    public Long addDriverWorkInfo(DriverWorkInfoDTO infoDTO){

        log.info("into DriverWorkInfoManager addDriverWorkInfo, infoDTO:{}", infoDTO);
        DriverWorkInfoEntity entity = convert(infoDTO);
        Date now = new Date();
        entity.setGmtCreate(now);
        entity.setGmtUpdate(now);
        driverWorkInfoBaseMapper.insert(entity);
        return entity.getId();
    }

    public boolean updateWorkInfoWithPrimaryId(DriverWorkInfoDTO infoDTO){

        log.info("into DriverWorkInfoManager updateWorkInfoWithPrimaryId, infoDTO:{}", infoDTO);
        DriverWorkInfoEntity entity = convert(infoDTO);
        return driverWorkInfoBaseMapper.updateByPrimaryKeySelective(entity) > 0;

    }

    private DriverWorkInfoEntity getDriverById(Long driverId){


        DriverWorkInfoEntity entity = driverWorkInfoBaseMapper.selectByPrimaryKey(driverId);
        return entity;
    }

    public DriverWorkInfoDTO getDriverWorkInfoById(Long driverId){

        return convert(getDriverById(driverId));
    }

    public DriverWorkInfoDTO getLastOne(){

        List<DriverWorkInfoEntity> entitys = driverWorkInfoBaseMapper.selectAll();
        if (CollectionUtils.isEmpty(entitys)){
            return null;
        }
        DriverWorkInfoEntity entity =
                entitys.stream().max(Comparator.comparingLong(DriverWorkInfoEntity::getId)).orElse(null);
        return convert(entity);
    }

    public DriverWorkInfoDTO convert(DriverWorkInfoEntity entity){

        if (entity == null){
            return null;
        }
        DriverWorkInfoDTO dto = new DriverWorkInfoDTO();
        BeanUtils.copyProperties(entity, dto);
        return dto;
    }

    public DriverWorkInfoEntity convert(DriverWorkInfoDTO dto){

        if (dto == null){
            return null;
        }
        DriverWorkInfoEntity entity = new DriverWorkInfoEntity();
        BeanUtils.copyProperties(dto, entity);
        return entity;
    }
}
