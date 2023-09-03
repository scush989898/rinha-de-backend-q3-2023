package com.rinhadebackend.service;

import com.rinhadebackend.dto.request.PessoaRequest;
import com.rinhadebackend.dto.response.PessoaResponse;

import java.util.List;
import java.util.UUID;

public interface PessoaService {

    PessoaResponse createPessoa (PessoaRequest pessoaRequest);

//    PessoaResponse getPersonById (UUID id);

//    List<PessoaResponse> getPersonsBySearchTerm (String searchTerm);

//    int getPersonsCount ();


}
