package ru.geekbrains.marketfinal.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import ru.geekbrains.marketfinal.entities.Role;


public interface RoleRepository extends JpaRepository<Role, Integer> {

    Role findByName(String name);

}
