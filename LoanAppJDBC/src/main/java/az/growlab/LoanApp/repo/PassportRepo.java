package az.growlab.LoanApp.repo;

import az.growlab.LoanApp.model.PassportInformation;
import lombok.RequiredArgsConstructor;
import org.springframework.jdbc.core.namedparam.MapSqlParameterSource;
import org.springframework.jdbc.core.namedparam.NamedParameterJdbcTemplate;
import org.springframework.jdbc.support.GeneratedKeyHolder;
import org.springframework.jdbc.support.KeyHolder;
import org.springframework.stereotype.Repository;

@Repository
@RequiredArgsConstructor
public class PassportRepo {

    private final NamedParameterJdbcTemplate loanJdbcTemplate;

    public int save(PassportInformation p) {
        KeyHolder keyHolder = new GeneratedKeyHolder();
        String query = "insert into passport_info(name, surname, patronymic, birthdate, gender, passport_number) values(:name, :surname, :patronymic, :birthdate, :gender, :passport_number)";
        loanJdbcTemplate.update(
                query,
                new MapSqlParameterSource()
                        .addValue("name", p.getName())
                        .addValue("surname", p.getSurname())
                        .addValue("patronymic", p.getPatronymic())
                        .addValue("birthdate", p.getBirthdate())
                        .addValue("gender", p.getGender().name())
                        .addValue("passport_number", p.getPassportNumber()),
                keyHolder
        );
        return keyHolder.getKey().intValue();
    }

}
