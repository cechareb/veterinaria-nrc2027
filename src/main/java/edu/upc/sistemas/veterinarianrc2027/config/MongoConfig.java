package edu.upc.sistemas.veterinarianrc2027.config;

import org.springframework.context.annotation.Configuration;
import org.springframework.data.mongodb.repository.config.EnableMongoRepositories;

@Configuration
@EnableMongoRepositories("edu.upc.sistemas.veterinarianrc2027.repositorio.documental")
public class MongoConfig {
}
