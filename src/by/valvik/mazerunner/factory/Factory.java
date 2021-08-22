package by.valvik.mazerunner.factory;


import by.valvik.mazerunner.factory.impl.GenericFactory;

public interface Factory<K, V> {

    void add(K k, V v);

    V get(K k);

    static <K, V> Factory<K, V> of() {

        return new GenericFactory<>();

    }

}
