package com.example.turboboost.cliente.dao;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.example.turboboost.cliente.models.Cliente;

@Repository
public interface ClienteDAO2 extends JpaRepository<Cliente, Long>{

}
