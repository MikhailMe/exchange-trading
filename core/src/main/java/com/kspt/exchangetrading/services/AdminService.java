package com.kspt.exchangetrading.services;

import com.kspt.exchangetrading.models.actors.Admin;
import com.kspt.exchangetrading.repositories.AdminRepository;
import org.jetbrains.annotations.NotNull;
import org.springframework.stereotype.Service;

@Service
public class AdminService extends AbstractService<Admin, AdminRepository> {

    public AdminService(@NotNull AdminRepository repository) {
        super(repository);
    }
}
