package by.valvik.mazerunner.factory;

import by.valvik.mazerunner.algorithm.KruskalAlg;
import by.valvik.mazerunner.algorithm.MSTalg;
import by.valvik.mazerunner.algorithm.PrimAlg;
import by.valvik.mazerunner.constant.AlgorithmOption;

import static by.valvik.mazerunner.constant.AlgorithmOption.KRUSKAL;
import static by.valvik.mazerunner.constant.AlgorithmOption.PRIM;

public class AlgorithmFactory extends AbstractFactory<AlgorithmOption, MSTalg> {

    @Override
    public Factory<AlgorithmOption, MSTalg> getFactory() {

        Factory<AlgorithmOption, MSTalg> factory = Factory.of();

        factory.add(PRIM, new PrimAlg());

        factory.add(KRUSKAL, new KruskalAlg());

        return factory;

    }

}
