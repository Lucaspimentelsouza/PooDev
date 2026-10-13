# Consultas SQL dos Repositories

Este documento apresenta os métodos dos repositories e o SQL equivalente
que eles representam no banco de dados.

Os comandos SQL são equivalentes para fins de documentação. O Hibernate
pode gerar aliases, listar colunas explicitamente e executar consultas
adicionais para carregar relacionamentos.

O parâmetro `:patientId` representa o identificador do paciente recebido
pelo método Java.

---

## PatientRepository

```java
Patient findByNameIgnoreCase(String name);
```

SQL equivalente:

```sql
SELECT *
FROM patients
WHERE LOWER(name) = LOWER(:name);
```

O método busca um paciente pelo nome, ignorando diferenças entre
maiúsculas e minúsculas.

---

## ConsultationRepository

```java
List<Consultation> findByPatientIdOrderByConsultationDateTimeDesc(
        Long patientId
);
```

SQL equivalente:

```sql
SELECT *
FROM consultations
WHERE patient_id = :patientId
ORDER BY consultation_date_time DESC;
```

O método busca todas as consultas de um paciente e ordena as mais
recentes primeiro.

---

## AllergyRepository

```java
List<Allergy> findByPatientIdAndActiveTrue(Long patientId);
```

SQL equivalente:

```sql
SELECT *
FROM allergies
WHERE patient_id = :patientId
  AND active = TRUE;
```

O método busca apenas as alergias ativas de um paciente.

---

## ContinuousMedicationRepository

```java
List<ContinuousMedication> findByPatientIdAndActiveTrue(Long patientId);
```

SQL equivalente:

```sql
SELECT *
FROM continuous_medications
WHERE patient_id = :patientId
  AND active = TRUE;
```

O método busca apenas os medicamentos de uso contínuo ativos de um
paciente.

---

## SurgeryRepository

```java
List<Surgery> findByPatientIdOrderBySurgeryDateDesc(Long patientId);
```

SQL equivalente:

```sql
SELECT *
FROM surgeries
WHERE patient_id = :patientId
ORDER BY surgery_date DESC;
```

O método busca as cirurgias de um paciente e ordena as mais recentes
primeiro.