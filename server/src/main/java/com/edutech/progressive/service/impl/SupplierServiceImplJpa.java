package com.edutech.progressive.service.impl;

import java.sql.SQLException;
import java.util.Collections;
import java.util.List;

import javax.transaction.Transactional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.edutech.progressive.entity.Supplier;
import com.edutech.progressive.repository.SupplierRepository;
import com.edutech.progressive.service.SupplierService;
@Service
public class SupplierServiceImplJpa implements SupplierService {

    private SupplierRepository supplierRepository;
    
    @Autowired
    public SupplierServiceImplJpa(SupplierRepository supplierRepository) {
        this.supplierRepository = supplierRepository;
    }

    @Override
    public List<Supplier> getAllSuppliers() throws SQLException {
        return supplierRepository.findAll();
    }

    @Override
    public int addSupplier(Supplier supplier) throws SQLException {
        return supplierRepository.save(supplier).getSupplierId();
    }

    @Override
    public List<Supplier> getAllSuppliersSortedByName() throws SQLException {
        List<Supplier> sortedSuppliers = supplierRepository.findAll();
        Collections.sort(sortedSuppliers);
        return sortedSuppliers;

    }

    @Override
    public void updateSupplier(Supplier supplier) throws SQLException {
        supplierRepository.save(supplier);
    }

    @Override
    @Transactional
    public void deleteSupplier(int supplierId) throws SQLException {
        supplierRepository.deleteBySupplierId(supplierId);
    }

    @Override
    public Supplier getSupplierById(int supplierId) throws SQLException {
        return supplierRepository.findBySupplierId(supplierId);
    }
}