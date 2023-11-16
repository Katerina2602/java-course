package edu.hw4;

import java.util.Comparator;
import java.util.List;
import java.util.Map;
import java.util.Optional;
import java.util.Set;
import java.util.stream.Collectors;
import static java.util.stream.Collectors.joining;

public class Task {
    public static final Integer ONE_HUNDRED = 100;

    //1  Отсортировать животных по росту от самого маленького к самому большому -> List<Animal>
    public List<Animal> getSortByHeight(List<Animal> animal) {
        return animal.stream()
            .sorted(Comparator.comparing(Animal::height))
            .toList();
    }

    //2 Отсортировать животных по весу от самого тяжелого к самому легкому, выбрать k первых -> List<Animal>
    public List<Animal> getSortByWeight(List<Animal> animal, int k) {
        if (k >= animal.size() || k < 0) {
            throw new IllegalArgumentException("K не входит в размеры списка");
        }
        return animal.stream()
            .sorted(Comparator.comparing(Animal::weight).reversed())
            .limit(k)
            .toList();
    }

    //3 Сколько животных каждого вида -> Map<Animal.Type, Integer>
    public Map<Animal.Type, Long> getCountAnimalInOpinion(List<Animal> animal) {
        return animal.stream()
            .collect(
                Collectors.groupingBy(Animal::type, Collectors.counting()));
    }

    //4 У какого животного самое длинное имя -> Animal
    public Optional<Animal> getLongestName(List<Animal> animal) {
        return animal.stream()
            .max(Comparator.comparing(animal1 -> animal1.name().length()));
    }

    //5 Каких животных больше: самцов или самок -> Sex
    public Animal.Sex getSexWhoIsMore(List<Animal> animal) {
        return animal.stream()
            .collect(Collectors.groupingBy(Animal::sex, Collectors.counting()))
            .entrySet()
            .stream()
            .max(Map.Entry.comparingByValue())
            .get()
            .getKey();
    }

    //6 Самое тяжелое животное каждого вида -> Map<Animal.Type, Animal>
    public Map<Animal.Type, Optional<Animal>> getHeaviestAnimalOfType(List<Animal> animal) {
        return animal.stream()
            .collect(Collectors.groupingBy(Animal::type, Collectors.maxBy(Comparator.comparingInt(Animal::weight))));

    }

    //7 K-е самое старое животное -> Animal
    public Optional<Animal> getOldestAnimalKPosition(List<Animal> animal, int k) {
        if (k >= animal.size() || k < 0) {
            throw new IllegalArgumentException("Значение K не входит в размеры списка");
        }
        return animal.stream()
            .sorted(Comparator.comparing(Animal::age).reversed())
            .skip(k).findFirst();
    }

    //8 Самое тяжелое животное среди животных ниже k см -> Optional<Animal>
    public Optional<Animal> getHeaviestAnimalAndBelowK(List<Animal> animal, int k) {
        if (k < 0) {
            throw new IllegalArgumentException("K не может быть отрицательным");
        }
        return animal.stream()
            .filter(p -> (p.height() < k)).max(Comparator.comparing(Animal::weight));
    }

    //9 Сколько в сумме лап у животных в списке -> Integer
    public Integer getSumAllPaws(List<Animal> animal) {
        return animal.stream().mapToInt(Animal::paws).sum();
    }

    //10 Список животных, возраст у которых не совпадает с количеством лап -> List<Animal>
    public List<Animal> getAnimalWhenAgeIsNotCountPaws(List<Animal> animal) {
        return animal.stream()
            .filter(p -> (p.age() != p.paws())).toList();
    }

    //11 Список животных, которые могут укусить (bites == true) и рост которых превышает 100 см -> List<Animal>
    public List<Animal> getAnimalThatBiteAndHeightOverHundred(List<Animal> animal) {
        return animal.stream()
            .filter(p -> (p.height() > ONE_HUNDRED) && p.bites()).toList();
    }

    //12 Сколько в списке животных, вес которых превышает рост -> Integer
    public Long getCountAnimalWhenWeightMoreHeight(List<Animal> animals) {
        return animals.stream()
            .filter(p -> (p.weight() > p.height())).count();

    }

