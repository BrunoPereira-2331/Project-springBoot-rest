package com.bruno.spring.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.bruno.spring.domain.Adress;

@Repository
public interface AdressRepository extends JpaRepository<Adress, Long>{

}
