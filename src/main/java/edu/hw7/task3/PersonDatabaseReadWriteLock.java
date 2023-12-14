package edu.hw7.task3;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Optional;
import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReadWriteLock;
import java.util.concurrent.locks.ReentrantReadWriteLock;
import java.util.stream.Collectors;
import java.util.stream.Stream;
import org.jetbrains.annotations.Nullable;

public class PersonDatabaseReadWriteLock implements PersonDatabase {
    private final Map<Integer, Person> personsById = new HashMap<>();
    private final Map<String, List<Integer>> personsByName = new HashMap<>();
    private final Map<String, List<Integer>> personsByPhone = new HashMap<>();
    private final Map<String, List<Integer>> personsByAddress = new HashMap<>();
    private final ReadWriteLock readWriteLock = new ReentrantReadWriteLock();
    private final Lock readLock = readWriteLock.readLock();
    private final Lock writeLock = readWriteLock.writeLock();

    @Override
    public void add(Person person) {
        writeLock.lock();
        try {
            personsById.put(person.id(), person);
            addPersonToMap(personsByName, person.name(), person.id());
            addPersonToMap(personsByPhone, person.phoneNumber(), person.id());
            addPersonToMap(personsByAddress, person.address(), person.id());
        } finally {
            writeLock.unlock();
        }
    }

    @Override
    public void delete(int id) {
        writeLock.lock();
        try {
            Optional.ofNullable(personsById.remove(id))
                .ifPresent(person -> {
                    removePersonFromMap(personsByName, person.name(), person.id());
                    removePersonFromMap(personsByPhone, person.phoneNumber(), person.id());
                    removePersonFromMap(personsByAddress, person.address(), person.id());
                });
        } finally {
            writeLock.unlock();
        }
    }

    @Override
    public @Nullable List<Person> findByName(String name) {
        readLock.lock();
        try {
            return Optional.ofNullable(personsByName.get(name))
                .map(ids ->
                    ids.stream()
                        .map(personsById::get)
                        .toList()
                )
                .orElse(null);
        } finally {
            readLock.unlock();
        }
    }

    @Override
    public @Nullable List<Person> findByAddress(String address) {
        readLock.lock();
        try {
            return Optional.ofNullable(personsByAddress.get(address))
                .map(ids ->
                    ids.stream()
                        .map(personsById::get)
                        .toList()
                )
                .orElse(null);
        } finally {
            readLock.unlock();
        }
    }

    @Override
    public @Nullable List<Person> findByPhone(String phone) {
        readLock.lock();
        try {
            return Optional.ofNullable(personsByPhone.get(phone))
                .map(ids ->
                    ids.stream()
                        .map(personsById::get)
                        .toList()
                )
                .orElse(null);
        } finally {
            readLock.unlock();
        }
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
