package com.example;

import com.example.domain.Person;
import com.example.exception.FormatDataException;

import java.io.IOException;
import java.nio.charset.StandardCharsets;
import java.nio.file.Files;
import java.nio.file.Path;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

/**
 * Класс служит для парсинга данных из строки (в .txt файлах) в Person
 */
public final class Parser {

    private Parser() {
    }

    /**
     * Считывает данные из файла и конвертирует их в Person.
     * @param path - путь к файлу
     * @return - список Person
     * @throws IOException
     * @throws FormatDataException
     */
    public static List<Person> parse(Path path) throws IOException, FormatDataException {

        List<String> listEntries = Files.readAllLines(path, StandardCharsets.UTF_8);
        List<Person> listPersons = new ArrayList<>();

        for (String str : listEntries) {
            if (str.equals("")) continue;

            Person person = new Person();
            String[] persons = str.replaceAll("[\\s]{2,}", " ")
                    .trim()
                    .split(" ");

            if (persons.length < 7 || persons.length > 7) {
                throw new FormatDataException();
            }

            person.setLastName(persons[0].trim());
            person.setFirstName(persons[1].trim());
            person.setMiddleName(persons[2].trim());

            if (checkFormatSex(persons[3])) {
                if (persons[3].equals("муж.")) {
                    person.setSex("муж.");
                } else if (persons[3].equals("жен.")) {
                    person.setSex("жен.");
                }
            } else {
                throw new FormatDataException();
            }

            if (checkFormatDate(persons[4])) {
                person.setDate(persons[4]);
            } else {
                throw new FormatDataException();
            }

            person.setHairColor(persons[5]);
            person.setEyeColor(persons[6]);

            listPersons.add(person);
        }

        Collections.sort(listPersons);

        return listPersons;
    }

    private static boolean checkFormatSex(String sex) {
        return sex.equals("муж.") || sex.equals("жен.");
    }

    private static boolean checkFormatDate(String date) {
        return date.matches("\\d{2}\\.\\d{2}\\.\\d{4}");
    }
}
