package com.esg.esg_monitor.services;

import com.esg.esg_monitor.model.Empresa;
import com.esg.esg_monitor.repository.EmpresaRepository;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class EmpresaService {

    private final EmpresaRepository empresaRepository;

    private Empresa empresa;

    public EmpresaService(EmpresaRepository empresaRepository) {
        this.empresaRepository = empresaRepository;
    }

    public List<Empresa> listarTodas() {
        return empresaRepository.findAll();
    }

    public Empresa salvar(Empresa empresa) {
        return empresaRepository.save(empresa);
    }

    public Empresa atualizar(Long id, Empresa empresaAtualizada) {
        return empresaRepository.findById(id)
                .map(empresaExistente -> {
                    empresaExistente.setNome(empresaAtualizada.getNome());
                    empresaExistente.setCnpj(empresaAtualizada.getCnpj());
                    empresaExistente.setEndereco(empresaAtualizada.getEndereco());
                    return empresaRepository.save(empresaExistente);
                })
                .orElseThrow(() -> new RuntimeException("Empresa não encontrada com id: " + id));
    }

    public void deletar(Long id) {
        empresaRepository.deleteById(id);
    }
}

