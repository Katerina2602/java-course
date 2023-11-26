package edu.hw7.task3;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Optional;
import java.util.stream.Collectors;
import java.util.stream.Stream;
import org.jetbrains.annotations.Nullable;

public class PersonDatabaseSynchronized implements PersonDatabase {
    private final Map<Integer, Person> personsById = new HashMap<>();
    private final Map<String, List<Integer>> personsByName = new HashMap<>();
    private final Map<String, List<Integer>> personsByPhone = new HashMap<>();
    private final Map<String, List<Integer>> personsByAddress = new HashMap<>();

    @Override
    public synchronized void add(Person person) {
        personsById.put(person.id(), person);
        addPersonToMap(personsByName, person.name(), person.id());
        addPersonToMap(personsByPhone, person.phoneNumber(), person.id());
        addPersonToMap(personsByAddress, person.address(), person.id());
    }

    @Override
    public synchronized void delete(int id) {
        Optional.ofNullable(personsById.remove(id))
            .ifPresent(person -> {
                removePersonFromMap(personsByName, person.name(), person.id());
                removePersonFromMap(personsByPhone, person.phoneNumber(), person.id());
                removePersonFromMap(personsByAddress, person.address(), person.id());
            });
    }

    @Override
    public synchronized @Nullable List<Person> findByName(String name) {
        return Optional.ofNullable(personsByName.get(name))
            .map(ids ->
                ids.stream()
                    .map(personsById::get)
                    .toList()
            )
            .orElse(null);
    }

    @Override
    public synchronized @Nullable List<Person> findByAddress(String address) {
        return Optional.ofNullable(personsByAddress.get(address))
            .map(ids ->
                ids.stream()
                    .map(personsById::get)
                    .toList()
            )
            .orElse(null);
    }

    @Override
    public synchronized @Nullable List<Person> findByPhone(String phone) {
        return Optional.ofNullable(personsByPhone.get(phone))
            .map(ids ->
                ids.stream()
                    .map(personsById::get)
                    .toList()
            )
            .orElse(null);
    }

    private void addPersonToMap(Map<String, List<Integer>> map, String key, Integer value) {
        map.merge(
            key,
            new ArrayList<>(List.of(value)),
            this::concatLists
        );
    }

    private List<Integer> concatLists(List<Integer> list1, List<Integer> list2) {
        return Stream.concat(list1.stream(), list2.stream()).collect(Collectors.toList());
    }

    private void removePersonFromMap(Map<String, List<Integer>> map, String key, Integer value) {
        List<Integer> ids = map.get(key);
        ids.remove(value);
        if (ids.isEmpty()) {
            map.remove(key);
        }
    }
}
