package ecoScan.config;

import jakarta.ws.rs.container.ContainerRequestContext;
import jakarta.ws.rs.container.ContainerResponseContext;
import jakarta.ws.rs.container.ContainerResponseFilter;
import jakarta.ws.rs.ext.Provider;
import java.io.IOException;

/**
 *
 * @author Benyi Uriel
 */

@Provider
public class CORSFilter implements ContainerResponseFilter{
    
    @Override
    public void filter(ContainerRequestContext crc, ContainerResponseContext crc1) throws IOException {
        crc1.getHeaders().putSingle(
                "Access-Control-Allow-Origin", "*");

        crc1.getHeaders().putSingle(
                "Access-Control-Allow-Headers",
                "origin, content-type, accept, authorization");

        crc1.getHeaders().putSingle(
                "Access-Control-Allow-Credentials", "true");

        crc1.getHeaders().putSingle(
                "Access-Control-Allow-Methods",
                "GET, POST, PUT, DELETE, OPTIONS, HEAD");
    }
}
