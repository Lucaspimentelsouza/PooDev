package com.poodev.poodev.Service;

import com.lowagie.text.Document;
import com.lowagie.text.DocumentException;
import com.lowagie.text.Element;
import com.lowagie.text.Font;
import com.lowagie.text.PageSize;
import com.lowagie.text.Paragraph;
import com.lowagie.text.pdf.BaseFont;
import com.lowagie.text.pdf.PdfWriter;
import com.poodev.poodev.Entity.Consultation;
import com.poodev.poodev.Entity.Doctor;
import com.poodev.poodev.Entity.Patient;
import com.poodev.poodev.Entity.Prescription;
import com.poodev.poodev.Exception.ConsultationNotFoundException;
import com.poodev.poodev.Exception.PrescriptionNotFoundException;
import com.poodev.poodev.Repository.ConsultationRepository;
import com.poodev.poodev.Repository.PrescriptionRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.time.format.DateTimeFormatter;

@Service
@RequiredArgsConstructor
public class PrescriptionPdfService {

    private static final DateTimeFormatter DATE_FORMAT =
            DateTimeFormatter.ofPattern("dd/MM/yyyy");

    private static final DateTimeFormatter DATE_TIME_FORMAT =
            DateTimeFormatter.ofPattern("dd/MM/yyyy HH:mm");

    private final ConsultationRepository consultationRepository;
    private final PrescriptionRepository prescriptionRepository;

    @Transactional(readOnly = true)
    public byte[] generate(Long consultationId) {
        if (!consultationRepository.existsById(consultationId)) {
            throw new ConsultationNotFoundException(consultationId);
        }

        Prescription prescription = prescriptionRepository
                .findByConsultation_Id(consultationId)
                .orElseThrow(() ->
                        new PrescriptionNotFoundException(consultationId)
                );

        Consultation consultation = prescription.getConsultation();
        Patient patient = consultation.getPatient();
        Doctor doctor = consultation.getDoctor();

        ByteArrayOutputStream output = new ByteArrayOutputStream();
        Document document = new Document(PageSize.A4, 50, 50, 50, 50);

        try {
            BaseFont baseFont = BaseFont.createFont(
                    BaseFont.HELVETICA,
                    BaseFont.WINANSI,
                    BaseFont.NOT_EMBEDDED
            );

            Font titleFont = new Font(baseFont, 18, Font.BOLD);
            Font headingFont = new Font(baseFont, 12, Font.BOLD);
            Font bodyFont = new Font(baseFont, 11, Font.NORMAL);
            Font noteFont = new Font(baseFont, 9, Font.ITALIC);

            PdfWriter.getInstance(document, output);

            document.addTitle("Receita - PooDev");
            document.open();

            Paragraph title = new Paragraph(
                    "RECEITA - PooDev",
                    titleFont
            );
            title.setAlignment(Element.ALIGN_CENTER);
            title.setSpacingAfter(10);
            document.add(title);

            Paragraph notice = new Paragraph(
                    "Documento acadêmico de demonstração. "
                            + "Sem assinatura digital. "
                            + "Não utilizar para dispensação de medicamentos.",
                    noteFont
            );
            notice.setSpacingAfter(18);
            document.add(notice);

            document.add(new Paragraph(
                    "Paciente: " + patient.getName(),
                    bodyFont
            ));

            if (patient.getBirthDate() != null) {
                document.add(new Paragraph(
                        "Nascimento: "
                                + patient.getBirthDate().format(DATE_FORMAT),
                        bodyFont
                ));
            }

            document.add(new Paragraph(
                    "Médico: " + doctor.getFullName(),
                    bodyFont
            ));

            document.add(new Paragraph(
                    "Registro profissional: " + doctor.getMedicalLicense(),
                    bodyFont
            ));

            document.add(new Paragraph(
                    "Data da consulta: "
                            + consultation.getConsultationDateTime()
                            .format(DATE_TIME_FORMAT),
                    bodyFont
            ));

            document.add(new Paragraph(
                    "Receita criada em: "
                            + prescription.getCreatedAt()
                            .format(DATE_TIME_FORMAT),
                    bodyFont
            ));

            document.add(new Paragraph(
                    "Última atualização: "
                            + prescription.getUpdatedAt()
                            .format(DATE_TIME_FORMAT),
                    bodyFont
            ));

            Paragraph contentHeading = new Paragraph(
                    "Prescrição",
                    headingFont
            );
            contentHeading.setSpacingBefore(20);
            contentHeading.setSpacingAfter(10);
            document.add(contentHeading);

            Paragraph content = new Paragraph(
                    prescription.getContent()
                            .replace("\r\n", "\n")
                            .replace('\r', '\n'),
                    bodyFont
            );
            content.setLeading(16);
            content.setKeepTogether(false);
            document.add(content);

            document.close();

            return output.toByteArray();

        } catch (DocumentException | IOException exception) {
            throw new IllegalStateException(
                    "Não foi possível gerar o PDF da receita.",
                    exception
            );
        } finally {
            if (document.isOpen()) {
                document.close();
            }
        }
    }
}