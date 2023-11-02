package edu.hw3.task5;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.List;

public class ListContact {
    private ListContact() {

    }

    public static List<String> contact(String[] listContact, String sortType) {

        if (listContact == null) {
            return Collections.emptyList();
        }
        List<Pair> sortListContact = new ArrayList<>();

        for (int i = 0; i < listContact.length; i++) {
            String[] s = listContact[i].split("\\s+");
            if (s.length == 2) {
                sortListContact.add(i, new Pair(s[1] + s[0], s[0] + " " + s[1]));
            }
            if (s.length == 1) {
                sortListContact.add(i, new Pair(s[0], s[0]));
            }
        }

        switch (sortType) {
            case "ASC":
                sortListContact.sort(Comparator.comparing(Pair::lastname));
                break;
            case "DESC":
                sortListContact.sort(Comparator.comparing(Pair::lastname).reversed());
                break;
            default:
                throw new IllegalArgumentException();
        }

        return sortListContact.stream().map(Pair::lastAndFirstName).toList();
    }
}
