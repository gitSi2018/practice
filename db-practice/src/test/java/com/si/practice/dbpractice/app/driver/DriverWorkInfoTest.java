package com.si.practice.dbpractice.app.driver;


import com.si.practice.dbpractice.client.driver.api.CreateDriverWorkInfoService;
import com.si.practice.dbpractice.client.driver.api.UpdateDriverWorkInfoService;
import com.si.practice.dbpractice.client.driver.request.DriverWorkInfoRequest;
import lombok.extern.slf4j.Slf4j;
import org.junit.Assert;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import javax.annotation.Resource;

@SpringBootTest
@RunWith(SpringRunner.class)
@Slf4j
public class DriverWorkInfoTest {


    @Resource
    private CreateDriverWorkInfoService createService;

    @Resource
    private UpdateDriverWorkInfoService updateService;

    @Test
    public void createDriverWorkInfoTest(){


        DriverWorkInfoRequest request = new DriverWorkInfoRequest(null, 0);
        Long id = createService.create(request);
        Assert.assertTrue(id != null && id > 0L);
    }

    @Test
    public void updateWithPrimaryKeyTest(){

    }
}
