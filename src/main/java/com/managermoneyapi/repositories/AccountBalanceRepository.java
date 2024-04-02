package com.managermoneyapi.repositories;

import com.managermoneyapi.entity.AccountBalance;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface AccountBalanceRepository extends JpaRepository<AccountBalance, Long> {
    @Query("SELECT a FROM AccountBalance a WHERE a.account.id = ?1")
    public Optional<AccountBalance> findByAccountId(Long accountId);

}
