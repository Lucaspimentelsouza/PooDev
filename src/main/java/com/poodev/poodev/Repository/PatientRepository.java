package com.poodev.poodev.Repository;

import com.poodev.poodev.Entity.Patient;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import java.util.Optional;

@Repository
public interface PatientRepository extends JpaRepository<Patient, Long> {
    //this is send a msg to database to find a patient with medicalRecordNumber = ?
    //Optional<Patient> findBymedicalRecordNumber(String medicalRecordNumber);
}
