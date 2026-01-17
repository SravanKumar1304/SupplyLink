package com.edutech.progressive.service.impl;

import java.util.List;

import com.edutech.progressive.entity.Supplier;
import com.edutech.progressive.service.SupplierService;

public class SupplierServiceImplArraylist implements SupplierService  {

    @Override
    public List<Supplier> getAllSuppliers() {
        return List.of();
    }

    @Override
    public int addSupplier(Supplier supplier) {
        return -1;
    }

    @Override
    public List<Supplier> getAllSuppliersSortedByName() {
       return List.of();
    }

     public void emptyArrayList() {
    }

}