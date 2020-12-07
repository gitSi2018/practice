package com.hongzhens.study.spring.myaop.api.manager;

import com.hongzhens.study.spring.myaop.api.entity.dto.InsertDTO;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Component;

/**
 * @author: HongZhenSi
 * @date: 2020/11/24
 * @modifiedBy:
 * @description:
 * @version: 1.0
 */

@Slf4j
@Component
public class UserManager {



    public String insert(InsertDTO dto){

        log.info("insert dto:{}", dto);
        return "张三";
    }
}
