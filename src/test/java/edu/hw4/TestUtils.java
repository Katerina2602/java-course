package edu.hw4;

import java.util.ArrayList;
import java.util.List;

public class TestUtils {

    public static List<Animal> createTestList() {
        List<Animal> animals = new ArrayList<>();
        animals.add(new Animal("Мурка", Animal.Type.CAT, Animal.Sex.F, 4, 20, 10, false));
        animals.add(new Animal("Васька", Animal.Type.CAT, Animal.Sex.M, 5, 8, 15, false));
        animals.add(new Animal("Рекс", Animal.Type.DOG, Animal.Sex.M, 10, 109, 25, true));
        animals.add(new Animal("Александр Александрович Первый", Animal.Type.FISH, Animal.Sex.M, 6, 5, 2, true));
        animals.add(new Animal("Золотая рыбка", Animal.Type.FISH, Animal.Sex.F, 1, 2, 30, true));
        animals.add(new Animal("Паук чёрная смерть", Animal.Type.SPIDER, Animal.Sex.M, 1, 4, 5, true));
        animals.add(new Animal("Тарантул", Animal.Type.SPIDER, Animal.Sex.F, 8, 2, 3, true));
        animals.add(new Animal("Дружок", Animal.Type.DOG, Animal.Sex.M, 3, 30, 40, false));

        return animals;
    }
}
