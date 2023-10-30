package edu.hw4;

import java.util.Comparator;
import java.util.List;
import java.util.Map;
import java.util.Optional;
import java.util.Set;
import java.util.stream.Collectors;
import static java.util.stream.Collectors.joining;

public class Task {
    public static final Integer ONE_HANGRIT = 100;

    //1
    public List<Animal> getSortByHeight(List<Animal> animal) {
        return animal.stream()
            .sorted(Comparator.comparing(Animal::height))
            .toList();
    }

    //2
    public List<Animal> getSortByWeight(List<Animal> animal, int k) {
        if (k >= animal.size() || k < 0) {
            throw new IllegalArgumentException("K не входит в размеры списка");
        }
        return animal.stream()
            .sorted(Comparator.comparing(Animal::weight).reversed())
            .limit(k)
            .toList();
    }

    //3
    public Map<Animal.Type, Long> getCountAnimalInOpinion(List<Animal> animal) {
        return animal.stream()
            .collect(
                Collectors.groupingBy(Animal::type, Collectors.counting()));
    }

    //4
    public Optional<Animal> getLongestName(List<Animal> animal) {
        return animal.stream()
            .max(Comparator.comparing(animal1 -> animal1.name().length()));
    }

    //5
    public Animal.Sex getSexWhoIsMore(List<Animal> animal) {
        return animal.stream()
            .collect(Collectors.groupingBy(Animal::sex, Collectors.counting()))
            .entrySet()
            .stream()
            .max(Map.Entry.comparingByValue())
            .get()
            .getKey();
    }

    //6
    public Map<Animal.Type, Optional<Animal>> getHeaviestAnimalOfType(List<Animal> animal) {
        return animal.stream()
            .collect(Collectors.groupingBy(Animal::type, Collectors.maxBy(Comparator.comparingInt(Animal::weight))));

    }

    //7
    public Optional<Animal> getOldestAnimalKPosition(List<Animal> animal, int k) {
        if (k >= animal.size() || k < 0) {
            throw new IllegalArgumentException("Значение K не входит в размеры списка");
        }
        return animal.stream()
            .sorted(Comparator.comparing(Animal::age).reversed())
            .skip(k).findFirst();
    }

    //8
    public Optional<Animal> getHeaviestAnimalAndBelowK(List<Animal> animal, int k) {
        if (k < 0) {
            throw new IllegalArgumentException("K не может быть отрицательным");
        }
        return animal.stream()
            .filter(p -> (p.height() < k)).max(Comparator.comparing(Animal::weight));
    }

    //9
    public Integer getSumAllPaws(List<Animal> animal) {
        return animal.stream().mapToInt(Animal::paws).sum();
    }

    //10
    public List<Animal> getAnimalWhenAgeIsNotCountPaws(List<Animal> animal) {
        return animal.stream()
            .filter(p -> (p.age() != p.paws())).toList();
    }

    //11
    public List<Animal> getAnimalThatBiteAndHeightOverHundred(List<Animal> animal) {
        return animal.stream()
            .filter(p -> (p.height() > ONE_HANGRIT) && p.bites()).toList();
    }

    //12
    public Long getCountAnimalWhenWeightMoreHeight(List<Animal> animals) {
        return animals.stream()
            .filter(p -> (p.weight() > p.height())).count();

    }

    //13
    public List<Animal> getAnimalNameWithMoreTwoWords(List<Animal> animals) {
        return animals.stream()
            .filter(animal -> animal.name().split(" ").length > 2)
            .toList();
    }

    //14
    public boolean getHasDogHeightMoreHundred(List<Animal> animals, int k) {
        if (k < 0) {
            throw new IllegalArgumentException("K не может быть отрицательными");
        }
        return animals.stream()
            .filter(animal -> animal.type() == Animal.Type.DOG)
            .anyMatch(animal -> animal.height() > k);
    }

    //15
    public Map<Animal.Type, Long> getSumWeightOfAnimalBtType(List<Animal> animals, int k, int l) {
        if (k <= 0 || l <= 0) {
            throw new IllegalArgumentException("K и L не могут быть отрицательными");
        }
        return animals.stream()
            .filter(animal -> (animal.age() >= k && animal.age() <= l))
            .collect(
                Collectors.groupingBy(Animal::type, Collectors.summingLong(Animal::weight)));
    }

    //16
    public List<Animal> getSortedListAnimal(List<Animal> animals) {
        return animals.stream()
            .sorted(Comparator.comparing(Animal::type)
                .thenComparing(Animal::sex)
                .thenComparing(Animal::name))
            .toList();

    }

    //17
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

    //18
    public Optional<Animal> getHeaviestFish(List<List<Animal>> list) {
        List<Animal> animals = list.stream()
            .flatMap(List::stream)
            .toList();
        return animals.stream()
            .filter(animal -> Animal.Type.FISH.equals(animal.type()))
            .max(Comparator.comparing(Animal::weight));
    }

    //19
    public Map<String, Set<ValidationError>> getlistAnimalsErorrs(List<Animal> animals) {
        return animals.stream()
            .collect(Collectors.toMap(Animal::name, Validator::valid))
            .entrySet()
            .stream()
            .filter(stringSetEntry -> !stringSetEntry.getValue().isEmpty())
            .collect(Collectors.toMap(Map.Entry::getKey, Map.Entry::getValue));
    }

    //20
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
