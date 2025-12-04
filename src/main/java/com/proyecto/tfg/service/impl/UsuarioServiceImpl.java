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

    // BCrypt encoder for password encryption and verification
    private final BCryptPasswordEncoder passwordEncoder = new BCryptPasswordEncoder();

    @Autowired
    private UsuariosRepository repo;

    @Override
    public void createAccount(Usuario usuario) {
        // Check if user with same email already exists
        Optional<Usuario> optionalUsuario = repo.findByEmail(usuario.getEmail());
        if (optionalUsuario.isPresent()) {
            throw new UserAlreadyExistsException(
                    "Customer already registered with given email " + usuario.getEmail());
        }

        // Save user with encrypted password
        repo.save(createNewAccount(usuario));
    }

    // Helper method to create new user account with default role and encrypted password
    private Usuario createNewAccount(Usuario usuario) {
        Usuario newUsuario = new Usuario();
        newUsuario.setNombre(usuario.getNombre());
        newUsuario.setAp1(usuario.getAp1());
        newUsuario.setAp2(usuario.getAp2());
        newUsuario.setEmail(usuario.getEmail());
        newUsuario.setNumTelefono(usuario.getNumTelefono());

        // Encrypt password with BCrypt
        newUsuario.setPassword(passwordEncoder.encode(usuario.getPassword()));

        // Set address fields
        newUsuario.setTipoVia(usuario.getTipoVia());
        newUsuario.setVia(usuario.getVia());
        newUsuario.setNumVia(usuario.getNumVia());
        newUsuario.setPiso(usuario.getPiso());
        newUsuario.setPuerta(usuario.getPuerta());
        newUsuario.setCodigoPostal(usuario.getCodigoPostal());
        newUsuario.setProvincia(usuario.getProvincia());
        newUsuario.setPoblacion(usuario.getPoblacion());
        newUsuario.setInfoExtra(usuario.getInfoExtra());

        // Set additional fields
        newUsuario.setIntV1(usuario.getIntV1());
        newUsuario.setIntV2(usuario.getIntV2());
        newUsuario.setIntV3(usuario.getIntV3());
        newUsuario.setImagen(usuario.getImagen());

        // Assign default role if none provided
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

            // Update personal data if provided
            if (usuario.getNombre() != null) existing.setNombre(usuario.getNombre());
            if (usuario.getAp1() != null) existing.setAp1(usuario.getAp1());
            if (usuario.getAp2() != null) existing.setAp2(usuario.getAp2());
            if (usuario.getEmail() != null) existing.setEmail(usuario.getEmail());
            if (usuario.getNumTelefono() != null) existing.setNumTelefono(usuario.getNumTelefono());

            // Update password if provided (encrypt it)
            if (usuario.getPassword() != null && !usuario.getPassword().isBlank()) {
                existing.setPassword(passwordEncoder.encode(usuario.getPassword()));
            }

            // Update address if provided
            if (usuario.getTipoVia() != null) existing.setTipoVia(usuario.getTipoVia());
            if (usuario.getVia() != null) existing.setVia(usuario.getVia());
            if (usuario.getNumVia() != null) existing.setNumVia(usuario.getNumVia());
            if (usuario.getPiso() != null) existing.setPiso(usuario.getPiso());
            if (usuario.getPuerta() != null) existing.setPuerta(usuario.getPuerta());
            if (usuario.getCodigoPostal() != null) existing.setCodigoPostal(usuario.getCodigoPostal());
            if (usuario.getProvincia() != null) existing.setProvincia(usuario.getProvincia());
            if (usuario.getPoblacion() != null) existing.setPoblacion(usuario.getPoblacion());
            if (usuario.getInfoExtra() != null) existing.setInfoExtra(usuario.getInfoExtra());

            // Update additional fields
            if (usuario.getIntV1() != null) existing.setIntV1(usuario.getIntV1());
            if (usuario.getIntV2() != null) existing.setIntV2(usuario.getIntV2());
            if (usuario.getIntV3() != null) existing.setIntV3(usuario.getIntV3());
            if (usuario.getImagen() != null) existing.setImagen(usuario.getImagen());

            // Update role only if provided
            if (usuario.getRol() != null) existing.setRol(usuario.getRol());

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

    @Override
    public Optional<Usuario> login(String email, String password) {
        Optional<Usuario> userOpt = repo.findByEmail(email);

        if (userOpt.isEmpty()) {
            return Optional.empty(); // User not found
        }

        Usuario user = userOpt.get();

        // Compare input password with stored encrypted password
        if (passwordEncoder.matches(password, user.getPassword())) {
            return Optional.of(user); // Login successful
        }

        return Optional.empty(); // Incorrect password
    }
}
