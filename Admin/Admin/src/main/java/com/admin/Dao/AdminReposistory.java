package com.admin.Dao;

import com.admin.entity.Admin;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface AdminReposistory extends JpaRepository<Admin,Integer> {
    Admin findByAdminId(Integer adminId);

    Admin findByEmailId(String tempEmailId);
}
