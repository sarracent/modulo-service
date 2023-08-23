package com.inclusioncloud.moduloservice.service.impl;

import com.inclusioncloud.moduloservice.exception.impl.TechnicalException;
import com.inclusioncloud.moduloservice.service.ModuloService;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.Arrays;
import java.util.List;

import static com.inclusioncloud.moduloservice.constants.Errors.ERROR_TECHNICAL_EXCEPTION;

@Service
@AllArgsConstructor
public class ModuloServiceImpl implements ModuloService {
    @Override
    public Integer calculateMaximumK(Integer x, Integer y, Integer n) {
        List<Integer> extraInfo =  Arrays.asList(x, y, n);
        try {
            return (n - y) / x * x + y;
        } catch (Exception e) {
            throw new TechnicalException(ERROR_TECHNICAL_EXCEPTION.getCode(), String.format(e.getMessage(), extraInfo));
        }
    }
}
