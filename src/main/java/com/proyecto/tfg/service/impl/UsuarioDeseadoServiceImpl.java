package com.proyecto.tfg.service.impl;

import com.proyecto.tfg.model.UsuarioDeseado;
import com.proyecto.tfg.repository.UsuarioDeseadoRepository;
import com.proyecto.tfg.service.IUsuarioDeseadoService;
import jakarta.transaction.Transactional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.stereotype.Service;

@Service
@Transactional
public class UsuarioDeseadoServiceImpl implements IUsuarioDeseadoService {

    @Autowired
    private UsuarioDeseadoRepository usuarioDeseadoRepository;

    @Override
    public String toggleDeseado(int idCliente, int idEvento) {
        try {
            // Verifica primero si ya existe
            boolean existe = usuarioDeseadoRepository.existsByIdClienteAndIdEvento(idCliente, idEvento);

            if (existe) {
                // Si existe, eliminar
                usuarioDeseadoRepository.deleteByIdClienteAndIdEvento(idCliente, idEvento);
                return "Evento eliminado de tu lista de deseados";
            }

            // Si no existe, agregar
            UsuarioDeseado deseado = new UsuarioDeseado();
            deseado.setIdCliente(idCliente);
            deseado.setIdEvento(idEvento);

            usuarioDeseadoRepository.save(deseado);
            return "Evento añadido a tu lista de deseados";

        } catch (DataIntegrityViolationException e) {
            // Si por alguna razón salta un duplicado, lo atrapamos
            return "Este evento ya está marcado como deseado";
        }
    }


    @Override
    public boolean esDeseado(int idCliente, int idEvento) {
        return usuarioDeseadoRepository.existsByIdClienteAndIdEvento(idCliente, idEvento);
    }

    @Override
    public void eliminarDeseado(int idCliente, int idEvento) {
        usuarioDeseadoRepository.deleteByIdClienteAndIdEvento(idCliente, idEvento);
    }
}
