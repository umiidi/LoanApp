package az.growlab.LoanApp.repo;

import az.growlab.LoanApp.enums.ActionStatus;
import az.growlab.LoanApp.enums.FinalStatus;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.jdbc.core.namedparam.MapSqlParameterSource;
import org.springframework.jdbc.core.namedparam.NamedParameterJdbcTemplate;
import org.springframework.jdbc.support.GeneratedKeyHolder;
import org.springframework.jdbc.support.KeyHolder;
import org.springframework.stereotype.Repository;

@Slf4j
@Repository
@RequiredArgsConstructor
//@Transactional
public class ClientRepo {

    private final NamedParameterJdbcTemplate loanJdbcTemplate;

    public int addClient() {
        KeyHolder keyHolder = new GeneratedKeyHolder();
        String query = "insert into client() values()";
        loanJdbcTemplate.update(
                query,
                new MapSqlParameterSource(),
                keyHolder
        );
        return keyHolder.getKey().intValue();
    }

    public void setPassportInfo(int clientId, int passportInfoId) {
        String query = "update client set passport_info_id = :passport_info_id where id = :client_id";
        loanJdbcTemplate.update(
                query,
                new MapSqlParameterSource()
                        .addValue("client_id", clientId)
                        .addValue("passport_info_id", passportInfoId)
        );
    }

    public void setPersonalInfo(int clientId, int personalInfoId) {
        String query = "update client set personal_info_id = :personal_info_id where id = :client_id";
        loanJdbcTemplate.update(
                query,
                new MapSqlParameterSource()
                        .addValue("client_id", clientId)
                        .addValue("personal_info_id", personalInfoId)
        );
    }

    public void setLoanInfo(int clientId, int loanInfoId) {
        String query = "update client set loan_info_id = :loan_info_id where id = :client_id";
        loanJdbcTemplate.update(
                query,
                new MapSqlParameterSource()
                        .addValue("client_id", clientId)
                        .addValue("loan_info_id", loanInfoId)
        );
    }

    public ActionStatus getActionStatus(int clientId) {
        String query = "select action_status from client where id = :id";
        String actionStatusStr = loanJdbcTemplate.queryForObject(
                query,
                new MapSqlParameterSource()
                        .addValue("id", clientId),
                (rs, rowNum) -> rs.getString("action_status")
        );
        return ActionStatus.valueOf(actionStatusStr);
    }

    public void setActionStatus(int clientId, ActionStatus status) {
        String query = "update client set action_status = :action_status, reject_reason = null where id = :client_id";
        loanJdbcTemplate.update(
                query,
                new MapSqlParameterSource()
                        .addValue("client_id", clientId)
                        .addValue("action_status", status.name())
        );
    }

    public String getRejectReason(int clientId) {
        String query = "select reject_reason from client where id = :id";
        return loanJdbcTemplate.queryForObject(
                query,
                new MapSqlParameterSource()
                        .addValue("id", clientId),
                (rs, rowNum) -> rs.getString("reject_reason")
        );
    }

    public void setRejectReason(int clientId, String rejectReason) {
        String query = "update client set reject_reason = :reject_reason where id = :client_id";
        loanJdbcTemplate.update(
                query,
                new MapSqlParameterSource()
                        .addValue("client_id", clientId)
                        .addValue("reject_reason", rejectReason)
        );
    }

    public void setFinalStatus(int clientId, FinalStatus status) {
        String query = "update client set final_status = :final_status where id = :client_id";
        loanJdbcTemplate.update(
                query,
                new MapSqlParameterSource()
                        .addValue("client_id", clientId)
                        .addValue("final_status", status.name())
        );
    }

}
