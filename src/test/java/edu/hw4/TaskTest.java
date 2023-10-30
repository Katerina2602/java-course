package edu.hw4;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.HashSet;
import java.util.List;
import java.util.Map;
import java.util.Optional;
import java.util.Set;
import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.junit.jupiter.api.Assertions.assertTrue;

class TaskTest {
    private Task task = new Task();

    @Test // task 1
    void willReturnSortedListAnimalByHeight() {
        List<Animal> animals = TestUtils.createTestList();

        List<Animal> animalsSorted = new ArrayList<>();
        animalsSorted.add(new Animal("Золотая рыбка", Animal.Type.FISH, Animal.Sex.F, 1, 2, 30, true));
        animalsSorted.add(new Animal("Тарантул", Animal.Type.SPIDER, Animal.Sex.F, 8, 2, 3, true));
        animalsSorted.add(new Animal("Паук чёрная смерть", Animal.Type.SPIDER, Animal.Sex.M, 1, 4, 5, true));
        animalsSorted.add(new Animal("Александр Александрович Первый", Animal.Type.FISH, Animal.Sex.M, 6, 5, 2, true));
        animalsSorted.add(new Animal("Васька", Animal.Type.CAT, Animal.Sex.M, 5, 8, 15, false));
        animalsSorted.add(new Animal("Мурка", Animal.Type.CAT, Animal.Sex.F, 4, 20, 10, false));
        animalsSorted.add(new Animal("Дружок", Animal.Type.DOG, Animal.Sex.M, 3, 30, 40, false));
        animalsSorted.add(new Animal("Рекс", Animal.Type.DOG, Animal.Sex.M, 10, 109, 25, true));

        assertEquals(animalsSorted, task.getSortByHeight(animals));
    }

    @Test //task2
    void willReturnSortedListAnimalByWeightOfKElemets() {
        List<Animal> animals = TestUtils.createTestList();

        List<Animal> animalsSorted = new ArrayList<>();
        animalsSorted.add(new Animal("Дружок", Animal.Type.DOG, Animal.Sex.M, 3, 30, 40, false));
        animalsSorted.add(new Animal("Золотая рыбка", Animal.Type.FISH, Animal.Sex.F, 1, 2, 30, true));
        animalsSorted.add(new Animal("Рекс", Animal.Type.DOG, Animal.Sex.M, 10, 109, 25, true));
        animalsSorted.add(new Animal("Васька", Animal.Type.CAT, Animal.Sex.M, 5, 8, 15, false));

        assertEquals(animalsSorted, task.getSortByWeight(animals, 4));
    }

    @Test //task3
    void willReturnMapWhereKeyIsTheTypeValueIsQuantity() {
        List<Animal> animals = TestUtils.createTestList();
        Map<Animal.Type, Long> map = new HashMap<>();
        map.put(Animal.Type.CAT, 2L);
        map.put(Animal.Type.DOG, 2L);
        map.put(Animal.Type.FISH, 2L);
        map.put(Animal.Type.SPIDER, 2L);
        assertEquals(map, task.getCountAnimalInOpinion(animals));
    }

    @Test //task4
    void willReturnAnimalTheLongestName() {
        List<Animal> animals = TestUtils.createTestList();
        Animal animal = new Animal("Александр Александрович Первый", Animal.Type.FISH, Animal.Sex.M, 6, 5, 2, true);
        assertEquals(animal, task.getLongestName(animals).get());
    }

    @Test //task5
    void willReturnWhatAnimalsAreThereMore() {
        List<Animal> animals = TestUtils.createTestList();
        assertEquals(Animal.Sex.M, task.getSexWhoIsMore(animals));
    }

    @Test //task6
    void willReturnMapWhereKeyIsTypeValueIsAnimalIsMaxWeith() {
        List<Animal> animals = TestUtils.createTestList();
        Map<Animal.Type, Optional<Animal>> map = new HashMap<>();
        Animal animal = new Animal("Васька", Animal.Type.CAT, Animal.Sex.M, 5, 8, 15, false);
        map.put(Animal.Type.CAT, Optional.of(animal));
        animal = new Animal("Дружок", Animal.Type.DOG, Animal.Sex.M, 3, 30, 40, false);
        map.put(Animal.Type.DOG, Optional.of(animal));
        animal = new Animal("Паук чёрная смерть", Animal.Type.SPIDER, Animal.Sex.M, 1, 4, 5, true);
        map.put(Animal.Type.SPIDER, Optional.of(animal));
        animal = new Animal("Золотая рыбка", Animal.Type.FISH, Animal.Sex.F, 1, 2, 30, true);
        map.put(Animal.Type.FISH, Optional.of(animal));

        assertEquals(map, task.getHeaviestAnimalOfType(animals));
    }

