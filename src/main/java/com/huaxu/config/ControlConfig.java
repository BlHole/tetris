package com.huaxu.config;

import org.dom4j.Element;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;

/**
 * <p>project: tetris</p>
 * <p>description: </p>
 * <p>create: 2019/12/27 15:56</p>
 * <p>author：huaxu</p>
 */
public class ControlConfig implements Serializable {

    private static final long serialVersionUID = -2140410048442737891L;

    private Map<String, Integer> collection;


    public ControlConfig(Element control) {
        this.collection = new ConcurrentHashMap<String, Integer>();
        List<Element> params = control.elements("param");
        for (Element e : params) {
            collection.put(e.attributeValue("name"), Integer.parseInt(e.attributeValue("code")));
        }
    }

    public Map<String, Integer> getCollection() {
        return collection;
    }
}