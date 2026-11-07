package com.poodev.poodev.Repository;

import com.poodev.poodev.Entity.VitalSigns;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface VitalSignsRepository
        extends JpaRepository<VitalSigns, Long> {

    Optional<VitalSigns> findByConsultation_Id(Long consultationId);

    boolean existsByConsultation_Id(Long consultationId);
}