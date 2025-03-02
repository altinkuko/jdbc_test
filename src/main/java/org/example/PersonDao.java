package org.example;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class PersonDao {
    private final Connection connection;

    public PersonDao(Connection connection) {
        this.connection = connection;
    }

    public void printPersons() {
        try {
            List<Person> personList = new ArrayList<>();
            String sql = "select * from persons";
            PreparedStatement preparedStatement = connection.prepareStatement(sql);
            ResultSet resultSet = preparedStatement.executeQuery();
            while (resultSet.next()) {
                Person person = new Person();
                person.setId(resultSet.getLong(1));
                person.setFirstName(resultSet.getString(2));
                person.setLastName(resultSet.getString(3));
                person.setAddress(resultSet.getString(4));
                personList.add(person);
//                System.out.println("Id:" + resultSet.getLong(1));
//                System.out.println("FirstName:" + resultSet.getString(2));
//                System.out.println("LastName:" + resultSet.getString(3));
//                System.out.println("Address:" + resultSet.getString(4));
            }
            personList.forEach(person -> System.out.println(person));
        } catch (SQLException e) {
            throw new RuntimeException(e);
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }

    public boolean savePerson(Person person) {
        try {
            String sql = "insert into persons values(null,?,?,?)";
            PreparedStatement preparedStatement = connection.prepareStatement(sql);
//            preparedStatement.setLong(1, person.getId());
            preparedStatement.setString(1, person.getFirstName());
            preparedStatement.setString(2, person.getLastName());
            preparedStatement.setString(3, person.getAddress());
            return preparedStatement.execute();
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

    public Person findById(Long id) {
        try {
            String sql = "select * from persons where id = ?";
            PreparedStatement preparedStatement = connection.prepareStatement(sql);
            preparedStatement.setLong(1, id);
            ResultSet resultSet = preparedStatement.executeQuery();
            if (resultSet.next()) {
                Person person = new Person();
                person.setId(resultSet.getLong(1));
                person.setFirstName(resultSet.getString(2));
                person.setLastName(resultSet.getString(3));
                person.setAddress(resultSet.getString(4));
                return person;
            } else {
                String message = String.format("Personi me id %s nuk u gjet", id);
                throw new RuntimeException(message);
            }
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

    public Integer updatePerson(Person person) {
        try {
            String sql = "update persons set firstName = ?, lastName = ?, adress = ? where id = ?";
            PreparedStatement preparedStatement = connection.prepareStatement(sql);
            preparedStatement.setString(1, person.getFirstName());
            preparedStatement.setString(2, person.getLastName());
            preparedStatement.setString(3, person.getAddress());
            preparedStatement.setLong(4, person.getId());
            return preparedStatement.executeUpdate();
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }

    public void deleteById(Long id) {
        try {
            String sql = String.format("delete from persons where id = %s", id);
            Statement statement = connection.createStatement();
            statement.execute(sql);
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }

    //Test
}
