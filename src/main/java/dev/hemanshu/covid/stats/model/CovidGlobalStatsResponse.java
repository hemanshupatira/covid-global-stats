package dev.hemanshu.covid.stats.model;

public class CovidGlobalStatsResponse {

    private int totalConfirmedCases;
    private int totalRecoveredCases;
    private int totalDeathCases;

    public int getTotalConfirmedCases() {
        return totalConfirmedCases;
    }

    public int getTotalRecoveredCases() {
        return totalRecoveredCases;
    }

    public int getTotalDeathCases() {
        return totalDeathCases;
    }

    public static final class CovidGlobalStatsResponseBuilder {
        private int totalConfirmedCases;
        private int totalRecoveredCases;
        private int totalDeathCases;

        private CovidGlobalStatsResponseBuilder() {
        }

        public static CovidGlobalStatsResponseBuilder aCovidGlobalStatsResponse() {
            return new CovidGlobalStatsResponseBuilder();
        }

        public CovidGlobalStatsResponseBuilder withTotalConfirmedCases(int totalConfirmedCases) {
            this.totalConfirmedCases = totalConfirmedCases;
            return this;
        }

        public CovidGlobalStatsResponseBuilder withTotalRecoveredCases(int totalRecoveredCases) {
            this.totalRecoveredCases = totalRecoveredCases;
            return this;
        }

        public CovidGlobalStatsResponseBuilder withTotalDeathCases(int totalDeathCases) {
            this.totalDeathCases = totalDeathCases;
            return this;
        }

        public CovidGlobalStatsResponse build() {
            CovidGlobalStatsResponse covidGlobalStatsResponse = new CovidGlobalStatsResponse();
            covidGlobalStatsResponse.totalConfirmedCases = this.totalConfirmedCases;
            covidGlobalStatsResponse.totalRecoveredCases = this.totalRecoveredCases;
            covidGlobalStatsResponse.totalDeathCases = this.totalDeathCases;
            return covidGlobalStatsResponse;
        }
    }
}