    @Test //task7
    void willReturnAnimalinKPlaceOldest() {
        Animal animal = new Animal("Александр Александрович Первый", Animal.Type.FISH, Animal.Sex.M, 6, 5, 2, true);
        List<Animal> animals = TestUtils.createTestList();
        assertEquals(animal, task.getOldestAnimalKPosition(animals, 2).get());
    }

    @Test //task8
    void willReturnHeaviestAnimalAndBelowKInHeight() {
        Animal animal = new Animal("Золотая рыбка", Animal.Type.FISH, Animal.Sex.F, 1, 2, 30, true);
        List<Animal> animals = TestUtils.createTestList();
        assertEquals(animal, task.getHeaviestAnimalAndBelowK(animals, 10).get());
    }

    @Test //task9
    void willReturnSumAllAnimalPaws() {
        List<Animal> animals = TestUtils.createTestList();
        assertEquals(32, task.getSumAllPaws(animals));
    }

    @Test //task10
    void willReturnListAnimalWhichAgeIsNotEqualCountType() {
        List<Animal> animals = TestUtils.createTestList();

        List<Animal> animalsList = new ArrayList<>();
        animalsList.add(new Animal("Васька", Animal.Type.CAT, Animal.Sex.M, 5, 8, 15, false));
        animalsList.add(new Animal("Рекс", Animal.Type.DOG, Animal.Sex.M, 10, 109, 25, true));
        animalsList.add(new Animal("Александр Александрович Первый", Animal.Type.FISH, Animal.Sex.M, 6, 5, 2, true));
        animalsList.add(new Animal("Золотая рыбка", Animal.Type.FISH, Animal.Sex.F, 1, 2, 30, true));
        animalsList.add(new Animal("Паук чёрная смерть", Animal.Type.SPIDER, Animal.Sex.M, 1, 4, 5, true));
        animalsList.add(new Animal("Дружок", Animal.Type.DOG, Animal.Sex.M, 3, 30, 40, false));


        assertEquals(animalsList, task.getAnimalWhenAgeIsNotCountPaws(animals));
    }

    @Test //task11
    void willReturnListAnimalsThatCanBiteAndHeightMoreOneHundred() {
        List<Animal> animalsList = new ArrayList<>();
        animalsList.add(new Animal("Рекс", Animal.Type.DOG, Animal.Sex.M, 10, 109, 25, true));
        List<Animal> animals = TestUtils.createTestList();
        assertEquals(animalsList, task.getAnimalThatBiteAndHeightOverHundred(animals));

    }

    @Test //task12
    void willReturnCountAnimalWhichWeightMoreHeight() {
        List<Animal> animals = TestUtils.createTestList();
        assertEquals(5, task.getCountAnimalWhenWeightMoreHeight(animals));
    }

    @Test//task 13
    void willReturnListAnimalWhoseNamesConsistOfMoreThatTwoWords() {
        List<Animal> animals = TestUtils.createTestList();
        List<Animal> animalsList = new ArrayList<>();
        animalsList.add(new Animal("Александр Александрович Первый", Animal.Type.FISH, Animal.Sex.M, 6, 5, 2, true));
        animalsList.add(new Animal("Паук чёрная смерть", Animal.Type.SPIDER, Animal.Sex.M, 1, 4, 5, true));
        assertEquals(animalsList, task.getAnimalNameWithMoreTwoWords(animals));
    }

    @Test //task14
    void willReturnTrueOrFalseItThereDogTallMoreK() {
        List<Animal> animals = TestUtils.createTestList();
        assertTrue(task.getHasDogHeightMoreHundred(animals, 10));

    }

    @Test //task15
    void willReturnMapWhereKeyIsTypeValueIsSumWeight() {
        List<Animal> animals = TestUtils.createTestList();
        Map<Animal.Type, Long> map = new HashMap<>();
        map.put(Animal.Type.CAT, 15L);
        map.put(Animal.Type.DOG, 25L);
        map.put(Animal.Type.FISH, 2L);
        map.put(Animal.Type.SPIDER, 3L);

        assertEquals(map, task.getSumWeightOfAnimalBtType(animals, 5, 10));

    }

    @Test //task16
    void willReturnListAnimalSortTypeAndSexAndName() {
        List<Animal> animals = TestUtils.createTestList();

        List<Animal> animalsSort = new ArrayList<>();
        animalsSort.add(new Animal("Васька", Animal.Type.CAT, Animal.Sex.M, 5, 8, 15, false));
        animalsSort.add(new Animal("Мурка", Animal.Type.CAT, Animal.Sex.F, 4, 20, 10, false));
        animalsSort.add(new Animal("Дружок", Animal.Type.DOG, Animal.Sex.M, 3, 30, 40, false));
        animalsSort.add(new Animal("Рекс", Animal.Type.DOG, Animal.Sex.M, 10, 109, 25, true));
        animalsSort.add(new Animal("Александр Александрович Первый", Animal.Type.FISH, Animal.Sex.M, 6, 5, 2, true));
        animalsSort.add(new Animal("Золотая рыбка", Animal.Type.FISH, Animal.Sex.F, 1, 2, 30, true));
        animalsSort.add(new Animal("Паук чёрная смерть", Animal.Type.SPIDER, Animal.Sex.M, 1, 4, 5, true));
        animalsSort.add(new Animal("Тарантул", Animal.Type.SPIDER, Animal.Sex.F, 8, 2, 3, true));

        assertEquals(animalsSort, task.getSortedListAnimal(animals));
    }

