package az.growlab.LoanApp.config;

import lombok.AllArgsConstructor;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.boot.jdbc.DataSourceBuilder;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import javax.sql.DataSource;

@Configuration
@AllArgsConstructor
public class DatabaseConfiguration {

    @Bean("DB-LoanApp")
    @ConfigurationProperties(prefix = "spring.datasource.loanapp")
    public DataSource productDb() {
        return DataSourceBuilder.create().build();
    }

}
