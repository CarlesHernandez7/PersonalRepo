package com.tecnocampus.bankblackroulette.Persistance;

import com.tecnocampus.bankblackroulette.Domain.Account;
import com.tecnocampus.bankblackroulette.Domain.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import java.util.List;

@Repository
public interface AccountRepository extends JpaRepository<Account, String> {
    List<Account> findAllByUser(User user);
}
