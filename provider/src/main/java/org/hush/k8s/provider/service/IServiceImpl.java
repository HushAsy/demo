package org.hush.k8s.provider.service;

import com.alibaba.dubbo.config.annotation.Service;
import com.baomidou.mybatisplus.core.conditions.Wrapper;
import org.hush.api.IService;
import org.hush.api.Item;
import org.hush.k8s.provider.dao.mapper.ItemMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.List;
import java.util.Map;

/**
 * @description:
 * @author: hewater
 * @create: 2018-09-28 14:33
 **/
@Component
@Service( interfaceClass = IService.class,
          version = "1.0.0",
          group = "hush")
public class IServiceImpl implements IService {

    @Autowired
    private ItemMapper itemMapper;

    public int delete(Map<String, Object> map) {
        return itemMapper.deleteByMap(map);
    }

    public int add(Item item) {
        return itemMapper.insert(item);
    }

    public int update(final int id, final Item item) {
        return itemMapper.update(item, new Wrapper<Item>() {
            @Override
            public Item getEntity() {
                Item _item = new Item();
                _item.setId(id);
                return _item;
            }

            public String getSqlSegment() {
                return null;
            }
        });
    }

    public Item select(Item item) {
        return itemMapper.selectById(item.getId());
    }

    public List<Item> selectAll() {
        return itemMapper.selectList(null);
    }
}
