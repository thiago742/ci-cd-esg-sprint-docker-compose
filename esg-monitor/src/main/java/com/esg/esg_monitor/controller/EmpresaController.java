package com.esg.esg_monitor.controller;

import com.esg.esg_monitor.model.Empresa;
import com.esg.esg_monitor.services.EmpresaService;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/empresas")
//@RequiredArgsConstructor
public class EmpresaController {

    private final EmpresaService empresaService;

    public EmpresaController(EmpresaService empresaService) {
        this.empresaService = empresaService;
    }

    @GetMapping
    public List<Empresa> listar() {
        return empresaService.listarTodas();
    }

    @PostMapping
    public Empresa salvar(@RequestBody Empresa empresa) {
        return empresaService.salvar(empresa);
    }

    @DeleteMapping("/{id}")
    public void deletar(@PathVariable Long id) {
        empresaService.deletar(id);
    }

    @PutMapping("/{id}")
    public Empresa atualizar(@PathVariable Long id, @RequestBody Empresa empresa) {
        return empresaService.atualizar(id, empresa);
    }
}
