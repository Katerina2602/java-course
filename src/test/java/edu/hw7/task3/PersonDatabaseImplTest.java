package edu.hw7.task3;

import java.util.ArrayList;
import java.util.List;
import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.junit.jupiter.api.Assertions.assertNull;

class PersonDatabaseImplTest {
    @Test
    void willReturnAllPersonNamedEkaterina() {
        PersonDatabase personActual = createSynchronizedDatabase();
        List<Person> actualByName = personActual.findByName("Ekaterina");
        List<Person> expectedByName = new ArrayList<>();
        expectedByName.add(new Person(1, "Ekaterina", "Moscow", "895421112"));
        expectedByName.add(new Person(2, "Ekaterina", "Voronez", "892045878"));
        assertEquals(expectedByName, actualByName);

    }

    @Test
    void willReturnAllPersonAddressMoscow() {
        PersonDatabase personActual = createSynchronizedDatabase();
        List<Person> actualByAddress = personActual.findByAddress("Moscow");
        List<Person> expectedByAddress = new ArrayList<>();
        expectedByAddress.add(new Person(1, "Ekaterina", "Moscow", "895421112"));
        expectedByAddress.add(new Person(4, "Mark", "Moscow", "8915474525"));
        expectedByAddress.add(new Person(5, "Elena", "Moscow", "8745626125"));
        assertEquals(expectedByAddress, actualByAddress);

    }

    @Test
    void willReturnPersonSpecifiedPhone() {
        PersonDatabase personActual = createSynchronizedDatabase();
        List<Person> actualByPhone = personActual.findByPhone("8951475202");
        List<Person> expectedByPhone = new ArrayList<>();
        expectedByPhone.add(new Person(6, "Alexandr", "Voronez", "8951475202"));

        assertEquals(expectedByPhone, actualByPhone);

    }

    @Test
    void willReturnCorrectListAfterDeletion() {
        PersonDatabase personActual = createSynchronizedDatabase();
        personActual.delete(1);
        personActual.delete(2);
        List<Person> actualByName = personActual.findByName("Ekaterina");
        assertNull(actualByName);

    }

    @Test
    void testSynchronizedDatabase() throws InterruptedException {
        PersonDatabase person = createSynchronizedDatabase();

        Thread readThread = new Thread(() -> {
            List<Person> byAddress = person.findByAddress("Lipetsk");
            List<Person> byName = person.findByName("Oleg");
            List<Person> byPhone = person.findByPhone("8457885854");
            assertNotNull(byAddress);
            assertNotNull(byName);
            assertNotNull(byPhone);
        });

        Thread writeThread = new Thread(() -> {
            person.add(new Person(8, "Oleg", "Lipetsk", "8457885854"));
            person.add(new Person(9, "Mark", "Moscow", "8915452215"));
            person.add(new Person(10, "Elena", "Moscow", "8745452125"));
        });

        writeThread.start();
        readThread.start();

        readThread.join();
        writeThread.join();
    }

    @Test
    void testReadWriteLockDatabase() throws InterruptedException {
        PersonDatabase person = createReadWriteDatabase();

        Thread readThread = new Thread(() -> {
            List<Person> byAddress = person.findByAddress("Lipetsk");
            List<Person> byName = person.findByName("Oleg");
            List<Person> byPhone = person.findByPhone("8457885854");
            assertNotNull(byAddress);
            assertNotNull(byName);
            assertNotNull(byPhone);
        });

        Thread writeThread = new Thread(() -> {
            person.add(new Person(8, "Oleg", "Lipetsk", "8457885854"));
            person.add(new Person(9, "Mark", "Moscow", "8915452215"));
            person.add(new Person(10, "Elena", "Moscow", "8745452125"));
        });

        writeThread.start();
        readThread.start();

        readThread.join();
        writeThread.join();
    }

    private PersonDatabase createSynchronizedDatabase() {
        PersonDatabase personActual = new PersonDatabaseSynchronized();
        personActual.add(new Person(1, "Ekaterina", "Moscow", "895421112"));
        personActual.add(new Person(2, "Ekaterina", "Voronez", "892045878"));
        personActual.add(new Person(3, "Masha", "Pavlovsk", "895125112"));
        personActual.add(new Person(4, "Mark", "Moscow", "8915474525"));
        personActual.add(new Person(5, "Elena", "Moscow", "8745626125"));
        personActual.add(new Person(6, "Alexandr", "Voronez", "8951475202"));
        personActual.add(new Person(7, "Mark", "Pavlovsk", "8915474455"));

        return personActual;
    }

    private PersonDatabase createReadWriteDatabase() {
        PersonDatabase personActual = new PersonDatabaseReadWriteLock();
        personActual.add(new Person(1, "Ekaterina", "Moscow", "895421112"));
        personActual.add(new Person(2, "Ekaterina", "Voronez", "892045878"));
        personActual.add(new Person(3, "Masha", "Pavlovsk", "895125112"));
        personActual.add(new Person(4, "Mark", "Moscow", "8915474525"));
        personActual.add(new Person(5, "Elena", "Moscow", "8745626125"));
        personActual.add(new Person(6, "Alexandr", "Voronez", "8951475202"));
        personActual.add(new Person(7, "Mark", "Pavlovsk", "8915474455"));

        return personActual;
    }
}
