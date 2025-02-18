package org.tsa.hms_backend.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import org.tsa.hms_backend.entities.Users;

@Repository
public interface UserRepository extends JpaRepository<Users, Long> {

}
