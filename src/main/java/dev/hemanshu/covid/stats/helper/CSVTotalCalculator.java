package dev.hemanshu.covid.stats.helper;

import com.opencsv.CSVReader;
import com.opencsv.CSVReaderBuilder;
import com.opencsv.exceptions.CsvException;
import org.springframework.stereotype.Component;

import java.io.BufferedInputStream;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.URL;
import java.util.List;

@Component
public class CSVTotalCalculator {

    public int getTotalCases(String url) throws IOException, CsvException {
        BufferedInputStream in = new BufferedInputStream(new URL(url).openStream());
        InputStreamReader inputStreamReader = new InputStreamReader(in);
        CSVReader csvReader = new CSVReaderBuilder(inputStreamReader)
                .withSkipLines(1)
                .build();
        List<String[]> allData = csvReader.readAll();
        int sum = 0;
        for (String[] row : allData) {
            sum += Integer.parseInt(row[row.length - 1]);
        }
        return sum;
    }
}
