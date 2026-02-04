package com.uisrael.hikvision.backend.presentacion.controladores;

import com.uisrael.hikvision.backend.aplicacion.casosuso.entradas.HikvisionLoginUseCase;
import com.uisrael.hikvision.backend.aplicacion.dto.request.HikvisionLoginRequest;
import com.uisrael.hikvision.backend.aplicacion.dto.response.HikvisionLoginResponse;
import jakarta.validation.Valid;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/auth")
public class AuthController {

    private final HikvisionLoginUseCase loginUseCase;

    public AuthController(HikvisionLoginUseCase loginUseCase) {
        this.loginUseCase = loginUseCase;
    }

    @PostMapping("/login")
    public HikvisionLoginResponse login(@RequestBody @Valid HikvisionLoginRequest request) {
        return loginUseCase.login(request);
    }
}
