package br.edu.ufape.myufapeanime.myufapeanime.negocio.cadastro.cadastroAnimeExceptions;

import br.edu.ufape.myufapeanime.myufapeanime.negocio.basica.Anime;

import java.io.Serial;
import java.time.LocalDate;

public class DataInvalidaAnimeException extends Exception {
    @Serial
    private static final long serialVersionUID = 1L;

    public DataInvalidaAnimeException(Anime anime) {
        super(gerarMensagemException(anime));
    }

    private static String gerarMensagemException(Anime anime){
        LocalDate hoje = LocalDate.now();
        LocalDate animeDataLancamento = anime.getDataLancamento();
        LocalDate primeiroAnimeLancado = LocalDate.of(1907, 1, 1);

        if(animeDataLancamento.isBefore(primeiroAnimeLancado)){
            return "A data de lançamento do anime é antes dos primeiros registros: " + primeiroAnimeLancado + " e " + animeDataLancamento;
        }
        if(animeDataLancamento.isAfter(primeiroAnimeLancado)){
            return "A data de lançamento é no futuro: " + hoje;
        }
        if(animeDataLancamento == null){
            return "A data de lançamento é nula";
        }
        return "Data inválida";
    }
}
