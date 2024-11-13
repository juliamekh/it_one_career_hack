package com.itonehack.smartbudget.infrastructure.jpa.role;

import com.itonehack.smartbudget.domain.enums.ERole;
import com.itonehack.smartbudget.domain.model.Role;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface JpaRoleRepository extends JpaRepository<Role, Long> {
    Optional<Role> findByName(ERole name);

    @Modifying
    @Query(nativeQuery = true, value = """
                DELETE FROM user_roles;
                DELETE FROM role;
            """)
    void deleteAll();
}