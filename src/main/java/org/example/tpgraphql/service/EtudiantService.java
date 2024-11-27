package org.example.tpgraphql.service;

import org.example.tpgraphql.DTO.EtudiantDTO;
import org.example.tpgraphql.entities.Centre;
import org.example.tpgraphql.entities.Etudiant;
import org.example.tpgraphql.repository.CentreRepository;
import org.example.tpgraphql.repository.EtudiantRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Sinks;

import java.util.List;

@Service
public class EtudiantService {
    @Autowired
    EtudiantRepository etudiantRepository;
    @Autowired
    CentreRepository centreRepository;
    //couler
    private final Sinks.Many<Etudiant> sink =
            Sinks.many().multicast().onBackpressureBuffer();

    public List<Etudiant> getStudents() {
        return etudiantRepository.findAll();
    }

    public Etudiant addEtudiant(EtudiantDTO etudiant) {
        Centre centre = centreRepository.findById(etudiant.centreId()).orElse(null);
        if (centre != null) {
            Etudiant et = new Etudiant();
            et.setNom(etudiant.nom());
            et.setPrenom(etudiant.prenom());
            et.setGenre(etudiant.genre());
            et.setCentre(centre);
            sink.tryEmitNext(et);
            etudiantRepository.save(et);
            sink.tryEmitNext(et);
            return et;
        }
        return null;
    }

    public Etudiant updateEtudiant(Long id, EtudiantDTO etudiant) {
        Centre centre = centreRepository.findById(etudiant.centreId()).orElse(null);
        if (centre != null)
            if (etudiantRepository.findById(id).isPresent()) {
                Etudiant et = etudiantRepository.findById(id).get();
                et.setNom(etudiant.nom());
                et.setPrenom(etudiant.prenom());
                et.setGenre(etudiant.genre());
                et.setCentre(centre);
                return etudiantRepository.save(et);
            }
        return null;
    }

    public Flux<Etudiant> getEtudiantAddedPublisher() {
        return sink.asFlux();
    }

    public String deleteEtudiant(Long id) {
        if (etudiantRepository.findById(id).isPresent()) {
            etudiantRepository.deleteById(id);
            return String.format("l'étudiant %s est bien supprimé !", id);
        }
        return String.format("l'étudiant %s n'existe pas !", id);
    }

    public Etudiant getEtudiant(Long id) {
        return etudiantRepository.findById(id).orElse(null);
    }
}
