package com.example.demo.service;

import java.time.LocalDateTime;
import java.util.List;

import org.springframework.stereotype.Service;

import com.example.demo.entity.ClinicalNote;
import com.example.demo.entity.Patient;
import com.example.demo.repository.ClinicalNotesRepository;
import com.example.demo.repository.PatientRepository;

@Service
public class ClinicalNoteService {
	private final ClinicalNotesRepository noteRepository;
    private final PatientRepository patientRepository;

    public ClinicalNoteService(ClinicalNotesRepository noteRepository,
                               PatientRepository patientRepository) {
        this.noteRepository = noteRepository;
        this.patientRepository = patientRepository;
    }

    public ClinicalNote create(ClinicalNote note) {
        Long patientId = note.getPatient().getId();
        Patient patient = patientRepository.findById(patientId)
                .orElseThrow(() -> new RuntimeException("Patient not found: " + patientId));
        note.setPatient(patient);
        note.setCategory(patient.getStatus());
        note.setCreatedAt(LocalDateTime.now());
        return noteRepository.save(note);
    }

    public List<ClinicalNote> getAll() {
        return noteRepository.findAll();
    }

    public ClinicalNote getById(Long id) {
        return noteRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Note not found: " + id));
    }

    public List<ClinicalNote> getByPatient(Long patientId) {
        return noteRepository.findByPatientIdOrderByCreatedAtDesc(patientId);
    }

    public void delete(Long id) {
        noteRepository.deleteById(id);
    }

}
