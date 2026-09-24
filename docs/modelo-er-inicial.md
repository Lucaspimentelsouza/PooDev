# Modelo Entidade-Relacionamento Inicial

## Entidades principais

### Patient

Representa o paciente cadastrado na clínica.

Campos principais:

- `id`: identificador do paciente
- `cpf`: CPF único
- `name`: nome do paciente
- `birthDate`: data de nascimento
- `fallRisk`: indicador de risco de queda

### Consultation

Representa a consulta clínica registrada para um paciente.

Campos principais:

- `id`: identificador da consulta
- `consultationDateTime`: data e hora da consulta
- `chiefComplaint`: queixa principal
- `diagnosticHypothesis`: hipótese diagnóstica
- `clinicalPlan`: conduta clínica
- `patientId`: identificador do paciente associado

### VitalSigns

Representa os sinais vitais registrados para uma consulta.

Campos principaiis:

- `id`: identificador do registro
- `bloodPressure`: pressão arterial
- `weight`: peso corporal
- `bodyTemperature`: temperatura corporal
- `consultationId`: identificador da consulta associada

## Relacionamentos

```text
Patient 1:N Consultation
Consultation 1:1 VitalSigns
```

- Um paciente pode possuir várias consultas.
- Cada consulta pertence a um único paciente.
- Uma consulta possui um único registro de sinais vitais.
- Um registro de sinais vitais pertence a uma única consulta.

## Chaves estrangeiras

```text
consultations.patient_id → patients.id
vital_signs.consultation_id → consultations.id
```

A coluna `vital_signs.consultation_id` possui restrição de unicidade, garantindo que uma consulta não seja associada a mais de um registro de sinais vitais.