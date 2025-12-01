package com.proyecto.tfg.service.impl;

import com.proyecto.tfg.exception.UserAlreadyExistsException;
import com.proyecto.tfg.model.Rol;
import com.proyecto.tfg.model.Usuario;
import com.proyecto.tfg.repository.UsuariosRepository;
import com.proyecto.tfg.service.IUsuarioService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class UsuarioServiceImpl implements IUsuarioService {

    @Autowired
    private UsuariosRepository repo;

    // 🔐 BCrypt para encriptar y comprobar contraseñas
    private final BCryptPasswordEncoder passwordEncoder = new BCryptPasswordEncoder();


    @Override
    public void createAccount(Usuario usuario) {
        Optional<Usuario> optionalUsuario = repo.findByEmail(usuario.getEmail());
        if (optionalUsuario.isPresent()) {
            throw new UserAlreadyExistsException("Customer already registered with given email " + usuario.getEmail());
        }

        // Guardar usuario con password encriptada
        repo.save(createNewAccount(usuario));
    }


    private Usuario createNewAccount(Usuario usuario) {
        Usuario newUsuario = new Usuario();
        newUsuario.setNombre(usuario.getNombre());
        newUsuario.setAp1(usuario.getAp1());
        newUsuario.setAp2(usuario.getAp2());
        newUsuario.setEmail(usuario.getEmail());

        // 🔐 ENCRIPTAR PASSWORD CON BCRYPT
        newUsuario.setPassword(passwordEncoder.encode(usuario.getPassword()));

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

        // Rol por defecto
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

            // Si se actualiza password, la volvemos a encriptar
            if (usuario.getPassword() != null && !usuario.getPassword().isEmpty()) {
                usuario.setPassword(passwordEncoder.encode(usuario.getPassword()));
            }

            repo.save(usuario);
            return true;
        }
        return false;
    }


    @Override
    public boolean deleteAccount(int idCliente) {
        Optional<Usuario> usuarioOpt = repo.findById(idCliente);
        if (usuarioOpt.isPresent()) {
            repo.delete(usuarioOpt.get());
            return true;
        }
        return false;
    }


    // 🔐 Login comparando contraseña con BCrypt
    @Override
    public Optional<Usuario> login(String email, String password) {
        Optional<Usuario> userOpt = repo.findByEmail(email);

        if (userOpt.isEmpty()) {
            return Optional.empty(); // Usuario no existe
        }

        Usuario user = userOpt.get();

        // Comparar contraseña ingresada con la encriptada
        if (passwordEncoder.matches(password, user.getPassword())) {
            return Optional.of(user); // Login OK
        }

        return Optional.empty(); // Contraseña incorrecta
    }
}
