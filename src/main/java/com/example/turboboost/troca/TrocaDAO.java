package com.example.turboboost.troca;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface TrocaDAO extends JpaRepository<Troca, Long>{

}
