package own.jb.onlinevotingplatform;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.ApplicationArguments;
import org.springframework.boot.ApplicationRunner;
import org.springframework.core.io.Resource;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.datasource.init.ScriptUtils;
import org.springframework.stereotype.Component;
import own.jb.onlinevotingplatform.voting.entites.Vote;
import own.jb.onlinevotingplatform.voting.services.VoteService;

import java.sql.SQLException;
import java.util.Objects;

@Component
public class AppStartup implements ApplicationRunner {

    private JdbcTemplate jdbcTemplate;
    private VoteService voteService;

    @Value("classpath:appStartupQueries.sql")
    private Resource resourceFile;

    public AppStartup(JdbcTemplate jdbcTemplate, VoteService voteService) {
        this.jdbcTemplate = jdbcTemplate;
        this.voteService = voteService;
    }

    /**
     * On startup sql script from resources folder which contains initial/testing data is executed
     */
    @Override
    public void run(ApplicationArguments args) throws SQLException {
        ScriptUtils.executeSqlScript(Objects.requireNonNull(jdbcTemplate.getDataSource()).getConnection(), resourceFile);
        initializeData();
    }

    /**
     * All currently taking place voting are set to be calculated after they will be finished
     */
    private void initializeData(){
        for (Vote vote: voteService.findAllCurrentVoting()) {
            voteService.calculateVoteResultWithTiming(vote);
        }
    }

}
