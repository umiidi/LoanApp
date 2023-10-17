package az.growlab.LoanApp.repo;

import az.growlab.LoanApp.model.PersonalInformation;
import lombok.RequiredArgsConstructor;
import org.springframework.jdbc.core.namedparam.MapSqlParameterSource;
import org.springframework.jdbc.core.namedparam.NamedParameterJdbcTemplate;
import org.springframework.jdbc.support.GeneratedKeyHolder;
import org.springframework.jdbc.support.KeyHolder;
import org.springframework.stereotype.Repository;

@Repository
@RequiredArgsConstructor
public class PersonalRepo {

    private final NamedParameterJdbcTemplate loanJdbcTemplate;
    private final ContactRepo contactRepo;
    private final AddressRepo addressRepo;

    public int save(PersonalInformation p) {
        int contactInfoId = contactRepo.save(p.getContactInformation());
        int addressInfoID = addressRepo.save(p.getAddressInformation());
        KeyHolder keyHolder = new GeneratedKeyHolder();
        String query = "insert into personal_info(contact_info_id, address_info_id) values(:contact_info_id, :address_info_id)";
        loanJdbcTemplate.update(
                query,
                new MapSqlParameterSource()
                        .addValue("contact_info_id", contactInfoId)
                        .addValue("address_info_id", addressInfoID),
                keyHolder
        );
        return keyHolder.getKey().intValue();
    }


}
