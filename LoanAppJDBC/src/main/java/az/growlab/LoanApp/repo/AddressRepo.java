package az.growlab.LoanApp.repo;

import az.growlab.LoanApp.model.AddressInformation;
import lombok.RequiredArgsConstructor;
import org.springframework.jdbc.core.namedparam.MapSqlParameterSource;
import org.springframework.jdbc.core.namedparam.NamedParameterJdbcTemplate;
import org.springframework.jdbc.support.GeneratedKeyHolder;
import org.springframework.jdbc.support.KeyHolder;
import org.springframework.stereotype.Repository;

@Repository
@RequiredArgsConstructor
public class AddressRepo {

    private final NamedParameterJdbcTemplate loanJdbcTemplate;

    public int save(AddressInformation addressInformation) {
        KeyHolder keyHolder = new GeneratedKeyHolder();
        String query = "insert into address_info(country, city, street, postal_code) values(:country, :city, :street, :postal_code)";
        loanJdbcTemplate.update(
                query,
                new MapSqlParameterSource()
                        .addValue("country", addressInformation.getCountry())
                        .addValue("city", addressInformation.getCity())
                        .addValue("street", addressInformation.getStreet())
                        .addValue("postal_code", addressInformation.getPostalCode()),
                keyHolder
        );
        return keyHolder.getKey().intValue();
    }

}
