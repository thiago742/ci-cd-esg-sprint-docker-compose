package com.esg.esg_monitor.repository;


import com.esg.esg_monitor.model.Empresa;
import org.springframework.data.jpa.repository.JpaRepository;

public interface EmpresaRepository extends JpaRepository<Empresa, Long> {

}
