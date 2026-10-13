package com.poodev.poodev.Repository;

import com.poodev.poodev.Entity.Allergy;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
@Repository

public interface AllergyRepository extends JpaRepository<Allergy, Long> {

    List<Allergy> findByPatientIdAndActiveTrue(Long patientId); //busca alergias ativaa
}