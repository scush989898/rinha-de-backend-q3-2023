package com.rinhadebackend.service;

import com.rinhadebackend.dto.request.PessoaRequest;
import com.rinhadebackend.dto.response.PessoaResponse;
import com.rinhadebackend.model.Pessoa;
import com.rinhadebackend.repository.PessoaRepository;
import com.rinhadebackend.repository.StackRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor

public class PessoaServiceImpl implements PessoaService {

    private final PessoaRepository pessoaRepository;

    private final StackRepository stackRepository;
    @Override
    public PessoaResponse createPessoa(PessoaRequest pessoaRequest) {


        Pessoa entity = pessoaRequest.toPessoa();
        stackRepository.saveAll(entity.getStacks());


        Pessoa pessoa = pessoaRepository.save(entity);
        return PessoaResponse.fromPessoa(pessoa);

    }
}
