package com.example.turboboost.admin;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.example.turboboost.commons.Usuario;

@Repository
public interface AdminDAO extends JpaRepository<Usuario, Long> {

}
