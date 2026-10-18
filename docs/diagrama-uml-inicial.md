# Diagrama UML Inicial

```mermaid
classDiagram
    class Doctor {
        +Long id
        +String username
        +String password
        +boolean active
        +String fullName
        +String medicalLicense
        +String specialty
        +String professionalEmail
        +LocalDateTime createdAt
        +LocalDateTime updatedAt
    }
    class Patient {
        +Long id
        +String cpf
        +String name
        +LocalDate birthDate
        +boolean fallRisk
        +String gender
        +String healthInsurance
        +String phone
    }

    class Consultation {
        +Long id
        +LocalDateTime consultationDateTime
        +String chiefComplaint
        +String diagnosticHypothesis
        +String clinicalPlan
    }

    class VitalSigns {
        +Long id
        +String bloodPressure
        +BigDecimal weight
        +BigDecimal bodyTemperature
    }
    class Allergy {
        +Long id
        +String allergen
        +boolean active
    }
    class ContinuousMedication {
        +Long id
        +String medicationName
        +String dosage
        +String frequency
        +boolean active
    }
    class Surgery {
        +Long id
        +String procedureName
        +LocalDate surgeryDate
        +String notes
    }

    Doctor "1" --> "0..*" Consultation : performs
    Patient "1" --> "0..*" Consultation : has
    Patient "1" --> "0..*" Allergy : has
    Consultation "1" --> "0..1" VitalSigns : has
    Patient "1" --> "0..*" ContinuousMedication : uses
    Patient "1" --> "0..*" Surgery : underwent
```

## Termos clínicos

- `chiefComplaint`: queixa principal.
- `diagnosticHypothesis`: hipótese diagnóstica.
- `clinicalPlan`: conduta clínica.
- `bloodPressure`: pressão arterial.
- `bodyTemperature`: temperatura corporal.
- `fallRisk`: risco de queda.

## Relações

```text
Patient 1 ──── N Consultation
Doctor  1 ──── N Consultation
Consultation 1 ──── 1 VitalSigns
Patient 1 ──── N Allergy
Patient 1 ──── N ContinuousMedication
Patient 1 ──── N Surgery
```