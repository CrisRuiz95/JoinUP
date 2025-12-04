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

    // 🔐 BCrypt para encriptar y comprobar contraseñas
    private final BCryptPasswordEncoder passwordEncoder = new BCryptPasswordEncoder();
    @Autowired
    private UsuariosRepository repo;

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

        newUsuario.setIntV1(usuario.getIntV1());
        newUsuario.setIntV2(usuario.getIntV2());
        newUsuario.setIntV3(usuario.getIntV3());

        newUsuario.setImagen(usuario.getImagen());
        // Rol por defecto
        if (usuario.getRol() == null) {
            newUsuario.setRol(Rol.GRATUITO);
        } else {
            newUsuario.setRol(usuario.getRol());
        }


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
        return repo.findById(usuario.getIdCliente()).map(existing -> {

            // Datos personales
            if (usuario.getNombre() != null)
                existing.setNombre(usuario.getNombre());

            if (usuario.getAp1() != null)
                existing.setAp1(usuario.getAp1());

            if (usuario.getAp2() != null)
                existing.setAp2(usuario.getAp2());

            if (usuario.getEmail() != null)
                existing.setEmail(usuario.getEmail());

            // Password: si viene, se encripta (igual que en createNewAccount)
            if (usuario.getPassword() != null && !usuario.getPassword().isBlank()) {
                existing.setPassword(passwordEncoder.encode(usuario.getPassword()));
            }

            // Dirección
            if (usuario.getTipoVia() != null)
                existing.setTipoVia(usuario.getTipoVia());

            if (usuario.getVia() != null)
                existing.setVia(usuario.getVia());

            if (usuario.getNumVia() != null)
                existing.setNumVia(usuario.getNumVia());

            if (usuario.getPiso() != null)
                existing.setPiso(usuario.getPiso());

            if (usuario.getPuerta() != null)
                existing.setPuerta(usuario.getPuerta());

            if (usuario.getCodigoPostal() != null)
                existing.setCodigoPostal(usuario.getCodigoPostal());

            if (usuario.getProvincia() != null)
                existing.setProvincia(usuario.getProvincia());

            if (usuario.getPoblacion() != null)
                existing.setPoblacion(usuario.getPoblacion());

            if (usuario.getInfoExtra() != null)
                existing.setInfoExtra(usuario.getInfoExtra());

            // Interiores
            if (usuario.getIntV1() != null)
                existing.setIntV1(usuario.getIntV1());

            if (usuario.getIntV2() != null)
                existing.setIntV2(usuario.getIntV2());

            if (usuario.getIntV3() != null)
                existing.setIntV3(usuario.getIntV3());

            // Imagen
            if (usuario.getImagen() != null)
                existing.setImagen(usuario.getImagen());

            // Rol (solo si viene, no se modifica automáticamente)
            if (usuario.getRol() != null)
                existing.setRol(usuario.getRol());

            repo.save(existing);
            return true;

        }).orElse(false);
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
