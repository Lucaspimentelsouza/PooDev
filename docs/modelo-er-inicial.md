# Modelo Entidade-Relacionamento Inicial

## Entidades principais

### Doctor

Representa o médico autenticado que acessa a API e é responsável pelas consultas
clínicas registradas no sistema.

Campos principais:

- `id`: identificador do médico
- `username`: nome de usuário utilizado na autenticação
- `password`: senha do médico utilizada na autenticação
- `active`: indica se o médico possui acesso ativo ao sistema
- `fullName`: nome completo do médico
- `medicalLicense`: registro profissional do médico (CRM)
- `specialty`: especialidade médica principal
- `professionalEmail`: e-mail profissional
- `createdAt`: data e hora de criação do registro
- `updatedAt`: data e hora da última atualização

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
- `doctorId`: identificador do médico responsável pela consulta

### VitalSigns

Representa os sinais vitais registrados para uma consulta.

Campos principaiis:

- `id`: identificador do registro
- `bloodPressure`: pressão arterial
- `weight`: peso corporal
- `bodyTemperature`: temperatura corporal
- `consultationId`: identificador da consulta associada

### Allergy

Representa uma alergia registrada no histórico clínico do paciente.

Campos principais:

- `id`: identificador do registro de alergia
- `allergen`: substância ou agente causador da alergia
- `active`: indica se a alergia está ativa
- `patientId`: identificador do paciente associado

## Relacionamentos

```text
Doctor 1:N Consultation
Patient 1:N Consultation
Consultation 1:1 VitalSigns
Patient 1:N Allergy
```

- Um médico pode realizar várias consultas.
- Cada consulta é realizada por um único médico.
- Um paciente pode possuir várias consultas.
- Cada consulta pertence a um único paciente.
- Uma consulta pode possuir no máximo um registro de sinais vitais.
- Um registro de sinais vitais pertence a uma única consulta.
- Um paciente pode possuir várias alergias.
- Cada alergia pertence a um único paciente.

## Chaves estrangeiras

```text
consultations.doctor_id → doctors.id
consultations.patient_id → patients.id
vital_signs.consultation_id → consultations.id
allergies.patient_id → patients.id
```

A coluna `vital_signs.consultation_id` possui restrição de unicidade, garantindo que uma consulta não seja associada a mais de um registro de sinais vitais.


## Revisão de escopo após orientação dos professores

O foco da API foi refinado para atender exclusivamente a tela do médico.
Os dados administrativos e operacionais da clínica, como cadastro realizado
pela recepção e registro operacional da triagem, são considerados existentes
na base da clínica e não fazem parte do escopo de implementação desta API.

A API será responsável por consultar, consolidar e disponibilizar o histórico
clínico do paciente para uso do médico, incluindo alertas clínicos informativos.

### Entidade central de acesso

O médico é representado pela entidade `Doctor` e possui acesso autenticado
aos recursos clínicos da API.

### Relações prioritárias

```text
Doctor 1:N Consultation
Patient 1:N Consultation
Consultation 1:1 VitalSigns

Patient 1:N Allergy
Patient 1:N PreExistingCondition
Patient 1:N ContinuousMedication
Patient 1:N Surgery
```

### Resultado prioritário

O principal recurso da API será a obtenção de um resumo clínico consolidado
do paciente para exibição na tela do médico.