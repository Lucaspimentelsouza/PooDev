package com.poodev.poodev.Service;

import com.poodev.poodev.DTO.Response.CriticalAlertResponse;
import com.poodev.poodev.Entity.Allergy;
import com.poodev.poodev.Entity.Patient;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class ClinicalAlertService {

    public List<CriticalAlertResponse> buildAlerts(
            Patient patient,
            List<Allergy> activeAllergies
    ) {
        List<CriticalAlertResponse> alerts = new ArrayList<>();

        if (Boolean.TRUE.equals(patient.isFallRisk())) {
            alerts.add(new CriticalAlertResponse(
                    "FALL_RISK",
                    "Paciente com risco de queda registrado."
            ));
        }

        for (Allergy allergy : activeAllergies) {
            alerts.add(new CriticalAlertResponse(
                    "ALLERGY",
                    "Alergia a " + allergy.getAllergen()
            ));
        }

        return List.copyOf(alerts);
    }
}