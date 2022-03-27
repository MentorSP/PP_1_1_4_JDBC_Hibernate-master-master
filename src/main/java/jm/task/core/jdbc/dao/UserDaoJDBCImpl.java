package jm.task.core.jdbc.dao;

import jm.task.core.jdbc.model.User;
import jm.task.core.jdbc.util.Util;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class UserDaoJDBCImpl implements UserDao {
    public UserDaoJDBCImpl() {

    }


    public void createUsersTable() {
        try (Connection connection = Util.getConnection();
             Statement statement = connection.createStatement()) {
            statement.executeUpdate("create table if not exists userstable (" +
                    "id bigint auto_increment, " +
                    "name varchar(40) not null, " +
                    "lastName varchar(40) not null, " +
                    "age int not null, " +
                    "primary key (id))");
            System.out.println("Таблица создана");
        } catch (SQLException e) {
            e.printStackTrace();
            System.out.println("Не создалась таблица");
        }
    }

    public void dropUsersTable() {
        try (Connection connection = Util.getConnection();
             Statement statement = connection.createStatement()) {
            statement.executeUpdate("DROP TABLE IF EXISTS userstable");
            System.out.println("Таблица удалена");
        } catch (SQLException e) {
            System.out.println("Не удалилась таблица");
            e.printStackTrace();
        }
    }

    public void saveUser(String name, String lastName, byte age) {
        try (Connection connection = Util.getConnection();
             PreparedStatement preparedStatement = connection.prepareStatement("INSERT INTO userstable (name, lastName, age) values (?, ?, ?)")) {
            preparedStatement.setString(1, name);
            preparedStatement.setString(2, lastName);
            preparedStatement.setByte(3, age);
            preparedStatement.executeUpdate();
            System.out.println("User с именем - " + name + " добавлен в базу дынных");
        } catch (SQLException e) {
            System.out.println("Не добавился пользователь");
            e.printStackTrace();
        }
    }

    public void removeUserById(long id) {
        try (Connection connection = Util.getConnection();
             PreparedStatement preparedStatement = connection.prepareStatement("DELETE FROM userstable where id = ?")) {
            preparedStatement.setLong(1, id);
            preparedStatement.executeUpdate();
            System.out.println("Пользователь с id " + id + " удален из БД");
        } catch (SQLException e) {
            e.printStackTrace();
            System.out.println("Не удалился пользователь");
        }
    }

    public List<User> getAllUsers() {
        List<User> userList = new ArrayList<>();
        try (Connection connection = Util.getConnection();
        Statement statement = connection.createStatement()) {
            ResultSet resultSet = statement.executeQuery("SELECT * FROM userstable");
            while (resultSet.next()) {
                String name = resultSet.getString("name");
                String lastName = resultSet.getString("lastName");
                Byte age = resultSet.getByte("age");
                User temp = new User(name, lastName, age);
                userList.add(temp);
                System.out.println("Взята БД");
            }
        } catch (SQLException e) {
            e.printStackTrace();
            System.out.println("Не взяты данные из таблицы");
        }
        return userList;
    }

    public void cleanUsersTable() {
        try (Connection connection = Util.getConnection();
        PreparedStatement preparedStatement = connection.prepareStatement("DELETE FROM  userstable")){
            preparedStatement.executeUpdate();
            System.out.println("Таблица очищена");
        } catch (SQLException e) {
            e.printStackTrace();
            System.out.println("Не очистилась таблица");
        }
    }
}