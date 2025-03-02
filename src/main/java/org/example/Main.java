package org.example;

import java.sql.Connection;

public class Main {
    public static void main(String[] args) {
        Connection connection = JdbcUtils.getConnection();
        PersonDao personDao = new PersonDao(connection);
//        Person person = new Person();
//        person.setFirstName("Test firstname");
//        person.setLastName("Test lastname");
//        System.out.println(personDao.savePerson(person));
//        personDao.printPersons();
//        System.out.println(personDao.findById(11L));
//        Person person = new Person();
//        person.setId(2L);
//        person.setFirstName("Firstname");
//        person.setLastName("Lastname");
//        personDao.updatePerson(person);
        personDao.deleteById(2L);
        personDao.printPersons();
    }
}