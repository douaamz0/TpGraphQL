package org.example.tpgraphql.mappers;

import org.example.tpgraphql.DTO.EtudiantDTO;
import org.example.tpgraphql.entities.Centre;
import org.example.tpgraphql.entities.Etudiant;
import org.example.tpgraphql.repository.CentreRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component
public class EtudiantMapper {

    @Autowired
    CentreRepository centreRepository;

    public Etudiant toEtudiant(Etudiant etudiant , EtudiantDTO etudiantDTO){
        Centre centre=centreRepository.findById(etudiantDTO.centreId()).orElse(null);
        if(centre!=null){
            etudiant.setNom(etudiantDTO.nom());
            etudiant.setPrenom(etudiantDTO.prenom());
            etudiant.setGenre(etudiantDTO.genre());
            etudiant.setCentre(centre);
        }
        return etudiant;
    }
}
