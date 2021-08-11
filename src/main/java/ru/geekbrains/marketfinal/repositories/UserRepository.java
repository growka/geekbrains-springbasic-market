package ru.geekbrains.marketfinal.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import ru.geekbrains.marketfinal.entities.User;


public interface UserRepository extends JpaRepository<User, Integer> {

    User findByLogin(String login);

}
