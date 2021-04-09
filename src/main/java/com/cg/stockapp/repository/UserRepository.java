package com.cg.stockapp.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.cg.stockapp.dto.User;

@Repository
public interface UserRepository extends JpaRepository<User, Long>{

}
