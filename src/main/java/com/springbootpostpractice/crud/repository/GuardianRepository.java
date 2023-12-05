package com.springbootpostpractice.crud.repository;

import com.springbootpostpractice.crud.model.Guardian;
import org.springframework.data.jpa.repository.JpaRepository;

public interface GuardianRepository  extends JpaRepository<Guardian, Integer> {
}
