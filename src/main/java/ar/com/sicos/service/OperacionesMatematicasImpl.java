package ar.com.sicos.service;

import ar.com.sicos.exceptions.DivisionPorZeroException;
import ar.com.sicos.model.OperationInput;
import ar.com.sicos.model.OperationOutput;
import jakarta.ws.rs.core.Response;
import org.springframework.stereotype.Component;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Component
public class OperacionesMatematicasImpl implements OperacionesMatematicas {

	@Override
	public Response suma(int a, int b) {
		return Response.ok(a + b).build();
	}

	@Override
	public Response resta(int a, int b) {
		return Response.ok(a - b).build();
	}

	@Override
	public Response producto(int a, int b) {
		return Response.ok(a * b).build();
	}

	@Override
	public Response division(int a, int b) {
		return Optional.of(b)
				.filter(v -> v != 0)
				.map(v -> Response.ok(a / v).build())
				.orElseThrow(DivisionPorZeroException::new);
	}

	@Override
	public Response operation(OperationInput input) {
		return Response.ok(OperationOutput.builder()
								.input(input)
								.resultado(input.getOperation().apply(input.getValue1(), input.getValue2()))
								.build())
						.build();
	}
}