    //13 Список животных, имена которых состоят из более чем двух слов -> List<Animal>
    public List<Animal> getAnimalNameWithMoreTwoWords(List<Animal> animals) {
        return animals.stream()
            .filter(animal -> animal.name().split("\\s+").length > 2)
            .toList();
    }

    //14 Есть ли в списке собака ростом более k см -> Boolean
    public boolean getHasDogHeightMoreHundred(List<Animal> animals, int k) {
        if (k < 0) {
            throw new IllegalArgumentException("K не может быть отрицательными");
        }
        return animals.stream()
            .filter(animal -> animal.type() == Animal.Type.DOG)
            .anyMatch(animal -> animal.height() > k);
    }

    //15 Найти суммарный вес животных каждого вида, которым от k до l лет -> Map<Animal.Type, Integer>
    public Map<Animal.Type, Long> getSumWeightOfAnimalBtType(List<Animal> animals, int k, int l) {
        if (k <= 0 || l <= 0) {
            throw new IllegalArgumentException("K и L не могут быть отрицательными");
        }
        return animals.stream()
            .filter(animal -> (animal.age() >= k && animal.age() <= l))
            .collect(
                Collectors.groupingBy(Animal::type, Collectors.summingLong(Animal::weight)));
    }

    //16 Список животных, отсортированный по виду, затем по полу, затем по имени -> List<Animal>
    public List<Animal> getSortedListAnimal(List<Animal> animals) {
        return animals.stream()
            .sorted(Comparator.comparing(Animal::type)
                .thenComparing(Animal::sex)
                .thenComparing(Animal::name))
            .toList();

    }

    //17 Правда ли, что пауки кусаются чаще, чем собаки -> Boolean (если данных для ответа недостаточно, вернуть false)
    public boolean getSpiderBitesMoreThatDog(List<Animal> animals) {
        List<Animal> spiders = animals.stream().filter(animal -> animal.type() == Animal.Type.SPIDER).toList();
        List<Animal> dogs = animals.stream().filter(animal -> animal.type() == Animal.Type.DOG).toList();
        long countSpidersBites = spiders.stream().filter(Animal::bites).count();
        long countDogsBites = dogs.stream().filter(Animal::bites).count();
        if (spiders.isEmpty() || dogs.isEmpty()) {
            return false;
        } else {
            return countSpidersBites / spiders.size() > countDogsBites / dogs.size();
        }
    }

    //18 Найти самую тяжелую рыбку в 2-х или более списках -> Animal
    public Optional<Animal> getHeaviestFish(List<List<Animal>> list) {
        List<Animal> animals = list.stream()
            .flatMap(List::stream)
            .toList();
        return animals.stream()
            .filter(animal -> Animal.Type.FISH.equals(animal.type()))
            .max(Comparator.comparing(Animal::weight));
    }

    //19 Животные, в записях о которых есть ошибки: вернуть имя и список ошибок -> Map<String, Set<ValidationError>>.
    //Класс ValidationError и набор потенциальных проверок нужно придумать самостоятельно.
    public Map<String, Set<ValidationError>> getlistAnimalsErorrs(List<Animal> animals) {
        return animals.stream()
            .collect(Collectors.toMap(Animal::name, Validator::valid))
            .entrySet()
            .stream()
            .filter(stringSetEntry -> !stringSetEntry.getValue().isEmpty())
            .collect(Collectors.toMap(Map.Entry::getKey, Map.Entry::getValue));
    }

    //20 Сделать результат предыдущего задания более читабельным: вернуть имя и названия полей с ошибками,
    // объединенные в строку -> Map<String, String>
    public Map<String, String> getlistAnimalsErorrs2(List<Animal> animals) {
        return getlistAnimalsErorrs(animals)
            .entrySet()
            .stream()
            .collect(
                Collectors.toMap(
                    Map.Entry::getKey,
                    stringSetEntry -> stringSetEntry.getValue()
                        .stream()
                        .map(Record::toString)
                        .collect(joining(","))
                )
            );
    }

}
