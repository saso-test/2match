package test2;

import javax.persistence.*;
import javax.validation.constraints.NotNull;
import java.math.BigDecimal;


//MATCH_ID|MARKET_ID|OUTCOME_ID|SPECIFIERS
@Entity
public class Match {

    @Id
    @GeneratedValue (strategy =  GenerationType.IDENTITY)
    private Integer id;

    @Column (name = "MATCH_ID", nullable = false)
    private String matchId;

    @NotNull
    @Column (name = "MARKET_ID", nullable = false)
    private String marketId;

    @NotNull
    @Column (name = "OUTCOME_ID", nullable = false)
    private String outcomeId;

    @Column (name = "SPECIFIERS")
    private String specifiers;

    @Column (name = "DATE_INSERT")
    private String dateInsert;

    public Match(){}

    public Match(String matchId, String marketId, String outcomeId, String specifiers, String dateInsert) {
        this.matchId = matchId;
        this.marketId = marketId;
        this.outcomeId = outcomeId;
        this.specifiers = specifiers;
        this.dateInsert = dateInsert;
    }

    public String getMatchId() {
        return matchId;
    }

    public void setMatchId(String matchId) {
        this.matchId = matchId;
    }

    public String getMarketId() {
        return marketId;
    }

    public void setMarketId(String marketId) {
        this.marketId = marketId;
    }

    public String getOutcomeId() {
        return outcomeId;
    }

    public void setOutcomeId(String outcomeId) {
        this.outcomeId = outcomeId;
    }

    public String getSpecifiers() {
        return specifiers;
    }

    public void setSpecifiers(String specifiers) {
        this.specifiers = specifiers;
    }

    public String getDateInsert() {
        return dateInsert;
    }

    public void setDateInsert(String dateInsert) {
        this.dateInsert = dateInsert;
    }
}
