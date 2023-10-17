package az.growlab.LoanApp.repo;

import az.growlab.LoanApp.model.Product;
import lombok.RequiredArgsConstructor;
import org.springframework.jdbc.core.namedparam.MapSqlParameterSource;
import org.springframework.jdbc.core.namedparam.NamedParameterJdbcTemplate;
import org.springframework.jdbc.support.GeneratedKeyHolder;
import org.springframework.jdbc.support.KeyHolder;
import org.springframework.stereotype.Repository;

@Repository
@RequiredArgsConstructor
public class ProductRepo {

    private final NamedParameterJdbcTemplate loanJdbcTemplate;

    public int save(Product p) {
        KeyHolder keyHolder = new GeneratedKeyHolder();
        String query = "insert into products(id, loan_info_id, name, price) values(:id, :loan_info_id, :name, :price)";
        loanJdbcTemplate.update(
                query,
                new MapSqlParameterSource()
                        .addValue("id", p.getId())
                        .addValue("loan_info_id", p.getLoanInfoId())
                        .addValue("name", p.getName())
                        .addValue("price", p.getPrice()),
                keyHolder
        );
        return keyHolder.getKey().intValue();
    }

}
