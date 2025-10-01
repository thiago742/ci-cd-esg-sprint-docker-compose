package com.esg.esg_monitor.services;

import com.esg.esg_monitor.model.Manutencao;
import com.esg.esg_monitor.repository.ManutencaoRepository;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class ManutencaoService {

    private final ManutencaoRepository manutencaoRepository;

    public ManutencaoService(ManutencaoRepository manutencaoRepository) {
        this.manutencaoRepository = manutencaoRepository;
    }

    public List<Manutencao> listarTodas() {
        return manutencaoRepository.findAll();
    }

    public Manutencao salvar(Manutencao manutencao) {
        return manutencaoRepository.save(manutencao);
    }

    public Manutencao atualizar(Long id, Manutencao manutencaoAtualizada) {
        return manutencaoRepository.findById(id)
                .map(manutencaoExistente -> {
                    manutencaoExistente.setDescricao(manutencaoAtualizada.getDescricao());
                    manutencaoExistente.setData(manutencaoAtualizada.getData());
                    return manutencaoRepository.save(manutencaoExistente);
                })
                .orElseThrow(() -> new RuntimeException("Manutenção não encontrada com id: " + id));
    }

    public void deletar(Long id) {
        manutencaoRepository.deleteById(id);
    }
}