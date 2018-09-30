package org.hush.api;

import java.util.List;
import java.util.Map;

public interface IService {

    public int delete(Map<String, Object> map);

    public int add(Item item);

    public int update(int id, Item item);

    public Item select(Item item);

    public List<Item> selectAll();

}
