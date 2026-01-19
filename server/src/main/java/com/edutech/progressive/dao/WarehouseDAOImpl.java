package com.edutech.progressive.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import com.edutech.progressive.config.DatabaseConnectionManager;
import com.edutech.progressive.entity.Warehouse;

public class WarehouseDAOImpl implements WarehouseDAO {
 
    @Override


    public int addWarehouse(Warehouse warehouse) throws SQLException {


        Connection connection = null;


        PreparedStatement statement = null;


        int generatedID = -1;
 
        try {


            connection = DatabaseConnectionManager.getConnection();


            String sql = "INSERT INTO warehouse (supplier_id, warehouse_name, location, capacity) VALUES (?, ?, ?, ?)";


            statement = connection.prepareStatement(sql, PreparedStatement.RETURN_GENERATED_KEYS);


            statement.setInt(1, warehouse.getSupplierId());


            statement.setString(2, warehouse.getWarehouseName());


            statement.setString(3, warehouse.getLocation());


            statement.setDouble(4, warehouse.getCapacity());


            statement.executeUpdate();
 
            ResultSet resultSet = statement.getGeneratedKeys();


            if (resultSet.next()) {


                generatedID = resultSet.getInt(1);


                warehouse.setWarehouseId(generatedID);


            }


        } catch (SQLException e) {


            e.printStackTrace();


            throw e;


        } finally {


            if (statement != null) {


                statement.close();


            }


        }


        return generatedID;


    }
 
    @Override


    public Warehouse getWarehouseById(int warehouseId) throws SQLException {


        Connection connection = null;


        PreparedStatement statement = null;


        ResultSet resultSet = null;
 
        try {


            connection = DatabaseConnectionManager.getConnection();


            String sql = "SELECT * FROM warehouse WHERE warehouse_id = ?";


            statement = connection.prepareStatement(sql);


            statement.setInt(1, warehouseId);


            resultSet = statement.executeQuery();
 
            if (resultSet.next()) {


                int supplierId = resultSet.getInt("supplier_id");


                String warehouseName = resultSet.getString("warehouse_name");


                String location = resultSet.getString("location");


                int capacity = resultSet.getInt("capacity");


                return new Warehouse(warehouseId, supplierId, warehouseName, location, capacity);


            }


        } catch (SQLException e) {


            e.printStackTrace();


            throw e;


        } finally {


            if (connection != null) {


                connection.close();


            }


        }


        return null;


    }
 
    @Override


    public void updateWarehouse(Warehouse warehouse) throws SQLException {


        Connection connection = null;


        PreparedStatement statement = null;
 
        try {


            connection = DatabaseConnectionManager.getConnection();


            String sql = "UPDATE warehouse SET supplier_id =?, warehouse_name =?, location =?, capacity =? WHERE warehouse_id = ?";


            statement = connection.prepareStatement(sql);


            statement.setInt(1, warehouse.getSupplierId());


            statement.setString(2, warehouse.getWarehouseName());


            statement.setString(3, warehouse.getLocation());


            statement.setInt(4, warehouse.getCapacity());


            statement.setInt(5, warehouse.getWarehouseId());


            statement.executeUpdate();


        } catch (SQLException e) {


            e.printStackTrace();


            throw e;


        } finally {


            if (connection != null) {


                connection.close();


            }


        }


    }
 
    @Override


    public void deleteWarehouse(int warehouseId) throws SQLException {


        Connection connection = null;


        PreparedStatement statement = null;
 
        try {


            connection = DatabaseConnectionManager.getConnection();


            String sql = "DELETE FROM warehouse WHERE warehouse_id = ?";


            statement = connection.prepareStatement(sql);


            statement.setInt(1, warehouseId);


            statement.executeUpdate();


        } catch (SQLException e) {


            e.printStackTrace();


            throw e; 


        } finally {


            if (connection != null) {


                connection.close();


            }


        }


    }
 
    @Override


