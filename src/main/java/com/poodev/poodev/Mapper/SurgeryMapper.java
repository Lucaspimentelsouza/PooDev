package com.poodev.poodev.Mapper;

import com.poodev.poodev.DTO.Request.SurgeryCreateRequest;
import com.poodev.poodev.DTO.Response.SurgeryResponse;
import com.poodev.poodev.Entity.Patient;
import com.poodev.poodev.Entity.Surgery;
import org.springframework.stereotype.Component;

@Component
public class SurgeryMapper {

    public Surgery toEntity(
            SurgeryCreateRequest request,
            Patient patient
    ) {
        Surgery surgery = new Surgery();

        surgery.setProcedureName(request.procedureName());
        surgery.setSurgeryDate(request.surgeryDate());
        surgery.setNotes(request.notes());
        surgery.setPatient(patient);

        return surgery;
    }

    public SurgeryResponse toResponse(Surgery surgery) {
        return new SurgeryResponse(
                surgery.getId(),
                surgery.getPatient().getId(),
                surgery.getProcedureName(),
                surgery.getSurgeryDate(),
                surgery.getNotes()
        );
    }
}