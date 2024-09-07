package br.edu.ufape.myufapeanime.myufapeanime.negocio.cadastro;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import br.edu.ufape.myufapeanime.myufapeanime.repositorios.InterfaceRepositorioAdm;

@Service
public class CadastroAdm {
    @Autowired
    private InterfaceRepositorioAdm repositorioAdm;

    
}
