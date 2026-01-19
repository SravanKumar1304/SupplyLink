package com.edutech.progressive.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import com.edutech.progressive.config.DatabaseConnectionManager;
import com.edutech.progressive.entity.Supplier;

public class SupplierDAOImpl implements SupplierDAO {
 
 
    @Override


    public int addSupplier(Supplier supplier) throws SQLException {


        Connection connection = null;


        PreparedStatement statement = null;


        int generatedID = -1;
 
        try {


            connection = DatabaseConnectionManager.getConnection();


            String sql = "INSERT INTO supplier (supplier_name, email, phone, username, password, address, role) VALUES (?, ?, ?, ?, ?, ?, ?)";


            statement = connection.prepareStatement(sql, PreparedStatement.RETURN_GENERATED_KEYS);


            statement.setString(1, supplier.getSupplierName());


            statement.setString(2, supplier.getEmail());


            statement.setString(3, supplier.getPhone());


            statement.setString(4, supplier.getUsername());


            statement.setString(5, supplier.getPassword());


            statement.setString(6, supplier.getAddress());


            statement.setString(7, supplier.getRole());


            statement.executeUpdate();
 
            ResultSet resultSet = statement.getGeneratedKeys();


            if (resultSet.next()) {


                generatedID = resultSet.getInt(1);


                supplier.setSupplierId(generatedID);


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


    public Supplier getSupplierById(int supplierId) throws SQLException {


        Connection connection = null;


        PreparedStatement statement = null;


        ResultSet resultSet = null;
 
        try {


            connection = DatabaseConnectionManager.getConnection();


            String sql = "SELECT * FROM supplier WHERE supplier_id = ?";


            statement = connection.prepareStatement(sql);


            statement.setInt(1, supplierId);


            resultSet = statement.executeQuery();
 
            if (resultSet.next()) {


                String supplierName = resultSet.getString("supplier_name");


                String email = resultSet.getString("email");


                String phone = resultSet.getString("phone");


                String username = resultSet.getString("username");


                String password = resultSet.getString("password");


                String address = resultSet.getString("address");


                String role = resultSet.getString("role");


                return new Supplier(supplierId, supplierName, email, phone, username, password, address, role);


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


    public void updateSupplier(Supplier supplier) throws SQLException {


        Connection connection = null;


        PreparedStatement statement = null;
 
        try {


            connection = DatabaseConnectionManager.getConnection();


            String sql = "UPDATE supplier SET supplier_name =?, email =?, phone =?, username =?, password =?, address =?, role =? WHERE supplier_id = ?";


            statement = connection.prepareStatement(sql);


            statement.setString(1, supplier.getSupplierName());


            statement.setString(2, supplier.getEmail());


            statement.setString(3, supplier.getPhone());


            statement.setString(4, supplier.getUsername());


            statement.setString(5, supplier.getPassword());


            statement.setString(6, supplier.getAddress());


            statement.setString(7, supplier.getRole());


            statement.setInt(8, supplier.getSupplierId());


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


    public void deleteSupplier(int supplierId) throws SQLException {


        Connection connection = null;


        PreparedStatement statement = null;
 
        try {


            connection = DatabaseConnectionManager.getConnection();


            String sql = "DELETE FROM supplier WHERE supplier_id = ?";


            statement = connection.prepareStatement(sql);


            statement.setInt(1, supplierId);


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


    public List<Supplier> getAllSuppliers() throws SQLException {


        List<Supplier> suppliers = new ArrayList<>();


        Connection connection = null;


        PreparedStatement statement = null;


        ResultSet resultSet = null;
 
        try {


            connection = DatabaseConnectionManager.getConnection();


            String sql = "SELECT * FROM supplier";


            statement = connection.prepareStatement(sql);


            resultSet = statement.executeQuery();
 
            while (resultSet.next()) {


                int supplierId = resultSet.getInt("supplier_id");


                String supplierName = resultSet.getString("supplier_name");


                String email = resultSet.getString("email");


                String phone = resultSet.getString("phone");


                String username = resultSet.getString("username");


                String password = resultSet.getString("password");


                String address = resultSet.getString("address");


                String role = resultSet.getString("role");


                suppliers.add(new Supplier(supplierId, supplierName, email, phone, username, password, address, role));


            }


        } catch (SQLException e) {


            e.printStackTrace();


            throw e; // Rethrow the exception


        } finally {


            if (connection != null) {


                connection.close();


            }


        }
 
        return suppliers;


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
// import com.edutech.progressive.entity.Supplier;

// public class SupplierDAOImpl implements SupplierDAO {

//    // @Override
//    // public int addSupplier(Supplier supplier) throws SQLException {
//    //    Connection connection = null;
//    //    PreparedStatement preparedStatement = null;
//    //    int generatedId = -1;
//    //    try {
//    //       connection = DatabaseConnectionManager.getConnection();
//    //       String query = "insert into supplier(supplier_name,email,username,password,phone,address,role) values(?,?,?,?,?,?,?)";
//    //       preparedStatement = connection.prepareStatement(query, Statement.RETURN_GENERATED_KEYS);
//    //       preparedStatement.setString(1, supplier.getSupplierName());
//    //       preparedStatement.setString(2, supplier.getEmail());
//    //       preparedStatement.setString(3, supplier.getUsername());
//    //       preparedStatement.setString(4, supplier.getPassword());
//    //       preparedStatement.setString(5, supplier.getPhone());
//    //       preparedStatement.setString(6, supplier.getAddress());
//    //       preparedStatement.setString(7, supplier.getRole());
//    //       preparedStatement.executeUpdate();
//    //       ResultSet resultSet = preparedStatement.getGeneratedKeys();
//    //       if (resultSet.next()) {
//    //          generatedId = resultSet.getInt(1);
//    //          supplier.setSupplierId(generatedId);
//    //       }
//    //    } catch (SQLException e) {
//    //       e.printStackTrace();
//    //       throw e;
//    //    } finally {
//    //       if (preparedStatement != null)
//    //          preparedStatement.close();
//    //    }
//    //    return generatedId;
//    // }

//        @Override
//     public int addSupplier(Supplier supplier) throws SQLException {
//         Connection connection = null;
//         PreparedStatement statement = null;
//         int generatedID = -1;
 
//         try {
//             connection = DatabaseConnectionManager.getConnection();
//             String sql = "INSERT INTO supplier (supplier_name, email, phone, username, password, address, role) VALUES (?, ?, ?, ?, ?, ?, ?)";
//             statement = connection.prepareStatement(sql, PreparedStatement.RETURN_GENERATED_KEYS);
//             statement.setString(1, supplier.getSupplierName());
//             statement.setString(2, supplier.getEmail());
//             statement.setString(3, supplier.getPhone());
//             statement.setString(4, supplier.getUsername());
//             statement.setString(5, supplier.getPassword());
//             statement.setString(6, supplier.getAddress());
//             statement.setString(7, supplier.getRole());
//             statement.executeUpdate();
 
//             ResultSet resultSet = statement.getGeneratedKeys();
//             if (resultSet.next()) {
//                 generatedID = resultSet.getInt(1);
//                 supplier.setSupplierId(generatedID);
//             }
//         } catch (SQLException e) {
//             e.printStackTrace();
//             throw e; // Rethrow the exception
//         } finally {
//             // Close resources in the reverse order of opening
//             if (statement != null) {
//                 statement.close();
//             }
//         }
//         return generatedID;
//     }

//    @Override
//    public Supplier getSupplierById(int supplierId) throws SQLException {
//       Connection connection = null;
//       PreparedStatement preparedStatement = null;
//       ResultSet resultSet = null;
//       try {
//          String query = "select * from supplier where supplier_id=?";
//          connection = DatabaseConnectionManager.getConnection();
//          preparedStatement = connection.prepareStatement(query);
//          preparedStatement.setInt(1, supplierId);
//          resultSet = preparedStatement.executeQuery();
//          if (resultSet.next()) {
//             String supplierName = resultSet.getString("supplier_name");
//             String username = resultSet.getString("username");
//             String password = resultSet.getString("password");
//             String email = resultSet.getString("email");
//             String phone = resultSet.getString("phone");
//             String address = resultSet.getString("address");
//             String role = resultSet.getString("role");
//             return new Supplier(supplierId, supplierName, email, phone, username, password,address, role);
//          }

//       } catch (SQLException e) {
//          e.printStackTrace();
//          throw e;
//       } finally {
//          if (connection != null)
//             connection.close();
//       }
//       return null;
//    }

//    @Override
//    public void updateSupplier(Supplier supplier) throws SQLException {
//       Connection connection = null;
//       PreparedStatement preparedStatement = null;
//       try {
//          String query = "update supplier set supplier_name=?,email=?,phone=?,username=?,password=?,address=?,role=? where supplier_id=?";
//          connection = DatabaseConnectionManager.getConnection();
//          preparedStatement = connection.prepareStatement(query);
//          preparedStatement.setString(1, supplier.getSupplierName());
//          preparedStatement.setString(2, supplier.getEmail());
//           preparedStatement.setString(3, supplier.getPhone());
//          preparedStatement.setString(4, supplier.getUsername());
//          preparedStatement.setString(5, supplier.getPassword());
//          preparedStatement.setString(6, supplier.getAddress());
//          preparedStatement.setString(7, supplier.getRole());
//          preparedStatement.setInt(8,supplier.getSupplierId());
//          preparedStatement.executeUpdate();
//       } catch (SQLException e) {
//          e.printStackTrace();
//          throw e;
//       }
//       finally{
//          if(connection!=null)
//             connection.close();
//       }
//    }

//    @Override
//    public void deleteSupplier(int supplierId) throws SQLException {
//        Connection connection = null;
//       PreparedStatement preparedStatement = null;
//       try {
//          String query = "delete from supplier where supplier_id=?";
//          connection = DatabaseConnectionManager.getConnection();
//          preparedStatement = connection.prepareStatement(query);
//          preparedStatement.setInt(1, supplierId);
//          preparedStatement.executeUpdate();
//       } catch (SQLException e) {
//          e.printStackTrace();
//          throw e;
//       }
//       finally{
//          if(connection!=null)
//             connection.close();
//       }
//    }

//    @Override
//    public List<Supplier> getAllSuppliers() throws SQLException {
//       List<Supplier> list = new ArrayList<>();
//        Connection connection = null;
//       PreparedStatement preparedStatement = null;
//       ResultSet resultSet = null;
//       try {
//          String query = "select * from supplier";
//            connection = DatabaseConnectionManager.getConnection();
//          preparedStatement = connection.prepareStatement(query);
//          resultSet = preparedStatement.executeQuery();
//          if (resultSet.next()) {
//             int supplierId = resultSet.getInt("supplier_id");
//             String supplierName = resultSet.getString("supplier_name");
//             String username = resultSet.getString("username");
//             String password = resultSet.getString("password");
//             String email = resultSet.getString("email");
//             String phone = resultSet.getString("phone");
//             String address = resultSet.getString("address");
//             String role = resultSet.getString("role");
//             list.add(new Supplier(supplierId, supplierName, email, phone,  username, password,address, role));
//          }
//       } catch (SQLException e) {
//          e.printStackTrace();
//          throw e;
//       }
//       finally{
//          if(connection!=null)
//             connection.close();
//       }
//       return list;
//    }

// }
