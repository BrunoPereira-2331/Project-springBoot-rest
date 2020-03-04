package com.bruno.spring.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.bruno.spring.domain.State;

@Repository
public interface StateRepository extends JpaRepository<State, Long>{

}
