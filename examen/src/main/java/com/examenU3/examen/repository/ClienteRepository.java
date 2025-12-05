package com.examenU3.examen.repository;

import com.examenU3.examen.model.Cliente;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface ClienteRepository extends JpaRepository<Cliente,Integer> {

    boolean existsByEmail(String email);


}
