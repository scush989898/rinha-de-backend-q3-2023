package com.rinhadebackend.controller;

import com.rinhadebackend.dto.request.PessoaRequest;
import com.rinhadebackend.dto.response.PessoaResponse;
import com.rinhadebackend.service.PessoaService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.util.UriComponentsBuilder;

@RestController
@RequiredArgsConstructor
public class PessoaController {

    private final PessoaService pessoaService;

    @PostMapping("/pessoas")
    public ResponseEntity<PessoaResponse> createPessoa(@RequestBody PessoaRequest pessoaRequest, UriComponentsBuilder uriBuilder) {
            PessoaResponse pessoaResponse = pessoaService.createPessoa(pessoaRequest);

        var uri = uriBuilder.path("/pessoas/{id}").buildAndExpand(pessoaResponse.id()).toUri();

        return ResponseEntity.created(uri).body(pessoaResponse);

    }

}
