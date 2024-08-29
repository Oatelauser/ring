package com.test.sensitive;

import com.test.sensitive.model.BaseResponse;
import com.test.sensitive.model.People;
import com.test.sensitive.model.PeopleMap;
import com.test.sensitive.model.PubResponse;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

import java.util.List;

/**
 * 实体类对象脱敏单元测试
 *
 * @author Emily
 * @since :  Created in 2023/5/31 3:34 PM
 */
public class DeSensitiveUtilsTest {
    @Test
    public void simpleFieldTest() {
        People people = new People();
        people.setUsername("孙少平");
        people.setPassword("ssp");
        People p = DeSensitiveUtils.acquireElseGet(people);
        Assertions.assertEquals(p, people);
        Assertions.assertEquals(p.getUsername(), "--隐藏--");
    }

    @Test
    public void flexFieldTest() {
        People people = new People();
        people.setKey("email");
        people.setValue("1563919868@qq.com");
        People p = DeSensitiveUtils.acquireElseGet(people);
        Assertions.assertEquals(p, people);
        Assertions.assertEquals(p.getKey(), "email");
        Assertions.assertEquals(p.getValue(), "1***8@qq.com");

        people.setKey("phone");
        people.setValue("1563919868");
        p = DeSensitiveUtils.acquireElseGet(people);
        Assertions.assertEquals(p.getKey(), "phone");
        Assertions.assertEquals(people.getValue(), "15****9868");
    }

    @Test
    public void jsonNullFieldTest1() {
        People people = new People();
        people.setKey("email");
        people.setValue("1563919868@qq.com");
        people.setStr("测试null");
        People s = DeSensitiveUtils.acquireElseGet(people);
        Assertions.assertNull(s.getStr());
        Assertions.assertEquals(s.getAge(), 0);
        Assertions.assertEquals(s.getB(), (byte) 0);
        Assertions.assertEquals(s.getS(), (short) 0);
        Assertions.assertEquals(s.getL(), 0L);
    }

    @Test
    public void testFieldMap() {
        PeopleMap peopleMap = new PeopleMap();
        peopleMap.getParams().put("password", "12345");
        peopleMap.getParams().put("username", "田晓霞");
        peopleMap.getParams().put("phone", "15645562587");
        PeopleMap p = DeSensitiveUtils.acquireElseGet(peopleMap);
        System.out.println(p);
    }

    @Test
    public void test11() {
        PubResponse response = new PubResponse();
        response.password = "32433";
        response.username = "条消息";
        response.email = "1393619859@qq.com";
        response.idCard = "321455188625645686";
        response.bankCard = "325648956125656666";
        response.phone = "18254452658";
        response.mobile = "1234567";
        PubResponse.Job job = new PubResponse.Job();
        job.email = "1393619859@qq.com";
        job.work = "呵呵哈哈哈";
        response.job = job;
        response.jobs = new PubResponse.Job[]{job};
        response.jobList = List.of(job);
        BaseResponse<PubResponse> r = BaseResponse.<PubResponse>newBuilder().withData(response).build();
        BaseResponse<PubResponse> response1 = DeSensitiveUtils.acquireElseGet(r);
        Assertions.assertEquals(response1.getData().email, "1393619859@qq.com");
        BaseResponse<PubResponse> response2 = DeSensitiveUtils.acquireElseGet(r, BaseResponse.class);
        Assertions.assertEquals(response2.getData().email, "1***9@qq.com");
    }
}
