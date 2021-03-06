package com.hunguyen.spring_app.repository;

import com.hunguyen.spring_app.domain.Account;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
@Repository
public interface AccountRepository extends JpaRepository<Account, String> {
    
}
