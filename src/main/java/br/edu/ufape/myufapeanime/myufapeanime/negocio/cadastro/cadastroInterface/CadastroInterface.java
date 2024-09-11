package br.edu.ufape.myufapeanime.myufapeanime.negocio.cadastro.cadastroInterface;

import javax.sound.midi.Track;
import java.util.List;
import java.util.Optional;

public interface CadastroInterface<Tipo> {
    public Tipo create(Tipo object) throws Throwable;
    public void delete(Tipo object) throws Exception;
    public void deleteById(Long id) throws Throwable;
    public Tipo update(Tipo object) throws Throwable;
    public List<Tipo> findAll() throws Exception;
    public Tipo findById(Long id) throws Throwable;

}
