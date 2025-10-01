package com.esg.esg_monitor.repository;

import com.esg.esg_monitor.model.Manutencao;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ManutencaoRepository extends JpaRepository<Manutencao, Long> {
}