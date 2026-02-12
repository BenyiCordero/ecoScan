package ecoscan.controller;

import ecoscan.dao.UsuarioDAO;
import ecoscan.model.Rol;
import ecoscan.model.Usuario;
import ecoscan.model.dto.LoginRequest;
import ecoscan.model.dto.RegisterRequest;
import ecoscan.security.JwtUtil;
import ecoscan.security.PasswordUtil;

/**
 *
 * @author Benyi Uriel
 */
public class UsuarioController {
    
    private static UsuarioDAO dao = new UsuarioDAO();
    
    public String register (RegisterRequest registerRequest) throws Exception {
        
        if(dao.findByEmail(registerRequest.email()) != null){
            throw new Exception("El usuario ya existe");
        }
        
        Usuario usuario = new Usuario(
                registerRequest.nombre(),
                registerRequest.primerApellido(),
                registerRequest.segundoApellido(),
                Rol.ADMINISTRADOR,
                registerRequest.email(),
                PasswordUtil.hash(registerRequest.password())
        );
        
        dao.register(usuario);
        
        return "Usuario registrado correctamente";
    }
    
    public String login(LoginRequest loginRequest) throws Exception {
        
        Usuario usuario = dao.findByEmail(loginRequest.email());
        
        if(usuario == null){
            throw new Exception("Usuario no encontrado");
        }
        
        if(!PasswordUtil.verify(loginRequest.password(), usuario.getPassword())){
            throw new Exception("Conteaseña incorrecta");
        }
        
        return JwtUtil.generarToken(
                usuario.getIdUsuario(),
                usuario.getEmail(),
                usuario.getRol()
        );
    }
    
    public Usuario getByEmail(String email) throws Exception {
        Usuario usuario = dao.findByEmail(email);
        
        if(usuario == null){
            throw new Exception("Usuario no encontrado");
        }
        
        return usuario;
    }
    
}
