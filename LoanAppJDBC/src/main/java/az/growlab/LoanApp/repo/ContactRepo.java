package az.growlab.LoanApp.repo;

import az.growlab.LoanApp.model.ContactInformation;
import lombok.RequiredArgsConstructor;
import org.springframework.jdbc.core.namedparam.MapSqlParameterSource;
import org.springframework.jdbc.core.namedparam.NamedParameterJdbcTemplate;
import org.springframework.jdbc.support.GeneratedKeyHolder;
import org.springframework.jdbc.support.KeyHolder;
import org.springframework.stereotype.Repository;

@Repository
@RequiredArgsConstructor
public class ContactRepo {

    private final NamedParameterJdbcTemplate loanJdbcTemplate;

    public int save(ContactInformation contactInformation) {
        KeyHolder keyHolder = new GeneratedKeyHolder();
        String query = "insert into contact_info(home_number, work_number, mobile, email) values(:home_number, :work_number, :mobile, :email)";
        loanJdbcTemplate.update(
                query,
                new MapSqlParameterSource()
                        .addValue("home_number", contactInformation.getHomeNumber())
                        .addValue("work_number", contactInformation.getWorkNumber())
                        .addValue("mobile", contactInformation.getMobile())
                        .addValue("email", contactInformation.getEmail()),
                keyHolder
        );
        return keyHolder.getKey().intValue();
    }
}
