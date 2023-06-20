package com.rick.bookStore.repositories;

import com.rick.bookStore.model.Permission;
import org.springframework.data.jpa.repository.JpaRepository;

public interface PermissionRepository extends JpaRepository<Permission, Long> {
}
