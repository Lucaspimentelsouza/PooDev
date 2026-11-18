package com.poodev.poodev.Repository;

import com.poodev.poodev.Entity.Prescription;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface PrescriptionRepository extends JpaRepository<Prescription, Long> {
    Optional<Prescription> findByConsultation_Id(Long consultationId);

    boolean existsByConsultation_Id(Long consultationId);
}