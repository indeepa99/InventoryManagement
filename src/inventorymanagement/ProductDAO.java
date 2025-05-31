package inventorymanagement;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class ProductDAO {
    // Add Product
    public static boolean addProduct(String name, double price, int qty, String code, String supplier) {
        String sql = "INSERT INTO inventory (product_name, product_price, product_qty, product_code, product_supplier) VALUES (?, ?, ?, ?, ?)";
        try (Connection conn = DBConnection.getConnection();
             PreparedStatement pst = conn.prepareStatement(sql)) {
            pst.setString(1, name);
            pst.setDouble(2, price);
            pst.setInt(3, qty);
            pst.setString(4, code);
            pst.setString(5, supplier);
            return pst.executeUpdate() > 0;
        } catch (SQLException ex) {
            ex.printStackTrace();
            return false;
        }
    }

    // Update Product
    public static boolean updateProduct(int id, String name, double price, int qty, String code, String supplier) {
        String sql = "UPDATE inventory SET product_name=?, product_price=?, product_qty=?, product_code=?, product_supplier=? WHERE id=?";
        try (Connection conn = DBConnection.getConnection();
             PreparedStatement pst = conn.prepareStatement(sql)) {
            pst.setString(1, name);
            pst.setDouble(2, price);
            pst.setInt(3, qty);
            pst.setString(4, code);
            pst.setString(5, supplier);
            pst.setInt(6, id);
            return pst.executeUpdate() > 0;
        } catch (SQLException ex) {
            ex.printStackTrace();
            return false;
        }
    }

    // Delete Product
    public static boolean deleteProduct(int id) {
        String sql = "DELETE FROM inventory WHERE id=?";
        try (Connection conn = DBConnection.getConnection();
             PreparedStatement pst = conn.prepareStatement(sql)) {
            pst.setInt(1, id);
            return pst.executeUpdate() > 0;
        } catch (SQLException ex) {
            ex.printStackTrace();
            return false;
        }
    }

    // Get All Products
    public static List<Object[]> getAllProducts() {
        List<Object[]> products = new ArrayList<>();
        String sql = "SELECT * FROM inventory";
        try (Connection conn = DBConnection.getConnection();
             Statement st = conn.createStatement();
             ResultSet rs = st.executeQuery(sql)) {
            while (rs.next()) {
                Object[] row = {
                    rs.getInt("id"),
                    rs.getString("product_name"),
                    rs.getDouble("product_price"),
                    rs.getInt("product_qty"),
                    rs.getString("product_code"),
                    rs.getString("product_supplier")
                };
                products.add(row);
            }
        } catch (SQLException ex) {
            ex.printStackTrace();
        }
        return products;
    }

    // Get Product by Name
    public static Object[] getProductByName(String name) {
        String sql = "SELECT * FROM inventory WHERE product_name = ? LIMIT 1";
        try (Connection conn = DBConnection.getConnection();
             PreparedStatement pst = conn.prepareStatement(sql)) {
            pst.setString(1, name);
            try (ResultSet rs = pst.executeQuery()) {
                if (rs.next()) {
                    return new Object[] {
                        rs.getInt("id"),
                        rs.getString("product_name"),
                        rs.getDouble("product_price"),
                        rs.getInt("product_qty"),
                        rs.getString("product_code"),
                        rs.getString("product_supplier")
                    };
                }
            }
        } catch (SQLException ex) {
            ex.printStackTrace();
        }
        return null;
    }

    // Get all product names
    public static java.util.List<String> getAllProductNames() {
        java.util.List<String> names = new java.util.ArrayList<>();
        String sql = "SELECT product_name FROM inventory";
        try (Connection conn = DBConnection.getConnection();
             Statement st = conn.createStatement();
             ResultSet rs = st.executeQuery(sql)) {
            while (rs.next()) {
                names.add(rs.getString("product_name"));
            }
        } catch (SQLException ex) {
            ex.printStackTrace();
        }
        return names;
    }
} 