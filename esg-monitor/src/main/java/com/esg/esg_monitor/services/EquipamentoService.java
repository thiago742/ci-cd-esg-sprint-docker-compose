package com.esg.esg_monitor.services;

import com.esg.esg_monitor.model.Equipamento;
import com.esg.esg_monitor.repository.EquipamentoRepository;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class EquipamentoService {

    private final EquipamentoRepository equipamentoRepository;

    public EquipamentoService(EquipamentoRepository equipamentoRepository) {
        this.equipamentoRepository = equipamentoRepository;
    }

    public List<Equipamento> listarTodos() {
        return equipamentoRepository.findAll();
    }

    public Equipamento salvar(Equipamento equipamento) {
        return equipamentoRepository.save(equipamento);
    }

    public Equipamento atualizar(Long id, Equipamento equipamentoAtualizado) {
        return equipamentoRepository.findById(id)
                .map(equipamentoExistente -> {
                    equipamentoExistente.setNome(equipamentoAtualizado.getNome());
                    equipamentoExistente.setTipo(equipamentoAtualizado.getTipo());
                    return equipamentoRepository.save(equipamentoExistente);
                })
                .orElseThrow(() -> new RuntimeException("Equipamento não encontrado com id: " + id));
    }

    public void deletar(Long id) {
        equipamentoRepository.deleteById(id);
    }
}