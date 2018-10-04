package org.hush.utils;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

/**
 * @description:
 * @author: hewater
 * @create: 2018-10-04 12:57
 **/
public class StringUtils {

    public static String getConfigZkAddress(String zk1, String zk2, String zk3){
        List<String> stringList = new ArrayList<String>(){
            @Override
            public String toString() {
                Iterator<String> it = iterator();
                if (! it.hasNext())
                    return "[]";

                StringBuilder sb = new StringBuilder();
                for (;;) {
                    String e = it.next();
                    sb.append(e);
                    if (! it.hasNext())
                        return sb.toString();
                    sb.append(',');
                }
            }
        };
        if (null != zk1){
            stringList.add(zk1);
        }
        if (null != zk2){
            stringList.add(zk2);
        }
        if (null != zk3){
            stringList.add(zk3);
        }
        return stringList.toString();
    }

}
