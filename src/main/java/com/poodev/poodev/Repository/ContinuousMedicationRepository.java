package com.poodev.poodev.Repository;

import com.poodev.poodev.Entity.ContinuousMedication;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import java.util.List;

@Repository
public interface ContinuousMedicationRepository extends JpaRepository<ContinuousMedication, Long> {
    List<ContinuousMedication> findByPatientIdAndActiveTrue(Long patientId);
}
