package test2;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.springframework.batch.item.ItemProcessor;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

public class MatchProcessor implements ItemProcessor<Match, Match> {

    private static final Log logger = LogFactory.getLog(MatchProcessor.class);
    @Override
    public Match process(final Match match) {
/*        logger.info(":::processedMatch:::1 " + match.getMarketId());
        logger.info(":::processedMatch:::2 " + match);*/

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

//        logger.info(":::processedMatch::: 3" + matchesToSort.size());

        return processedMatch;
    }
}
