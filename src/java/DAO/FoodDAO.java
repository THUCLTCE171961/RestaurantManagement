/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package DAO;

import Model.FoodModel;
import java.sql.CallableStatement;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

/**
 *
 * @author Admin
 */
public class FoodDAO extends DBContext {

    public void addFood(FoodModel food) {
        try {
            // Gọi stored procedure để lấy FoodID mới
            String getNewFoodIDQuery = "EXEC GenerateFoodID";
            PreparedStatement psGetNewFoodID = connection.prepareStatement(getNewFoodIDQuery);
            ResultSet rs = psGetNewFoodID.executeQuery();
            if (rs.next()) {
                String newFoodID = rs.getString("NewFoodID");
                food.setFoodID(newFoodID);
            }

            // Chèn dữ liệu vào bảng Foods
            String query = "INSERT INTO Foods (FoodID, FoodImg, FoodName, CategoryID, FoodDescription, Price, Quantity) VALUES (?, ?, ?, ?, ?, ?, ?)";
            PreparedStatement ps = connection.prepareStatement(query);
            ps.setString(1, food.getFoodID());
            ps.setString(2, food.getFoodImg());
            ps.setString(3, food.getFoodName());
            ps.setString(4, food.getCategoryID());
            ps.setString(5, food.getDescription());
            ps.setBigDecimal(6, food.getPrice());
            ps.setInt(7, food.getQuantity());
            ps.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public List<FoodModel> getAllFoods() {
        List<FoodModel> list = new ArrayList<>();
        String query = "SELECT * FROM [dbo].[Foods] ORDER BY FoodID ASC";
        try {
            PreparedStatement ps = connection.prepareStatement(query);
            ResultSet rs = ps.executeQuery();
            while (rs.next()) {
                FoodModel p = new FoodModel(rs.getString(1), rs.getString(2), rs.getString(3),
                        rs.getString(4), rs.getString(5), rs.getBigDecimal(6), rs.getInt(7));
                list.add(p);
            }
        } catch (SQLException e) {
            System.out.println("loi getAll");
        }
        return list;
    }

    public void updateFood(FoodModel food) {
        try {
            String query = "UPDATE Foods SET FoodImg = ?, FoodName = ?, CategoryID = ?, FoodDescription = ?, Price = ?, Quantity = ? WHERE FoodID = ?";
            PreparedStatement ps = connection.prepareStatement(query);
            ps.setString(1, food.getFoodImg());
            ps.setString(2, food.getFoodName());
            ps.setString(3, food.getCategoryID());
            ps.setString(4, food.getDescription());
            ps.setBigDecimal(5, food.getPrice());
            ps.setInt(6, food.getQuantity());
            ps.setString(7, food.getFoodID());
            ps.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public FoodModel getFoodById(String id) {
        String query = "select * from Foods where FoodID=?";
        try {
            PreparedStatement ps = connection.prepareStatement(query);
            ps.setString(1, id);
            ResultSet rs = ps.executeQuery();
            if (rs.next()) {
                FoodModel f = new FoodModel(rs.getString(1), rs.getString(2), rs.getString(3),
                        rs.getString(4), rs.getString(5), rs.getBigDecimal(6), rs.getInt(7));
                return f;
            }
        } catch (SQLException e) {
            System.out.println("loi byID");
        }
        return null;
    }

    public void deleteFood(String foodID) {
        String query = "DELETE FROM [dbo].[Foods]\n"
                + "      WHERE FoodID=?";

        try {
            PreparedStatement ps = connection.prepareStatement(query);
            ps.setString(1, foodID);
            ps.executeUpdate();
        } catch (SQLException e) {
            System.out.println(e);
        }
    }

    public List<FoodModel> searchFood(String name) {
        List<FoodModel> list = new ArrayList<>();
        String query = "select * from Foods where FoodName like ?";
        try {
            PreparedStatement ps = connection.prepareStatement(query);
            ps.setString(1, name + "%");
            ResultSet rs = ps.executeQuery();
            while (rs.next()) {
                FoodModel p = new FoodModel(rs.getString(1), rs.getString(2), rs.getString(3),
                        rs.getString(4), rs.getString(5), rs.getBigDecimal(6), rs.getInt(7));
                list.add(p);
            }
        } catch (SQLException e) {
            System.out.println("loi name");
        }
        return list;
    }
}