    @Test //task17
    void willReturnFalseOrTrueIfSpidersBiteMoreOftenThanDogs() {
        List<Animal> animals = TestUtils.createTestList();
        assertTrue(task.getSpiderBitesMoreThatDog(animals));
    }

    @Test //task18
    void willReturnTheHeaviestFish() {
        List<Animal> animalsOne = TestUtils.createTestList();
        List<Animal> animalsTwo = new ArrayList<>();

        animalsTwo.add(new Animal("Cомик", Animal.Type.FISH, Animal.Sex.F, 4, 20, 10, false));
        animalsTwo.add(new Animal("Немо", Animal.Type.FISH, Animal.Sex.M, 2, 8, 5, false));
        List<List<Animal>> list = List.of(animalsOne, animalsTwo);

        Animal animal = new Animal("Золотая рыбка", Animal.Type.FISH, Animal.Sex.F, 1, 2, 30, true);
        assertEquals(animal, task.getHeaviestFish(list).get());
    }

    @Test //task 19
    void getReturnMapWhereKeyIsNameValueIsListError() {
        List<Animal> animals = new ArrayList<>();
        animals.add(new Animal("Мурка", Animal.Type.SPIDER, Animal.Sex.F, -4, 20, 10, true));
        animals.add(new Animal("Васька", Animal.Type.SPIDER, Animal.Sex.M, 2, 8, 5, true));
        animals.add(new Animal("Рекс", Animal.Type.DOG, Animal.Sex.M, -3, -9, 25, false));
        animals.add(new Animal("Дружок", Animal.Type.DOG, Animal.Sex.M, 3, 9, 25, true));

        Map<String, Set<ValidationError>> map = new HashMap<>();
        Set<ValidationError> set1 = new HashSet<>();
        Set<ValidationError> set2 = new HashSet<>();

        set1.add(new ValidationError("Отрицательное значение не может быть", "age"));
        map.put("Мурка", set1);
        set2.add(new ValidationError("Отрицательное значение не может быть", "height"));
        set2.add(new ValidationError("Отрицательное значение не может быть", "age"));
        map.put("Рекс", set2);

        assertEquals(map, task.getlistAnimalsErorrs(animals));
    }

    @Test //task 20
    void getReturnMapWhereKeyIsNameValueIsStringError() {

        List<Animal> animals = new ArrayList<>();
        animals.add(new Animal("Мурка", Animal.Type.SPIDER, Animal.Sex.F, -4, 20, 10, true));
        animals.add(new Animal("Васька", Animal.Type.SPIDER, Animal.Sex.M, 2, 8, 5, true));
        animals.add(new Animal("Рекс", Animal.Type.DOG, Animal.Sex.M, -3, -9, 25, false));
        animals.add(new Animal("Дружок", Animal.Type.DOG, Animal.Sex.M, 3, 9, 25, true));

        Map<String, String> map = new HashMap<>();

        String set1 = "ValidationError[message=Отрицательное значение не может быть, fieldName=age]";
        map.put("Мурка", set1);
        String set2 = "ValidationError[message=Отрицательное значение не может быть, fieldName=height],";
        set2 = set2 + "ValidationError[message=Отрицательное значение не может быть, fieldName=age]";
        map.put("Рекс", set2);

        assertEquals(map, task.getlistAnimalsErorrs2(animals));
    }

    @Test //task2 task 7
    void willReturnErrorIfKMoreSizeListOrLessZero() {
        List<Animal> animals = TestUtils.createTestList();
        Task task = new Task();
        assertThrows(IllegalArgumentException.class, () -> task.getSortByWeight(animals, -5));//task 2
        assertThrows(IllegalArgumentException.class, () -> task.getOldestAnimalKPosition(animals, 10));//task 7
        assertThrows(IllegalArgumentException.class, () -> task.getSumWeightOfAnimalBtType(animals, -5, 10));//task 15
        assertThrows(IllegalArgumentException.class, () -> task.getHasDogHeightMoreHundred(animals, -10));//task 14
        assertThrows(IllegalArgumentException.class, () -> task.getHeaviestAnimalAndBelowK(animals, -10));//task 8
    }

}
