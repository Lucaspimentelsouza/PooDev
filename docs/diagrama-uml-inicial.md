# Diagrama UML Inicial

```mermaid
classDiagram
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

    Patient "1" --> "0..*" Consultation : has
    Consultation "1" --> "0..1" VitalSigns : has
```

## Termos clínicos

- `chiefComplaint`: queixa principal.
- `diagnosticHypothesis`: hipótese diagnóstica.
- `clinicalPlan`: conduta clínica.
- `bloodPressure`: pressão arterial.
- `bodyTemperature`: temperatura corporal.
- `fallRisk`: risco de queda.

## Relações

- Um `Patient` pode possuir nenhuma ou várias `Consultation`.
- Cada `Consultation` pertence a um `Patient`.
- Uma `Consultation` pode possuir no máximo um registro de `VitalSigns`.
- Cada `VitalSigns` pertence a uma única `Consultation`.