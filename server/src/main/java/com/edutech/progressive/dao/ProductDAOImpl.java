package com.edutech.progressive.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import com.edutech.progressive.config.DatabaseConnectionManager;
import com.edutech.progressive.entity.Product;

public class ProductDAOImpl implements ProductDAO {
 
    @Override


    public int addProduct(Product product) throws SQLException {


        Connection connection = null;


        PreparedStatement statement = null;


        int generatedID = -1;
 
        try {


            connection = DatabaseConnectionManager.getConnection();


            String sql = "INSERT INTO product (warehouse_id, product_name, product_description, quantity, price) VALUES (?, ?, ?, ?, ?)";


            statement = connection.prepareStatement(sql, PreparedStatement.RETURN_GENERATED_KEYS);


            statement.setDouble(1, product.getWarehouseId());


            statement.setString(2, product.getProductName());


            statement.setString(3, product.getProductDescription());


            statement.setInt(4, product.getQuantity());


            statement.setDouble(5, product.getPrice());


            statement.executeUpdate();
 
            ResultSet resultSet = statement.getGeneratedKeys();


            if (resultSet.next()) {


                generatedID = resultSet.getInt(1);


                product.setProductId(generatedID);


            }


        } catch (SQLException e) {


            e.printStackTrace();


            throw e; // Rethrow the exception


        } finally {


            // Close resources in the reverse order of opening


            if (statement != null) {


                statement.close();


            }


        }


        return generatedID;


    }
 
    @Override


    public Product getProductById(int productId) throws SQLException {


        Connection connection = null;


        PreparedStatement statement = null;


        ResultSet resultSet = null;
 
        try {


            connection = DatabaseConnectionManager.getConnection();


            String sql = "SELECT * FROM product WHERE product_id = ?";


            statement = connection.prepareStatement(sql);


            statement.setInt(1, productId);


            resultSet = statement.executeQuery();
 
            if (resultSet.next()) {


                int warehouseId = resultSet.getInt("warehouse_id");


                String productName = resultSet.getString("product_name");


                String productDescription = resultSet.getString("product_description");


                int quantity = resultSet.getInt("quantity");


                Long price = (long) resultSet.getDouble("price");


                return new Product(productId, warehouseId, productName, productDescription, quantity, price);


            }


        } catch (SQLException e) {


            e.printStackTrace();


            throw e; // Rethrow the exception


        } finally {


            if (connection != null) {


                connection.close();


            }


        }


        return null;


    }
 
    @Override


    public void updateProduct(Product product) throws SQLException {


        Connection connection = null;


        PreparedStatement statement = null;
 
        try {


            connection = DatabaseConnectionManager.getConnection();


            String sql = "UPDATE product SET warehouse_id = ?, product_name = ?, product_description = ?, quantity =?, price =? WHERE product_id = ?";


            statement = connection.prepareStatement(sql);


            statement.setInt(1, product.getWarehouseId());


            statement.setString(2, product.getProductName());


            statement.setString(3, product.getProductDescription());


            statement.setInt(4, product.getQuantity());


            statement.setDouble(5, product.getPrice());


            statement.setInt(6, product.getProductId());


            statement.executeUpdate();


        } catch (SQLException e) {


            e.printStackTrace();


            throw e; // Rethrow the exception


        } finally {


            if (connection != null) {


                connection.close();


            }


        }


    }
 
    @Override


    public void deleteProduct(int productId) throws SQLException {


        Connection connection = null;


        PreparedStatement statement = null;
 
        try {


            connection = DatabaseConnectionManager.getConnection();


            String sql = "DELETE FROM product WHERE product_id = ?";


            statement = connection.prepareStatement(sql);


            statement.setInt(1, productId);


            statement.executeUpdate();


        } catch (SQLException e) {


            e.printStackTrace();


            throw e; // Rethrow the exception


        } finally {


            if (connection != null) {


                connection.close();


            }


        }


    }
 
    @Override


    public List<Product> getAllProducts() throws SQLException {


        List<Product> products = new ArrayList<>();


        Connection connection = null;


        PreparedStatement statement = null;


        ResultSet resultSet = null;
 
        try {


            connection = DatabaseConnectionManager.getConnection();


            String sql = "SELECT * FROM product";


            statement = connection.prepareStatement(sql);


            resultSet = statement.executeQuery();
 
            while (resultSet.next()) {


                int productId = resultSet.getInt("product_id");


                int warehouseId = resultSet.getInt("warehouse_id");


                String productName = resultSet.getString("product_name");


                String productDescription = resultSet.getString("product_description");


                int quantity = resultSet.getInt("quantity");


                Long price = (long) resultSet.getDouble("price");


                products.add(new Product(productId, warehouseId, productName, productDescription, quantity, price));


            }


        } catch (SQLException e) {


            e.printStackTrace();


            throw e; // Rethrow the exception


        } finally {


            if (connection != null) {


                connection.close();


            }


        }
 
        return products;


    }


}

 
 
// package com.edutech.progressive.dao;

// import java.sql.Connection;
// import java.sql.PreparedStatement;
// import java.sql.ResultSet;
// import java.sql.SQLException;
// import java.sql.Statement;
// import java.util.ArrayList;
// import java.util.List;

