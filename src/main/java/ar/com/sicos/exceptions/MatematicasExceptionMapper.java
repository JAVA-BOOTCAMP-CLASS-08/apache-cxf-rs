package ar.com.sicos.exceptions;

import jakarta.ws.rs.core.MediaType;
import jakarta.ws.rs.core.Response;
import jakarta.ws.rs.ext.ExceptionMapper;
import jakarta.ws.rs.ext.Provider;
import org.springframework.beans.factory.annotation.Value;

@Provider
public class MatematicasExceptionMapper implements ExceptionMapper<DivisionPorZeroException> {

    @Value("${divisionPorCero.message}")
    private String message;
    @Override
    public Response toResponse(DivisionPorZeroException exc) {
        return Response.status(Response.Status.BAD_REQUEST)
                .entity(ErrorModel.builder()
                        .code(Response.Status.BAD_REQUEST.getStatusCode())
                        .message(message)
                        .build())
                .type(MediaType.APPLICATION_JSON)
                .build();
    }
}
