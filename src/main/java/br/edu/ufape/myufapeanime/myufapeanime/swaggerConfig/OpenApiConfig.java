package br.edu.ufape.myufapeanime.myufapeanime.swaggerConfig;

import io.swagger.v3.oas.models.OpenAPI;
import io.swagger.v3.oas.models.info.Info;
import io.swagger.v3.oas.models.tags.Tag;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class OpenApiConfig {

    @Bean
    public OpenAPI customOpenAPI() {
        return new OpenAPI()
                .info(new Info()
                        .title("My UFAPE Anime API")
                        .version("1.0")
                        .description("API de cadastro e gerenciamento de animes do MyUFAPEAnime"))
                .addTagsItem(new Tag().name("Animes").description("Operações relacionadas a animes"))
                .addTagsItem(new Tag().name("Usuários").description("Operações relacionadas a usuários"))
                .addTagsItem(new Tag().name("Avaliações").description("Operações relacionadas a avaliações"))
                .addTagsItem(new Tag().name("Autenticação").description("API de autenticação e gerenciamento de usuários"));
    }
}
