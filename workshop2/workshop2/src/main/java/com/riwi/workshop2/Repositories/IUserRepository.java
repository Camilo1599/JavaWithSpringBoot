package com.riwi.workshop2.Repositories;

import com.riwi.workshop2.Entities.EventEntity;
import org.springframework.data.jpa.repository.JpaRepository;

public interface IUserRepository extends JpaRepository <EventEntity, String> {
}
