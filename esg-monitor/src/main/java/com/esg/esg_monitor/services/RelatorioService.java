package com.esg.esg_monitor.services;
import com.esg.esg_monitor.model.Relatorio;
import com.esg.esg_monitor.repository.RelatorioRepository;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class RelatorioService {

    private final RelatorioRepository relatorioRepository;

    public RelatorioService(RelatorioRepository relatorioRepository) {
        this.relatorioRepository = relatorioRepository;
    }

    public List<Relatorio> listarTodos() {
        return relatorioRepository.findAll();
    }

    public Relatorio salvar(Relatorio relatorio) {
        return relatorioRepository.save(relatorio);
    }

    public Relatorio atualizar(Long id, Relatorio relatorioAtualizado) {
        return relatorioRepository.findById(id)
                .map(relatorioExistente -> {
                    relatorioExistente.setTitulo(relatorioAtualizado.getTitulo());
                    relatorioExistente.setConteudo(relatorioAtualizado.getConteudo());
                    relatorioExistente.setData(relatorioAtualizado.getData());
                    return relatorioRepository.save(relatorioExistente);
                })
                .orElseThrow(() -> new RuntimeException("Equipamento não encontrado com id: " + id));
    }

    public void deletar(Long id) {
        relatorioRepository.deleteById(id);
    }
}