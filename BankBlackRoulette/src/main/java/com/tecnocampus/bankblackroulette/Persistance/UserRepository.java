package com.tecnocampus.bankblackroulette.Persistance;

import com.tecnocampus.bankblackroulette.Domain.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface UserRepository extends JpaRepository<User, String> {

}