    public List<Warehouse> getAllWarehouse() throws SQLException {


        List<Warehouse> warehouses = new ArrayList<>();


        Connection connection = null;


        PreparedStatement statement = null;


        ResultSet resultSet = null;
 
        try {


            connection = DatabaseConnectionManager.getConnection();


            String sql = "SELECT * FROM warehouse";


            statement = connection.prepareStatement(sql);


            resultSet = statement.executeQuery();
 
            while (resultSet.next()) {


                int warehouseId = resultSet.getInt("warehouse_id");


                int supplierId = resultSet.getInt("supplier_id");


                String warehouseName = resultSet.getString("warehouse_name");


                String location = resultSet.getString("location");


                int capacity = resultSet.getInt("capacity");


                warehouses.add(new Warehouse(warehouseId, supplierId, warehouseName, location, capacity));


            }


        } catch (SQLException e) {


            e.printStackTrace();


            throw e; 


        } finally {


            if (connection != null) {


                connection.close();


            }


        }
 
        return warehouses;


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
// import com.edutech.progressive.entity.Warehouse;

// public class WarehouseDAOImpl implements WarehouseDAO {

//     @Override
//     public int addWarehouse(Warehouse warehouse)throws SQLException {
//          Connection connection = null;
//       PreparedStatement preparedStatement = null;
//       int generatedId = -1;
//       try {
//         String query = "insert into warehouse(supplier_id,warehouse_name,location,capacity) values (?,?,?,?)";
//         connection = DatabaseConnectionManager.getConnection();
//          preparedStatement = connection.prepareStatement(query,Statement.RETURN_GENERATED_KEYS);
//          preparedStatement.setInt(1,warehouse.getSupplierId());
//          preparedStatement.setString(2, warehouse.getWarehouseName());
//          preparedStatement.setString(3, warehouse.getLocation());
//          preparedStatement.setInt(4, warehouse.getCapacity());
//          preparedStatement.executeUpdate();
//          ResultSet resultSet = preparedStatement.getGeneratedKeys();
//          if(resultSet.next()){
//             generatedId = resultSet.getInt(1);
//             warehouse.setWarehouseId(generatedId);
//          }
//       } catch (SQLException e) {
//         e.printStackTrace();
//         throw e;
//       }
//       finally{
//         if(preparedStatement!=null)
//             preparedStatement.close();
//       }
//       return generatedId;
//     }

//     @Override
//     public Warehouse getWarehouseById(int warehouseId)throws SQLException {
//        Connection connection = null;
//       PreparedStatement preparedStatement = null;
//       ResultSet resultSet = null;
//       try {
//         connection = DatabaseConnectionManager.getConnection();
//         String query = "select * from warehouse where warehouse_id=?";
//         preparedStatement=connection.prepareStatement(query);
//         preparedStatement.setInt(1, warehouseId);
//         resultSet = preparedStatement.executeQuery();
//         if(resultSet.next()){
//             int supplier_id = resultSet.getInt("supplier_id");
//             String name = resultSet.getString("warehouse_name");
//             String location = resultSet.getString("location");
//             int capacity = resultSet.getInt("capacity");
//             return new Warehouse(warehouseId, supplier_id, name, location, capacity);
//         }
//       } catch (SQLException e) {
//         e.printStackTrace();
//         throw e;
//       }
//       finally{
//         if(connection!=null)
//             connection.close();
//       }
//       return null;
//     }

//     @Override
//     public void updateWarehouse(Warehouse warehouse) throws SQLException{
//         Connection connection = null;
//       PreparedStatement preparedStatement = null;
//       try {
//         String query = "update warehouse set supplier_id=?,warehouse_name=?,location=?,capacity=? where warehouse_id=?";
//         connection = DatabaseConnectionManager.getConnection();
//          preparedStatement=connection.prepareStatement(query);
//          preparedStatement.setInt(1, warehouse.getSupplierId());
//          preparedStatement.setString(2,warehouse.getWarehouseName());
//          preparedStatement.setString(3,warehouse.getLocation());
//          preparedStatement.setInt(4, warehouse.getCapacity());
//          preparedStatement.setInt(5, warehouse.getWarehouseId());
//          preparedStatement.executeUpdate();
//       } catch (SQLException e) {
//         e.printStackTrace();
//         throw e;
//       }
//       finally{
//         if(connection!=null)
//             connection.close();
//       }
//     }

//     @Override
//     public void deleteWarehouse(int warehouseId)throws SQLException {
//         Connection connection = null;
//       PreparedStatement preparedStatement = null;
//       try {
//         String query = "delete from warehouse where warehouse_id=?";
//          connection = DatabaseConnectionManager.getConnection();
//          preparedStatement=connection.prepareStatement(query);
//          preparedStatement.setInt(1, warehouseId);
//          preparedStatement.executeUpdate();
//       } catch (SQLException e) {
//         e.printStackTrace();
//         throw e;
//       }
//       finally{
//         if(connection!=null)
//             connection.close();
//       }
//     }

//     @Override
//     public List<Warehouse> getAllWarehouse()throws SQLException {
//         List<Warehouse> list = new ArrayList<>();
//         Connection connection = null;
//       PreparedStatement preparedStatement = null;
//       ResultSet resultSet = null;
//       try {
//         String query = "select * from warehouse";
//          connection = DatabaseConnectionManager.getConnection();
//          preparedStatement=connection.prepareStatement(query);
//          resultSet = preparedStatement.executeQuery();
//          while(resultSet.next()){
//             int warehouseId = resultSet.getInt("warehouse_id");
//             int supplier_id = resultSet.getInt("supplier_id");
//             String name = resultSet.getString("warehouse_name");
//             String location = resultSet.getString("location");
//             int capacity = resultSet.getInt("capacity");
//             list.add(new Warehouse(warehouseId, supplier_id, name, location, capacity));
//          }
//       } catch (SQLException e) {
//         e.printStackTrace();
//         throw e;
//       }
//       finally{
//         if(connection!=null)
//             connection.close();
//       }
//       return list;
//     }

// }