// import com.edutech.progressive.config.DatabaseConnectionManager;
// import com.edutech.progressive.entity.Product;

// public class ProductDAOImpl implements ProductDAO {

//     @Override
//     public int addProduct(Product product) throws SQLException {
//         Connection connection = null;
//         PreparedStatement preparedStatement = null;
//         int generatedId = -1;
//         try {
//             connection = DatabaseConnectionManager.getConnection();
//             String query = "insert into product(warehouse_id,product_name,product_description,quantity,price) values(?,?,?,?,?)";
//             preparedStatement = connection.prepareStatement(query,PreparedStatement.RETURN_GENERATED_KEYS);
//             preparedStatement.setInt(1, product.getWarehouseId());
//             preparedStatement.setString(2, product.getProductName());
//             preparedStatement.setString(3, product.getProductDescription());
//             preparedStatement.setInt(4, product.getQuantity());
//             preparedStatement.setDouble(5, product.getPrice());
//             preparedStatement.executeUpdate();
//             ResultSet resultSet = preparedStatement.getGeneratedKeys();
//             if (resultSet.next()) {
//                 generatedId = resultSet.getInt(1);
//                 product.setProductId(generatedId);
//             }

//         } catch (SQLException e) {
//             e.printStackTrace();
//             throw e;
//         } finally {
//             if (preparedStatement != null)
//                 preparedStatement.close();
//         }

//         return generatedId;
//     }

//     @Override
//     public Product getProductById(int productId) throws SQLException {
//         Connection connection = null;
//         PreparedStatement preparedStatement = null;
//         ResultSet resultSet = null;
//         try {
//             String query = "select * from product where product_id=?";
//             connection = DatabaseConnectionManager.getConnection();
//             preparedStatement = connection.prepareStatement(query);
//             preparedStatement.setInt(1, productId);
//             resultSet = preparedStatement.executeQuery();
//             while (resultSet.next()) {
//                 int warehouse_id = resultSet.getInt("warehouse_id");
//                 String productName = resultSet.getString("product_name");
//                 String productDescription = resultSet.getString("product_description");
//                 int quantity = resultSet.getInt("quantity");
//                 Long price = (long) resultSet.getDouble("price");
//                 return new Product(productId, warehouse_id, productName, productDescription, quantity, price);
//             }
//         } catch (SQLException e) {
//             e.printStackTrace();
//             throw e;
//         } finally {
//             if (connection != null)
//                 connection.close();
//         }
//         return null;
//     }

//     @Override
//     public void updateProduct(Product product) throws SQLException {
//         Connection connection = null;
//         PreparedStatement preparedStatement = null;
//         try {
//             String query = "update product set warehouse_id=?,product_name=?,product_description=?,quantity=?,price=? where product_id=?";
//             connection = DatabaseConnectionManager.getConnection();
//             preparedStatement = connection.prepareStatement(query);
//             preparedStatement.setInt(1, product.getWarehouseId());
//             preparedStatement.setString(2, product.getProductName());
//             preparedStatement.setString(3, product.getProductDescription());
//             preparedStatement.setInt(4, product.getQuantity());
//             preparedStatement.setDouble(5, product.getPrice());
//             preparedStatement.setInt(6, product.getProductId());
//             preparedStatement.executeUpdate();
//         } catch (SQLException e) {
//             e.printStackTrace();
//             throw e;
//         } finally {
//             if (connection != null)
//                 connection.close();
//         }

//     }

//     @Override
//     public void deleteProduct(int productId) throws SQLException {
//         Connection connection = null;
//         PreparedStatement preparedStatement = null;
//         try {
//             String query = "delete from product where product_id=?";
//              connection = DatabaseConnectionManager.getConnection();
//             preparedStatement = connection.prepareStatement(query);
//             preparedStatement.setInt(1, productId);
//             preparedStatement.executeUpdate();
            
//         } catch (SQLException e) {
//             e.printStackTrace();
//             throw e;
//         }
//         finally{
//             if(connection!=null)
//                 connection.close();
//         }

//     }

//     @Override
//     public List<Product> getAllProducts() throws SQLException {
//         List<Product> list = new ArrayList<>();
//         Connection connection = null;
//         PreparedStatement preparedStatement = null;
//         ResultSet resultSet = null;
//         try {
//             String query = " select * from product";
//             connection = DatabaseConnectionManager.getConnection();
//             preparedStatement = connection.prepareStatement(query);
//             resultSet=preparedStatement.executeQuery();
//             while(resultSet.next()){
//                 int productId = resultSet.getInt("product_id");
//                 int warehouse_id = resultSet.getInt("warehouse_id");
//                 String productName = resultSet.getString("product_name");
//                 String productDescription = resultSet.getString("product_description");
//                 int quantity = resultSet.getInt("quantity");
//                 Long price = (long) resultSet.getDouble("price");
//                 list.add(new Product(productId, warehouse_id, productName, productDescription, quantity, price));
//             }
//         } catch (SQLException e) {
//             e.printStackTrace();
//             throw e;
//         }
//         finally{
//             if(connection!=null)
//                 connection.close();
//         }
//         return list;
//     }

// }
