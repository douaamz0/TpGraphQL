package org.example.tpgraphql.repository;

import org.example.tpgraphql.entities.Centre;
import org.springframework.data.jpa.repository.JpaRepository;

public interface CentreRepository extends JpaRepository<Centre,Long> {
}
