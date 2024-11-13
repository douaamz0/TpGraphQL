package org.example.tpgraphql;

import org.example.tpgraphql.entities.Centre;
import org.example.tpgraphql.entities.Etudiant;
import org.example.tpgraphql.enums.Genre;
import org.example.tpgraphql.repository.CentreRepository;
import org.example.tpgraphql.repository.EtudiantRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
public class TpGraphQlApplication implements CommandLineRunner {
    @Autowired
    EtudiantRepository etudiantRepository;
    @Autowired
    CentreRepository centreRepository;
    public static void main(String[] args) {
        SpringApplication.run(TpGraphQlApplication.class, args);
    }
    @Override
    public void run(String... args) throws Exception {
        Centre centre1 = Centre.builder()
                .nom("Maarif").adresse("Biranzarane").build();
        centreRepository.save(centre1);
        Centre centre2 = Centre.builder()
                .nom("Oranges").adresse("Oulfa").build();
        centreRepository.save(centre2);
        Etudiant et1 = Etudiant.builder()
                .nom("Adnani").prenom("Brahim").genre(Genre.Homme)
                .centre(centre1).build();
        etudiantRepository.save(et1);
        Etudiant et2 = Etudiant.builder()
                .nom("Ziani").prenom("Asmaa").genre(Genre.Femme)
                .centre(centre1).build();
        etudiantRepository.save(et2);
        Etudiant et3 = Etudiant.builder()
                .nom("Ibrahimi").prenom("Hassan").genre(Genre.Homme)
                .centre(centre2).build();
        etudiantRepository.save(et3);

    }
}


