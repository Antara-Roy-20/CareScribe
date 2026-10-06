package com.example.demo.Service;

import java.util.List;

import org.springframework.stereotype.Service;

import com.example.demo.entity.Patient;
import com.example.demo.repository.PatientRepository;



@Service
public class PatientService {
	

	    private final PatientRepository patientRepository;

	    public PatientService(PatientRepository patientRepository) {
	        this.patientRepository = patientRepository;
	    }

	    public Patient create(Patient patient) {
	        return patientRepository.save(patient);
	    }

	    public List<Patient> getAll() {
	        return patientRepository.findAll();
	    }

	    public Patient getById(Long id) {
	        return patientRepository.findById(id)
	                .orElseThrow(() -> new RuntimeException("Patient not found: " + id));
	    }

	    public Patient update(Long id, Patient updated) {
	        Patient existing = getById(id);
	        existing.setName(updated.getName());
	        existing.setAge(updated.getAge());
	        existing.setGender(updated.getGender());
	        existing.setPhone(updated.getPhone());
	        existing.setAdmissionDate(updated.getAdmissionDate());
	        existing.setWard(updated.getWard());
	        existing.setBedNumber(updated.getBedNumber());
	        existing.setStatus(updated.getStatus());
	        return patientRepository.save(existing);
	    }

	    public void delete(Long id) {
	        patientRepository.deleteById(id);
	    }
	}


