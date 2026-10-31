package com.poodev.poodev.Mapper;

import com.poodev.poodev.DTO.Response.SurgeryResponse;
import com.poodev.poodev.Entity.Surgery;
import org.springframework.stereotype.Component;

@Component
public class SurgeryMapper {

    public SurgeryResponse toResponse(Surgery surgery) {
        return new SurgeryResponse(
                surgery.getId(),
                surgery.getProcedureName(),
                surgery.getSurgeryDate(),
                surgery.getNotes()
        );
    }
}