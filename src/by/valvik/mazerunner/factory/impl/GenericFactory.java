package by.valvik.mazerunner.factory.impl;


import by.valvik.mazerunner.factory.Factory;

import java.util.HashMap;
import java.util.Map;

public class GenericFactory<K, V> implements Factory<K, V> {

    private final Map<K, V> map;

    public GenericFactory() {

        map = new HashMap<>();

    }

    @Override
    public void add(K k, V v) {

        map.put(k, v);

    }

    @Override
    public V get(K k) {

        return map.get(k);

    }

}
