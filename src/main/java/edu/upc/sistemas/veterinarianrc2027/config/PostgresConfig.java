package edu.upc.sistemas.veterinarianrc2027.config;

//import org.springframework.boot.autoconfigure.domain.EntityScan;
import org.springframework.boot.persistence.autoconfigure.EntityScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;

@Configuration
@EntityScan("edu.upc.sistemas.veterinarianrc2027.modelo.relacional")
@EnableJpaRepositories("edu.upc.sistemas.veterinarianrc2027.repositorio.relacional")
public class PostgresConfig {
}
