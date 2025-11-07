package com.proyecto.tfg.service;

import com.proyecto.tfg.exception.UserAlreadyExistsException;
import com.proyecto.tfg.model.Rol;
import com.proyecto.tfg.model.Usuario;
import com.proyecto.tfg.service.db.UsuarioServiceJpa;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class UsuarioServiceImpl implements IUsuarioService {

    @Autowired
    private UsuarioServiceJpa repo;


    @Override
    public void createAccount(Usuario usuario) {
        Optional<Usuario> optionalUsuario = repo.findByEmail(usuario.getEmail());
        if (optionalUsuario.isPresent()) {
            throw new UserAlreadyExistsException("Customer already registered with given mobileNumber " + usuario.getEmail());
        }
        repo.save(createNewAccount(usuario));
    }
    private Usuario createNewAccount(Usuario usuario) {
        Usuario newUsuario = new Usuario();
        newUsuario.setNombre(usuario.getNombre());
        newUsuario.setAp1(usuario.getAp1());
        newUsuario.setAp2(usuario.getAp2());
        newUsuario.setEmail(usuario.getEmail());       // 🔹 importante
        newUsuario.setPassword(usuario.getPassword());
        // Dirección
        newUsuario.setTipoVia(usuario.getTipoVia());
        newUsuario.setVia(usuario.getVia());
        newUsuario.setNumVia(usuario.getNumVia());
        newUsuario.setPiso(usuario.getPiso());
        newUsuario.setPuerta(usuario.getPuerta());
        newUsuario.setCodigoPostal(usuario.getCodigoPostal());
        newUsuario.setProvincia(usuario.getProvincia());
        newUsuario.setPoblacion(usuario.getPoblacion());
        newUsuario.setInfoExtra(usuario.getInfoExtra());
        // Si no viene rol, ponemos el valor por defecto
        if (usuario.getRol() == null) {
            newUsuario.setRol(Rol.GRATUITO);
        } else {
            newUsuario.setRol(usuario.getRol());
        }

        newUsuario.setInteres(usuario.getInteres());

        return newUsuario;
    }

    @Override
    public Optional<Usuario> fetchAccount(int idCliente) {
        return repo.findById(idCliente);
    }

    @Override
    public List<Usuario> findAll() {
    return repo.findAll();
    }

    @Override
    public boolean updateAccount(Usuario usuario) {
        if (repo.existsById(usuario.getIdCliente())) {
            repo.save(usuario); // save() actualiza si el ID ya existe
            return true;         // actualización exitosa
        } else {
            return false;        // no existe el usuario a actualizar
        }
    }


    @Override
    public boolean deleteAccount(int idCliente) {
        Optional<Usuario> usuarioOpt = repo.findById(idCliente);
        if (usuarioOpt.isPresent()) {
            repo.delete(usuarioOpt.get());
            return true; // Eliminación exitosa
        }
        return false; // Usuario no encontrado
    }

}