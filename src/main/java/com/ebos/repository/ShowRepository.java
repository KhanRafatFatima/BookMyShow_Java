package com.ebos.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.ebos.tables.Shows;

@Repository
public interface ShowRepository extends JpaRepository<Shows, Long> {

}
