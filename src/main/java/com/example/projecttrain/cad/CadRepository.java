package com.example.projecttrain.cad;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface CadRepository extends JpaRepository<CadEntity, Long> {

}
