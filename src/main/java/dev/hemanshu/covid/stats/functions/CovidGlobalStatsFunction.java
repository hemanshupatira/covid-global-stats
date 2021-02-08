package dev.hemanshu.covid.stats.functions;

import com.amazonaws.services.lambda.runtime.events.APIGatewayProxyRequestEvent;
import com.amazonaws.services.lambda.runtime.events.APIGatewayProxyResponseEvent;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.opencsv.exceptions.CsvException;
import dev.hemanshu.covid.stats.helper.CSVTotalCalculator;
import dev.hemanshu.covid.stats.model.CovidGlobalStatsResponse;
import org.springframework.stereotype.Component;

import java.io.IOException;
import java.util.function.Function;

@Component
public class CovidGlobalStatsFunction implements Function<APIGatewayProxyRequestEvent, APIGatewayProxyResponseEvent> {

    private final CSVTotalCalculator csvTotalCalculator;

    public CovidGlobalStatsFunction(CSVTotalCalculator csvTotalCalculator) {
        this.csvTotalCalculator = csvTotalCalculator;
    }


    @Override
    public APIGatewayProxyResponseEvent apply(APIGatewayProxyRequestEvent apiGatewayProxyRequestEvent) {

        String confirmedCasesURL = "https://raw.githubusercontent.com/CSSEGISandData/COVID-19/master/csse_covid_19_data/csse_covid_19_time_series/time_series_covid19_confirmed_global.csv";
        String recoveredCasesURL = "https://raw.githubusercontent.com/CSSEGISandData/COVID-19/master/csse_covid_19_data/csse_covid_19_time_series/time_series_covid19_recovered_global.csv";
        String deathCasesURL = "https://raw.githubusercontent.com/CSSEGISandData/COVID-19/master/csse_covid_19_data/csse_covid_19_time_series/time_series_covid19_deaths_global.csv";
        APIGatewayProxyResponseEvent responseEvent = new APIGatewayProxyResponseEvent();

        try {
            int totalConfirmedCases = csvTotalCalculator.getTotalCases(confirmedCasesURL);
            int totalRecoveredCases = csvTotalCalculator.getTotalCases(recoveredCasesURL);
            int totalDeathCases = csvTotalCalculator.getTotalCases(deathCasesURL);

            CovidGlobalStatsResponse response = CovidGlobalStatsResponse.CovidGlobalStatsResponseBuilder.aCovidGlobalStatsResponse()
                    .withTotalConfirmedCases(totalConfirmedCases)
                    .withTotalRecoveredCases(totalRecoveredCases)
                    .withTotalDeathCases(totalDeathCases)
                    .build();
            ObjectMapper objectMapper = new ObjectMapper();
            String responseBody = objectMapper.writeValueAsString(response);
            System.out.println("Response: " + responseBody);
            responseEvent.setStatusCode(200);
            responseEvent.setBody(responseBody);

        } catch (IOException | CsvException e) {
            e.printStackTrace();
            responseEvent.setStatusCode(500);
        }
        return responseEvent;
    }
}
