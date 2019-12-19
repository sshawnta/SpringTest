package com.testregistration.testregistration;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import org.springframework.stereotype.Service;

@Service
@Repository
public interface RegistrationRepository extends JpaRepository<RegistrationBase, Long> {
	RegistrationBase findByUsername(String username);
	RegistrationBase findByName(String name);
}