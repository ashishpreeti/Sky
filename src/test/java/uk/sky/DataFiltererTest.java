package test.java.uk.sky;

import main.java.uk.sky.DataFilterer;
import org.junit.Test;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.util.Collection;

import static org.hamcrest.CoreMatchers.is;
import static org.junit.Assert.assertThat;
import static org.junit.Assert.assertTrue;

public class DataFiltererTest {
    @Test
    public void whenEmpty() throws IOException {
        assertTrue(DataFilterer.filterByCountry(openFile("src/test/resources/empty"), "GB").isEmpty());
    }

    @Test
    public void  whenSingleLine() throws IOException {
        Collection<?> recordWithGB = DataFilterer.filterByCountry(openFile("src/test/resources/single-line"), "GB");
        assertThat(recordWithGB.size(), is(1));
    }

    @Test
    public void whenMultiLines() throws IOException {
        Collection<?> recordWithGB = DataFilterer.filterByCountry(openFile("src/test/resources/multi-lines"), "US");
        assertThat(recordWithGB.size(), is(3));
    }

    @Test
    public void shouldReturnOneRecordForResponseTimeAbov8100() throws IOException {
        Collection<?> recordWithGB = DataFilterer.filterByCountryWithResponseTimeAboveLimit(openFile("src/test/resources/multi-lines"), "US", 800);
        assertThat(recordWithGB.size(), is(1));
    }


    private FileReader openFile(String filename) throws FileNotFoundException {
        return new FileReader(new File(filename));

    }
}
