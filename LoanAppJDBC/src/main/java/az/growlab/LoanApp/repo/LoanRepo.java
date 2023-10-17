package az.growlab.LoanApp.repo;

import az.growlab.LoanApp.model.LoanInformation;
import lombok.RequiredArgsConstructor;
import org.springframework.jdbc.core.namedparam.MapSqlParameterSource;
import org.springframework.jdbc.core.namedparam.NamedParameterJdbcTemplate;
import org.springframework.jdbc.support.GeneratedKeyHolder;
import org.springframework.jdbc.support.KeyHolder;
import org.springframework.stereotype.Repository;

@Repository
@RequiredArgsConstructor
public class LoanRepo {

    private final NamedParameterJdbcTemplate loanJdbcTemplate;

    public int save(LoanInformation p) {
        KeyHolder keyHolder = new GeneratedKeyHolder();
        String query = "insert into loan_info(total_amount, pre_amount, interest_rate) values(:total_amount, :pre_amount, :interest_rate)";
        loanJdbcTemplate.update(
                query,
                new MapSqlParameterSource()
                        .addValue("pre_amount", p.getPreAmount())
                        .addValue("total_amount", p.getTotalAmount())
                        .addValue("interest_rate", p.getInterestRate()),
                keyHolder
        );
        return keyHolder.getKey().intValue();
    }

}
