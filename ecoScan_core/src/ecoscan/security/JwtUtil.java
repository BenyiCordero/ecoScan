package ecoscan.security;

import ecoscan.model.Rol;
import io.jsonwebtoken.Claims;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;
import io.jsonwebtoken.security.Keys;
import java.security.Key;
import java.util.Date;

/**
 *
 * @author Benyi Uriel
 */
public class JwtUtil {
    
    private static final String SECRET_KEY = System.getenv("JWT_SECRET_KEY");
    private static final Long EXPIRATION = Long.parseLong(System.getenv("JWT_EXPIRATION"));
    
    private static Key getSignInKey() {
        return Keys.hmacShaKeyFor(SECRET_KEY.getBytes());
    }
    
    public static String generarToken(Integer idUsuario, String email, Rol rol) {
        return Jwts.builder()
                .setSubject(email)
                .claim("idUsuario", idUsuario)
                .claim("rol", rol)
                .setIssuedAt(new Date())
                .setExpiration(new Date(System.currentTimeMillis() + EXPIRATION))
                .signWith(getSignInKey(), SignatureAlgorithm.HS256)
                .compact();
    }
    
    public static Claims validarToken(String token){
        return Jwts.parserBuilder()
                .setSigningKey(getSignInKey())
                .build()
                .parseClaimsJws(token)
                .getBody();
    }
    
    public static String getEmail(String token){
        return validarToken(token).getSubject();
    }
    
    public static Integer getIdUsuario(String token) {
        return validarToken(token).get("idUsuario", Integer.class);
    }
    
    public static Rol getRol(String token){
        return validarToken(token).get("rol", Rol.class);
    }
    
}
