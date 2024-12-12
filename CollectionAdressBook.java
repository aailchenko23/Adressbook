package com.example.demo1;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;

public class CollectionAdressBook implements AddressBook {
    private ObservableList<Person> personList = FXCollections.observableArrayList();

    @Override
    public void add(Person person) {
        personList.add(person);
    }

    @Override
    public void delete(Person person) {
        personList.remove(person);
    }

    @Override
    public void edit(Person person) {
        // Реалізація залежить від контексту: можливо, змінюються дані в існуючому об'єкті
        for (Person p : personList) {
            if (p.getPip().equals(person.getPip())) {
                p.setPhone(person.getPhone());
                break;
            }
        }
    }
    public ObservableList<Person> searchPerson(String searchText) {
        ObservableList<Person> filteredList = FXCollections.observableArrayList();

        // Якщо пошуковий текст не порожній
        if (searchText != null && !searchText.isEmpty()) {
            String searchLowerCase = searchText.toLowerCase();  // Перетворюємо весь текст в пошуковому полі в малий регістр

            // Перевірка кожної особи в списку
            for (Person person : personList) {
                // Порівнюємо імені особи з пошуковим текстом (обидва у нижньому регістрі)
                if (person.getPip().toLowerCase().contains(searchLowerCase)) {
                    filteredList.add(person);  // Додаємо особу, якщо є відповідність
                }
            }
        }
        return filteredList;  // Повертаємо відфільтрований список
    }

    public ObservableList<Person> getPersonList () {
                return personList;
            }

            public void print () {
                int number = 0;
                System.out.println();
                for (Person person : personList) {
                    number++;
                    System.out.println(number + ") ПІП: " + person.getPip() + "; Телефон:" + person.getPhone());
                }
            }
            public void fillTestData () {
                personList.clear();
                testData();
            }
            public void testData () {
                personList.add(new Person("Anastasia", "1233"));
                personList.add(new Person("Yulia", "1233"));
                personList.add(new Person("Oksana", "1233"));
                personList.add(new Person("Petro", "1233"));

            }
        }

