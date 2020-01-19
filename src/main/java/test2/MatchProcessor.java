package test2;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.springframework.batch.item.ItemProcessor;

import java.text.SimpleDateFormat;
import java.util.Date;

public class MatchProcessor implements ItemProcessor<Match, Match> {

    private static final Log logger = LogFactory.getLog(MatchProcessor.class);
    @Override
    public Match process(final Match match) {
/*        logger.info(":::processedMatch:::1 " + match.getMarketId()); */

        final String matchId = match.getMatchId();
        final String marketId  = match.getMarketId();
        final String outcomeId  = match.getOutcomeId();
        final String specifiers  = match.getSpecifiers();

        SimpleDateFormat formatter = new SimpleDateFormat("yyyy-MM-dd; HH:mm:ss z");
        Date date = new Date(System.currentTimeMillis());

        final Match processedMatch = new Match();
        processedMatch.setMatchId(matchId);
        processedMatch.setMarketId(marketId);
        processedMatch.setOutcomeId(outcomeId);
        processedMatch.setSpecifiers(specifiers);
        processedMatch.setDateInsert(formatter.format(date));

        return processedMatch;
    }
}
