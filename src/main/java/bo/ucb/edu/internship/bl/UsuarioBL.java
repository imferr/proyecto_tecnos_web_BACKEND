package bo.ucb.edu.internship.bl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import bo.ucb.edu.internship.dao.UsuarioDAO;
import bo.ucb.edu.internship.entity.UsuarioEntity;
import java.util.List;

@Service
public class UsuarioBL {

    private final UsuarioDAO usuarioDAO;

    @Autowired
    public UsuarioBL(UsuarioDAO usuarioDAO) {
        this.usuarioDAO = usuarioDAO;
    }

    // Método para crear un usuario con validaciones de contraseña y email:
    public UsuarioEntity createUsuario(UsuarioEntity usuarioEntity) {
        if (usuarioEntity.getName() == null || usuarioEntity.getName().isEmpty()) {
            throw new RuntimeException("El nombre del usuario no puede estar vacío");
        } else if (usuarioEntity.getLastName() == null || usuarioEntity.getLastName().isEmpty()) {
            throw new RuntimeException("El apellido del usuario no puede estar vacío");
        } else if (usuarioEntity.getEmail() == null || usuarioEntity.getEmail().isEmpty()) {
            throw new RuntimeException("El correo del usuario no puede estar vacío");
        } else if (usuarioEntity.getPassword() == null || usuarioEntity.getPassword().isEmpty()) {
            throw new RuntimeException("La contraseña del usuario no puede estar vacía");
        } else if (usuarioEntity.getPhone() == null || usuarioEntity.getPhone().isEmpty()) {
            throw new RuntimeException("El teléfono del usuario no puede estar vacío");
        } else if (usuarioEntity.getAddress() == null || usuarioEntity.getAddress().isEmpty()) {
            throw new RuntimeException("La dirección del usuario no puede estar vacía");
        } else if (usuarioEntity.getCarnet() == null || usuarioEntity.getCarnet().isEmpty()) {
            throw new RuntimeException("El carné del usuario no puede estar vacío");
        } 
        // mensajede verificacion de telefono:
        else if (!usuarioEntity.getPhone().matches("[0-9]{8}")) {
            throw new RuntimeException("El telefono no es valido");
        }
        // mensaje de verificacion de correo:
        else if (!usuarioEntity.getEmail().matches("^[a-zA-Z0-9+_.-]+@[a-zA-Z0-9.-]+.$")) {
            throw new RuntimeException("El correo no es valido");
        }// Mensaje de verificación de contrasena
        else if (usuarioEntity.getPassword().length() < 8) {
            throw new RuntimeException("La contraseña debe tener más de 8 caracteres");
        }
        // mensajede verificacion de carnet:
        else if (!usuarioEntity.getCarnet().matches("[0-9]{8}")) {
            throw new RuntimeException("El carnet no es valido");
        }
        
        UsuarioEntity usuario = new UsuarioEntity();
        usuario.setName(usuarioEntity.getName());
        usuario.setLastName(usuarioEntity.getLastName());
        usuario.setEmail(usuarioEntity.getEmail());
        usuario.setPassword(usuarioEntity.getPassword());
        usuario.setPhone(usuarioEntity.getPhone());
        usuario.setAddress(usuarioEntity.getAddress());
        usuario.setCarnet(usuarioEntity.getCarnet());
    
        UsuarioEntity usuarioResult = usuarioDAO.save(usuario);
        return usuarioResult;
    }
    


    // Método para obtener una lista de todos los usuarios registrados:
    public List<UsuarioEntity> getAllUsuarios() {
        return usuarioDAO.findAll();
    }

    // Método para obtener un usuario por su ID:
    public UsuarioEntity findUsuarioById(Integer id) {
        return usuarioDAO.findById(id).orElseThrow(() -> new RuntimeException("No se encontró ningún usuario con el ID proporcionado"));
    }
}