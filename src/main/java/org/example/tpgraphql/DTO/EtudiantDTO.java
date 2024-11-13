package org.example.tpgraphql.DTO;

import org.example.tpgraphql.enums.Genre;

public record EtudiantDTO (
        String nom,
        String prenom,
        Genre genre,
        Long centreId
){ }
