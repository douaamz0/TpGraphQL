package org.example.tpgraphql.service;

import graphql.com.google.common.base.Supplier;
import org.example.tpgraphql.DTO.CentreDto;
import org.example.tpgraphql.entities.Centre;
import org.example.tpgraphql.repository.CentreRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class CentreService {
    @Autowired
    private CentreRepository centreRepository;

    public List<Centre> centres() {
        return centreRepository.findAll();
    }

    public Centre getCentre(long id) {
        return centreRepository.findById(id).orElse(null);
    }

    public void add(CentreDto centreDto) {
        Centre centre = new Centre();
        centre.setNom(centreDto.nom());
        centre.setAdresse(centreDto.adresse());
        centreRepository.save(centre);
    }

    public String delete(long id){
        Centre centre=centreRepository.findById(id).get();
        if(centre!=null){
            centreRepository.delete(centre);
            return "le centre a été supprimé avec succès !";
        }

        return "le centre n'existe pas !";

    }

    public void update(long id,CentreDto centreDto){
        Centre centre = centreRepository.findById(id).get();
        if(centre!=null){
            centre.setNom(centreDto.nom());
            centre.setAdresse(centreDto.adresse());
            centreRepository.save(centre);
        }
    }
}

