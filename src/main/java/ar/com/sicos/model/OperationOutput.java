package ar.com.sicos.model;

import lombok.Builder;
import lombok.Data;

@Data
@Builder
public class OperationOutput {

    private OperationInput input;

    private Integer resultado;
}
