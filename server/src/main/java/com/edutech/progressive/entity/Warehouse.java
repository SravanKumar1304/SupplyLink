package com.edutech.progressive.entity;

import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;

@Entity
public class Warehouse implements Comparable<Warehouse> {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int warehouseId;

    //private int supplierId;
    @ManyToOne(cascade = CascadeType.MERGE)
    @JoinColumn(name = "supplierId")
    private Supplier supplier;
    private String warehouseName;
    private String location;
    private int capacity;

    public Warehouse() {
    }
    
    public Warehouse(int supplierId, String warehouseName, String location, int capacity) {
        this.supplier.setSupplierId(supplierId);
        this.warehouseName = warehouseName;
        this.location = location;
        this.capacity = capacity;
    }

    public Warehouse(int warehouseId, int supplierId, String warehouseName, String location, int capacity) {
        this.warehouseId = warehouseId;
        this.supplier.setSupplierId(supplierId);
        this.warehouseName = warehouseName;
        this.location = location;
        this.capacity = capacity;
    }

    

    public int getWarehouseId() {
        return warehouseId;
    }

    // public Warehouse(int warehouseId, Supplier supplier, String warehouseName, String location, int capacity) {
    //     this.warehouseId = warehouseId;
    //     this.supplier = supplier;
    //     this.warehouseName = warehouseName;
    //     this.location = location;
    //     this.capacity = capacity;
    // }

    public void setWarehouseId(int warehouseId) {
        this.warehouseId = warehouseId;
    }

    
    // public int getSupplierId() {
    //     return supplierId;
    // }

    // public void setSupplierId(int supplierId) {
    //     this.supplierId = supplierId;
    // }

    public String getWarehouseName() {
        return warehouseName;
    }

    public void setWarehouseName(String warehouseName) {
        this.warehouseName = warehouseName;
    }

    public String getLocation() {
        return location;
    }

    public void setLocation(String location) {
        this.location = location;
    }

    public int getCapacity() {
        return capacity;
    }

    public void setCapacity(int capacity) {
        this.capacity = capacity;
    }

    @Override
    public int compareTo(Warehouse otherWarehouse) {
        // Implement comparison logic based on warehouse capacity
        return Double.compare(otherWarehouse.getCapacity(), this.getCapacity());
    }

    public Supplier getSupplier() {
        return supplier;
    }

    public void setSupplier(Supplier supplier) {
        this.supplier = supplier;
    }
}


// package com.edutech.progressive.entity;
// public class Warehouse implements Comparable<Warehouse> {
// private int warehouseId;
// private int supplierId;
// private String warehouseName;
// private String location;
// private int capacity;
// public Warehouse() {
// }
// public Warehouse(int warehouseId, int supplierId, String warehouseName,
// String location, int capacity) {
// this.warehouseId = warehouseId;
// this.supplierId = supplierId;
// this.warehouseName = warehouseName;
// this.location = location;
// this.capacity = capacity;
// }
// public Warehouse(int supplierId, String warehouseName, String location, int
// capacity) {
// this.supplierId = supplierId;
// this.warehouseName = warehouseName;
// this.location = location;
// this.capacity = capacity;
// }
// public Warehouse(String warehouseName, String location, int capacity) {
// this.warehouseName = warehouseName;
// this.location = location;
// this.capacity = capacity;
// }
// public int getWarehouseId() {
// return warehouseId;
// }
// public void setWarehouseId(int warehouseId) {
// this.warehouseId = warehouseId;
// }
// public int getSupplierId() {
// return supplierId;
// }
// public void setSupplier(int supplierId) {
// this.supplierId = supplierId;
// }
// public String getWarehouseName() {
// return warehouseName;
// }
// public void setWarehouseName(String warehouseName) {
// this.warehouseName = warehouseName;
// }
// public String getLocation() {
// return location;
// }
// public void setLocation(String location) {
// this.location = location;
// }
// public int getCapacity() {
// return capacity;
// }
// public void setCapacity(int capacity) {
// this.capacity = capacity;
// }
// @Override
// public int compareTo(Warehouse o) {
// return Double.compare( o.getCapacity(),this.getCapacity());
// }
// }
// package com.edutech.progressive.entity;
// public class Warehouse implements Comparable<Warehouse>{
// private int warehouseId;
// private int supplierId;
// private String warehouseName;
// private String location;
// private int capacity;
// public Warehouse() {
// }
// public Warehouse(int warehouseId, int supplierId, String warehouseName,
// String location, int capacity) {
// this.warehouseId = warehouseId;
// this.supplierId = supplierId;
// this.warehouseName = warehouseName;
// this.location = location;
// this.capacity = capacity;
// }
// public int getWarehouseId() {
// return warehouseId;
// }
// public void setWarehouseId(int warehouseId) {
// this.warehouseId = warehouseId;
// }
// public int getSupplierId() {
// return supplierId;
// }
// public void setSupplierId(int supplierId) {
// this.supplierId = supplierId;
// }
// public String getWarehouseName() {
// return warehouseName;
// }
// public void setWarehouseName(String warehouseName) {
// this.warehouseName = warehouseName;
// }
// public String getLocation() {
// return location;
// }
// public void setLocation(String location) {
// this.location = location;
// }
// public int getCapacity() {
// return capacity;
// }
// public void setCapacity(int capacity) {
// this.capacity = capacity;
// }
// @Override
// public int compareTo(Warehouse o) {
// return -Integer.compare(this.capacity,o.capacity );
// }
// }