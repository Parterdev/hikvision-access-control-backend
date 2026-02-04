package com.uisrael.hikvision.backend.aplicacion.casosuso.entradas;


import com.uisrael.hikvision.backend.aplicacion.dto.request.HikvisionLoginRequest;
import com.uisrael.hikvision.backend.aplicacion.dto.response.HikvisionLoginResponse;

public interface HikvisionLoginUseCase {
    HikvisionLoginResponse login(HikvisionLoginRequest request);
}