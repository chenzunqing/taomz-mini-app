package com.taomz.mini.apps.web.controller;

import com.taomz.mini.apps.service.rest.RestClient;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

/**
 * @author nanmu@taomz.com
 * @version V1.0
 * @title: LiveApplicationTest
 * @Package: com.taomz.live.portal.web.controller
 * @Description:
 * @date 2020/7/11 11:13
 **/
@RunWith(SpringRunner.class)
@SpringBootTest
public class MiniAppsApplicationTest {

    @Autowired
    private RestClient restClient;

    @Test
    public void restRequestTest() {
        
    }
}
