package ar.com.sicos.security.provider;

import org.apache.cxf.common.util.Base64Exception;
import org.apache.cxf.common.util.Base64Utility;

import jakarta.ws.rs.container.ContainerRequestContext;
import jakarta.ws.rs.container.ContainerRequestFilter;
import jakarta.ws.rs.core.Response;
import java.io.IOException;

public class BasicSecurityAuthenticator implements ContainerRequestFilter {

	@Override
	public void filter(ContainerRequestContext requestContext) throws IOException {
        String authorization = requestContext.getHeaderString("Authorization");
        
        if (authorization != null && !authorization.isEmpty()) {
        	
            String[] parts = authorization.split(" ");
            
            if (parts.length != 2 || !"Basic".equals(parts[0])) {
                requestContext.abortWith(createFaultResponse());
            } else {
             
	            String decodedValue = null;
	            try {
	                decodedValue = new String(Base64Utility.decode(parts[1]));
	
	                String[] namePassword = decodedValue.split(":"); 
	                if (isAuthenticated(namePassword[0], namePassword[1])) {
	                    System.out.println("[" + namePassword[0] + "] - Autenticado!!!");
	                } else {
	                    // la autenticacion fallo, retorno el fault con WWW-Authenticate header
	                    requestContext.abortWith(createFaultResponse());
	                }
	                
	            } catch (Base64Exception ex) {
	                requestContext.abortWith(createFaultResponse());
	            }
            }
        } else {
            requestContext.abortWith(createFaultResponse());
        }
        
    }
   
	private Response createFaultResponse() {
        return Response.status(401).header("WWW-Authenticate", "Basic realm=\"matematica\"").build();
    }
    
	private boolean isAuthenticated(String user, String password) {
		return "admin".equals(user) && "1234".equals(password);
	}
}
