package test2;

import org.springframework.batch.item.file.mapping.FieldSetMapper;
import org.springframework.batch.item.file.transform.FieldSet;
import org.springframework.stereotype.Component;

@Component
public class MatchFieldSetMapper implements FieldSetMapper<Match> {
    @Override
    public Match mapFieldSet(FieldSet fieldSet) {
        final Match match = new Match();
        match.setMatchId(fieldSet.readString("matchId"));
        match.setMarketId((fieldSet.readString("marketId")));
        match.setOutcomeId(fieldSet.readString("outcomeId"));
        match.setSpecifiers(fieldSet.readString("specifiers"));
        return match;
    }
}

