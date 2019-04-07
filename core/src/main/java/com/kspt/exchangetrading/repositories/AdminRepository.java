package com.kspt.exchangetrading.repositories;

import com.kspt.exchangetrading.models.actors.Admin;
import org.springframework.data.jpa.repository.JpaRepository;

public interface AdminRepository extends JpaRepository<Admin, Long> {


}
