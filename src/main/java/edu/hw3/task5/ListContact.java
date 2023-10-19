package edu.hw3.task5;

import java.util.ArrayList;
import java.util.Collections;
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
            String[] s = listContact[i].split(" ");
            if (s.length == 2) {
                sortListContact.add(i, new Pair(s[1] + s[0], s[0] + " " + s[1]));
            }
            if (s.length == 1) {
                sortListContact.add(i, new Pair(s[0], s[0]));
            }
        }

        if ("ASC".equals(sortType)) {
            sortListContact.sort((o1, o2) -> o1.lastname().compareTo(o2.lastname()));
        }
        if ("DESC".equals(sortType)) {
            sortListContact.sort((o1, o2) -> o2.lastname().compareTo(o1.lastname()));
        }

        return sortListContact.stream().map(Pair::lastAndFirstName).toList();
    }
}
