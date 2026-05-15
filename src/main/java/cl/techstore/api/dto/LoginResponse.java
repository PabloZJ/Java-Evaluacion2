package cl.techstore.api.dto;

import lombok.AllArgsConstructor;
import lombok.Data;

@Data
@AllArgsConstructor

public class LoginResponse {
    private String token;
    private String tipo;
    private String expiracion;
}
