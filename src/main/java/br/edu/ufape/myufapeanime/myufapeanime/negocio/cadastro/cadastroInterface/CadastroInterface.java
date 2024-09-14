package br.edu.ufape.myufapeanime.myufapeanime.negocio.cadastro.cadastroInterface;

import java.util.List;


public interface CadastroInterface<Tipo> {
    public Tipo create(Tipo object) throws Throwable;
    public void delete(Tipo object) throws Exception;
    public void deleteById(Long id) throws Throwable;
    public Tipo update(Tipo object) throws Throwable;
    public List<Tipo> findAll() throws Exception;
    public Tipo findById(Long id) throws Throwable;

}
