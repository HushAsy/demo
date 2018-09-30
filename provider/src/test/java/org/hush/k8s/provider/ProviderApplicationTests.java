package org.hush.k8s.provider;

import org.hush.api.Item;
import org.hush.k8s.provider.dao.mapper.ItemMapper;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mybatis.spring.annotation.MapperScan;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

@RunWith(SpringRunner.class)
@SpringBootTest
@MapperScan("org.hush.k8s.provider.dao.mapper")
public class ProviderApplicationTests {

    @Autowired
    private ItemMapper itemMapper;

    @Test
    public void contextLoads() {
        Item item = new Item();
        item.setId(123);
        item.setName("mike");
        itemMapper.insert(item);
    }

}
