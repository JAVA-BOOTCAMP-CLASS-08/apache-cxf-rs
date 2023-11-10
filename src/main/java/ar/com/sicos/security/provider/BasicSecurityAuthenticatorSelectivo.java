package ar.com.sicos.security.provider;

import ar.com.sicos.security.AuthBasic;
import jakarta.ws.rs.ext.Provider;
import org.apache.cxf.configuration.security.AuthorizationPolicy;
import org.apache.cxf.jaxrs.impl.ContainerRequestContextImpl;
import org.apache.cxf.jaxrs.model.OperationResourceInfo;
import org.apache.cxf.message.Message;

import jakarta.ws.rs.container.ContainerRequestContext;
import java.io.IOException;

@Provider
public class BasicSecurityAuthenticatorSelectivo extends BasicSecurityAuthenticator {

	@Override
	public void filter(ContainerRequestContext requestContext) throws IOException {
		
		Message message = ((ContainerRequestContextImpl)requestContext).getMessage();		
		
		if (message != null) {
			/**
			 * Solo para demostrar que, cuando se env�a el header de autorizacion, CXF ya lo recibe decodificado
			 */
			
			AuthorizationPolicy policy = message.get(AuthorizationPolicy.class);
			
			if (policy != null) {
				System.out.println("[AUTHORIZATION TYPE] " + policy.getAuthorizationType());
				System.out.println("[AUTHORIZATION USER] " + policy.getUserName());
				System.out.println("[AUTHORIZATION PASSWORD] " + policy.getPassword());
			}
			
			/**
			 * ------------------------------
			 */
			
			OperationResourceInfo operInfo = message.getExchange().get(OperationResourceInfo.class);
			
			if (operInfo != null) {
				if (operInfo.getAnnotatedMethod().isAnnotationPresent(AuthBasic.class)) {
					super.filter(requestContext);
				}
				
			}
		}
    }
   
}
