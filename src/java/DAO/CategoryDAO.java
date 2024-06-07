/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package DAO;

import Model.CategoryModel;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

/**
 *
 * @author Admin
 */
public class CategoryDAO extends DBContext {

    private static final String SELECT_ALL_CATEGORIES = "SELECT * FROM Category";
    private static final String SELECT_CATEGORY_BY_ID = "SELECT * FROM Category WHERE CategoryID = ?";

    public List<CategoryModel> getAllCategories() {
    List<CategoryModel> categories = new ArrayList<>();
    try {
        String query = "SELECT CategoryID, CategoryName FROM Categories";
        PreparedStatement ps = connection.prepareStatement(query);
        ResultSet rs = ps.executeQuery();
        while (rs.next()) {
            CategoryModel category = new CategoryModel();
            category.setCategoryID(rs.getString("CategoryID"));
            category.setCategoryName(rs.getString("CategoryName"));
            categories.add(category);
        }
    } catch (SQLException e) {
        e.printStackTrace();
    }
    return categories;
}

    public boolean categoryExists(String categoryID) {
        try (PreparedStatement ps = connection.prepareStatement(SELECT_CATEGORY_BY_ID)) {
            ps.setString(1, categoryID);
            ResultSet rs = ps.executeQuery();
            return rs.next();
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return false;
    }
}
