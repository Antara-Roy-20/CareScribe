package com.example.demo.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

import com.example.demo.entity.ClinicalNote;

public interface ClinicalNotesRepository extends JpaRepository<ClinicalNote, Long> {

    List<ClinicalNote> findByPatientIdOrderByCreatedAtDesc(Long patientId);
}
