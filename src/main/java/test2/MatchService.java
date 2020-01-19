package test2;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.io.ClassPathResource;
import org.springframework.scheduling.annotation.Async;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;
import java.io.*;
import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.CompletableFuture;

@Service
public class MatchService {

/*    private static final Logger LOGGER = LoggerFactory.getLogger(MatchService.class);
    @Autowired
    private MatchRepository matchRepository;

//    SaveMatches

    @Async
    public CompletableFuture<List<Match>> saveMatches(final MultipartFile file) throws Exception {
        final long start = System.currentTimeMillis();
        List<Match> matches = parseCSVFile(new ClassPathResource("fo_random.txt"));
        LOGGER.info("Saving a list of matches of size {} records", matches.size());
        matches = matchRepository.saveAll(matches);
        LOGGER.info("Elapsed time: {}", (System.currentTimeMillis() - start));
        return CompletableFuture.completedFuture(matches);
    }

    private List<Match> parseCSVFile(final ClassPathResource file) throws Exception {
        final List<Match> matches =new ArrayList<>();
        try {
            try (final BufferedReader br = new BufferedReader(new InputStreamReader(file.getInputStream()))) {
                String line;
                while ((line=br.readLine()) != null) {
                    final String[] data=line.split("|");
                    final Match match =new Match();
                    match.setMatchId(data[0]);
                    match.setMarketId(data[1]);
                    match.setOutcomeId(data[2]);
                    match.setSpecifiers(data[3]);
                    matches.add(match);
                }
                return matches;
            }
        } catch(final IOException e) {
            LOGGER.error("Failed to parse CSV file {}", e);
            throw new Exception("Failed to parse CSV file {}", e);
        }
    }*/
}
