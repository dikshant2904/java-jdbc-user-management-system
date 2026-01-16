package com.company.usermanagement.userdao;

import com.company.usermanagement.model.User;
import com.company.usermanagement.util.DBUtil;
import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class UserDAO {

    // ✅ Add User
    public boolean addUser(User user) {

        String sql = "INSERT INTO users (id, name, email, age) VALUES (?, ?, ?, ?)";

        try (
            Connection con = DBUtil.getConnection();
            PreparedStatement ps = con.prepareStatement(sql)
        ) {
            ps.setInt(1, user.getId());
            ps.setString(2, user.getName());
            ps.setString(3, user.getEmail());
            ps.setInt(4, user.getAge());

            return ps.executeUpdate() > 0;

        } catch (SQLException e) {
            e.printStackTrace();
        }
        return false;
    }

    // ✅ Get All Users
    public List<User> getAllUsers() {

        List<User> users = new ArrayList<>();
        String sql = "SELECT id, name, email, age FROM users";

        try (
            Connection con = DBUtil.getConnection();
            PreparedStatement ps = con.prepareStatement(sql);
            ResultSet rs = ps.executeQuery()
        ) {
            while (rs.next()) {
                User user = new User(
                    rs.getInt("id"),
                    rs.getString("name"),
                    rs.getString("email"),
                    rs.getInt("age")
                );
                users.add(user);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return users;
    }

    // ✅ Get User By Id
    public User getUserById(int id) {

        String sql = "SELECT id, name, email, age FROM users WHERE id = ?";
        User user = null;

        try (
            Connection con = DBUtil.getConnection();
            PreparedStatement ps = con.prepareStatement(sql)
        ) {
            ps.setInt(1, id);
            ResultSet rs = ps.executeQuery();

            if (rs.next()) {
                user = new User(
                    rs.getInt("id"),
                    rs.getString("name"),
                    rs.getString("email"),
                    rs.getInt("age")
                );
            }

        } catch (SQLException e) {
            e.printStackTrace();
        }
        return user;
    }

    // ✅ Delete User By Id
    public boolean deleteById(int id) {

        String sql = "DELETE FROM users WHERE id = ?";

        try (
            Connection con = DBUtil.getConnection();
            PreparedStatement ps = con.prepareStatement(sql)
        ) {
            ps.setInt(1, id);
            return ps.executeUpdate() > 0;

        } catch (SQLException e) {
            e.printStackTrace();
        }
        return false;
    }
}
