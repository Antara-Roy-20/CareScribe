package com.example.demo.entity;

import java.time.LocalDateTime;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.Table;


@Entity
@Table(name = "clinical_notes")
public class ClinicalNote {
	@Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @ManyToOne
    @JoinColumn(name = "patient_id", nullable = false)
    private Patient patient;

    @Column(columnDefinition = "TEXT", nullable = false)
    private String noteText;

    private String author;
    private String category;


	private LocalDateTime createdAt;

	public Long getId() {
		return id;
	}

	public Patient getPatient() {
		return patient;
	}

	public String getNoteText() {
		return noteText;
	}

	public String getAuthor() {
		return author;
	}

	public LocalDateTime getCreatedAt() {
		return createdAt;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public void setPatient(Patient patient) {
		this.patient = patient;
	}

	public void setNoteText(String noteText) {
		this.noteText = noteText;
	}

	public void setAuthor(String author) {
		this.author = author;
	}

	public void setCreatedAt(LocalDateTime createdAt) {
		this.createdAt = createdAt;
	}
    
	 public String getCategory() {
			return category;
		}

		public void setCategory(String category) {
			this.category = category;
		}

}


