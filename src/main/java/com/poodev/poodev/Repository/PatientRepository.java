package com.poodev.poodev.Repository;

import com.poodev.poodev.Entity.Patient;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface PatientRepository extends JpaRepository<Patient, Long> {
    Patient findByNameIgnoreCase(String name);
}