package com.example.demo.controller;

import java.util.List;

import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.example.demo.entity.ClinicalNote;
import com.example.demo.service.ClinicalNoteService;


@RestController
@RequestMapping("/api/notes")
public class ClinicalNoteController {

	private final ClinicalNoteService noteService;

    public ClinicalNoteController(ClinicalNoteService noteService) {
        this.noteService = noteService;
    }

    @PostMapping
    public ClinicalNote create(@RequestBody ClinicalNote note) {
        return noteService.create(note);
    }

    @GetMapping
    public List<ClinicalNote> getAll() {
        return noteService.getAll();
    }

    @GetMapping("/{id}")
    public ClinicalNote getById(@PathVariable Long id) {
        return noteService.getById(id);
    }

    @GetMapping("/patient/{patientId}")
    public List<ClinicalNote> getByPatient(@PathVariable Long patientId) {
        return noteService.getByPatient(patientId);
    }

    @DeleteMapping("/{id}")
    public void delete(@PathVariable Long id) {
        noteService.delete(id);
    }
}
