package foo.bar;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.data.solr.repository.config.EnableSolrRepositories;

/**
 * Classe de configuração responsável por habilitar repositórios Solr ({@link EnableSolrRepositories}). De forma comoda
 * colocamos ainda nesta classe o método executável que inicia um container embarcado com a aplicação.
 * 
 * @author Francisco Spaeth
 */
@SpringBootApplication
@EnableSolrRepositories(multicoreSupport = true)
public class ApplicationConfiguration {

	public static void main(String args[]) {
		SpringApplication.run(ApplicationConfiguration.class, args);
	}

}
