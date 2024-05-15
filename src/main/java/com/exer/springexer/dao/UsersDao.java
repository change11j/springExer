package com.exer.springexer.dao;

import com.exer.springexer.entity.Users;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;


@Repository
public interface UsersDao extends JpaRepository<Users,Integer> {
    boolean existsByMail(String email);

    Users findByMail(String email);

}
