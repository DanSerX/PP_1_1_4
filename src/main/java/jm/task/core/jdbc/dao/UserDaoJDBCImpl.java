package jm.task.core.jdbc.dao;

import jm.task.core.jdbc.model.User;
import jm.task.core.jdbc.util.Util;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

public class UserDaoJDBCImpl implements UserDao {
    public UserDaoJDBCImpl() {

    }

    public void createUsersTable() {
        String create = "create table if not exists userX (id INT AUTO_INCREMENT PRIMARY KEY, name VARCHAR(30), lastName VARCHAR(30), age INT)";
        try (Connection connection = Util.getConnection(); Statement statement = connection.createStatement()) {
            statement.executeUpdate(create);
        } catch (SQLException e) {
            e.printStackTrace();
        };
    }

    public void dropUsersTable() {
        String drop = "DROP TABLE IF EXISTS UserX";
        try (Connection connection = Util.getConnection(); Statement statement = connection.createStatement()) {
            statement.executeUpdate(drop);
        } catch (SQLException e) {
            e.printStackTrace();
        };
    }

    public void saveUser(String name, String lastName, byte age) {
        String save = String.format("insert into UserX (name, lastName, age) values ('%s', '%s', '%s')", name, lastName, age);
        try (Connection connection = Util.getConnection(); Statement statement = connection.createStatement()) {
            statement.executeUpdate(save);
        } catch (SQLException e) {
            e.printStackTrace();
        };
    }

    public void removeUserById(long id) {
        String remove = String.format("delete from UserX where id = %s", id);
        try (Connection connection = Util.getConnection(); Statement statement = connection.createStatement()) {
            statement.executeUpdate(remove);
        } catch (SQLException e) {
            e.printStackTrace();
        };
    }

    public List<User> getAllUsers() {
        List<User> allUsers = new ArrayList<>();
        String get = "select * from UserX";

        try (Connection connection = Util.getConnection(); Statement statement = connection.createStatement()) {
            ResultSet rset = statement.executeQuery(get);
            while (rset.next()) {
                User user = new User();
                user.setId(rset.getLong("id"));
                user.setName(rset.getString("name"));
                user.setLastName(rset.getString("lastName"));
                user.setAge(rset.getByte("age"));
                allUsers.add(user);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        };
        return allUsers;
    }

    public void cleanUsersTable() {
        String delete = "delete from UserX";
        try (Connection connection = Util.getConnection(); Statement statement = connection.createStatement()) {
            statement.executeUpdate(delete);
        } catch (SQLException e) {
            e.printStackTrace();
        };
    }
}
