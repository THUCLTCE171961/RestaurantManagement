/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package DAO;

import Model.DrinkModel;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

/**
 *
 * @author Admin
 */
public class DrinkDAO extends DBContext {

    public void addDrink(DrinkModel drink) {
        try {
            // Gọi stored procedure để lấy FoodID mới
            String getNewDrinkIDQuery = "EXEC GenerateDrinkID";
            PreparedStatement psGetNewDrinkID = connection.prepareStatement(getNewDrinkIDQuery);
            ResultSet rs = psGetNewDrinkID.executeQuery();
            if (rs.next()) {
                String newDrinkID = rs.getString("NewDrinkID");
                drink.setDrinkID(newDrinkID);
            }

            // Chèn dữ liệu vào bảng Foods
            String query = "INSERT INTO Drinks (DrinkID, DrinkImg, DrinkName, BeverageID, DrinkDescription, Price, Quantity) VALUES (?, ?, ?, ?, ?, ?, ?)";
            PreparedStatement ps = connection.prepareStatement(query);
            ps.setString(1, drink.getDrinkID());
            ps.setString(2, drink.getDrinkImg());
            ps.setString(3, drink.getDrinkName());
            ps.setString(4, drink.getBeverageID());
            ps.setString(5, drink.getDescription());
            ps.setBigDecimal(6, drink.getPrice());
            ps.setInt(7, drink.getQuantity());
            ps.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public List<DrinkModel> getAllDrinks() {
        List<DrinkModel> list = new ArrayList<>();
        String query = "SELECT * FROM [dbo].[Drinks] ORDER BY DrinkID ASC";
        try {
            PreparedStatement ps = connection.prepareStatement(query);
            ResultSet rs = ps.executeQuery();
            while (rs.next()) {
                DrinkModel p = new DrinkModel(rs.getString(1), rs.getString(2), rs.getString(3),
                        rs.getString(4), rs.getString(5), rs.getBigDecimal(6), rs.getInt(7));
                list.add(p);
            }
        } catch (SQLException e) {
            System.out.println("loi getAll");
        }
        return list;
    }

    public void updateDrink(DrinkModel drink) {
//        try {
//            String query = "UPDATE Drinks SET DrinkImg = ?, DrinkName = ?, BeverageID = ?, DrinkDescription = ?, Price = ?, Quantity = ? WHERE DrinkID = ?";
//            PreparedStatement ps = connection.prepareStatement(query);
//            ps.setString(1, drink.getDrinkImg());
//            ps.setString(2, drink.getDrinkName());
//            ps.setString(3, drink.getBeverageID());
//            ps.setString(4, drink.getDescription());
//            ps.setBigDecimal(5, drink.getPrice());
//            ps.setInt(6, drink.getQuantity());
//            ps.setString(7, drink.getDrinkID());
//            ps.executeUpdate();
//
//        } catch (SQLException e) {
//            e.printStackTrace();
//        }

        String query = "UPDATE Drinks SET DrinkImg = ?, DrinkName = ?, BeverageID = ?, DrinkDescription = ?, Price = ?, Quantity = ? WHERE DrinkID = ?";
        try ( PreparedStatement ps = connection.prepareStatement(query)) {
            ps.setString(1, drink.getDrinkImg());
            ps.setString(2, drink.getDrinkName());
            ps.setString(3, drink.getBeverageID());
            ps.setString(4, drink.getDescription());
            ps.setBigDecimal(5, drink.getPrice());
            ps.setInt(6, drink.getQuantity());
            ps.setString(7, drink.getDrinkID()); // Assure DrinkID is an Integer

            System.out.println("Executing query: " + query);
            System.out.println("DrinkImg: " + drink.getDrinkImg());
            System.out.println("DrinkName: " + drink.getDrinkName());
            System.out.println("BeverageID: " + drink.getBeverageID());
            System.out.println("Description: " + drink.getDescription());
            System.out.println("Price: " + drink.getPrice());
            System.out.println("Quantity: " + drink.getQuantity());
            System.out.println("DrinkID: " + drink.getDrinkID());

            int rowsAffected = ps.executeUpdate();
            System.out.println("Rows affected: " + rowsAffected);
        } catch (SQLException e) {
            System.err.println("SQL Error: " + e.getMessage());
            e.printStackTrace();
        }
    }

    public DrinkModel getDrinkById(String id) {
        String query = "select * from Drinks where DrinkID = ?";
        try {
            PreparedStatement ps = connection.prepareStatement(query);
            ps.setString(1, id);
            ResultSet rs = ps.executeQuery();
            if (rs.next()) {
                DrinkModel f = new DrinkModel(rs.getString(1), rs.getString(2), rs.getString(3),
                        rs.getString(4), rs.getString(5), rs.getBigDecimal(6), rs.getInt(7));
                return f;
            }
        } catch (SQLException e) {
            System.out.println("loi byID");
        }
        return null;
    }

    public void deleteDrink(String drinkID) {
        String query = "DELETE FROM [dbo].[Drinks]\n"
                + "      WHERE DrinkID=?";

        try {
            PreparedStatement ps = connection.prepareStatement(query);
            ps.setString(1, drinkID);
            ps.executeUpdate();
        } catch (SQLException e) {
            System.out.println(e);
        }
    }

    public List<DrinkModel> searchDrink(String name) {
        List<DrinkModel> list = new ArrayList<>();
        String query = "select * from Drinks where DrinkName like ?";
        try {
            PreparedStatement ps = connection.prepareStatement(query);
            ps.setString(1, name + "%");
            ResultSet rs = ps.executeQuery();
            while (rs.next()) {
                DrinkModel p = new DrinkModel(rs.getString(1), rs.getString(2), rs.getString(3),
                        rs.getString(4), rs.getString(5), rs.getBigDecimal(6), rs.getInt(7));
                list.add(p);
            }
        } catch (SQLException e) {
            System.out.println("loi name");
        }
        return list;
    }
}
