import com.fasterxml.jackson.databind.ObjectMapper;
import org.apache.http.client.methods.CloseableHttpResponse;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.impl.client.CloseableHttpClient;
import org.apache.http.impl.client.HttpClients;

import java.io.IOException;
import java.net.URLEncoder;
import java.nio.charset.StandardCharsets;

public class BookApiClient {
    private static final String API_URL = "https://api.gutendex.com/books";

    public static BookResponse searchBooksByTitle(String title) throws IOException {
        String encodedTitle = URLEncoder.encode(title, StandardCharsets.UTF_8.toString());
        String url = API_URL + "?search=" + encodedTitle;
        return executeSearch(url);
    }

    public static BookResponse searchBooksByAuthor(String author) throws IOException {
        String encodedAuthor = URLEncoder.encode(author, StandardCharsets.UTF_8.toString());
        String url = API_URL + "?author=" + encodedAuthor;
        return executeSearch(url);
    }

    public static BookResponse searchBooksByYear(int year) throws IOException {
        String url = API_URL + "?year=" + year;
        return executeSearch(url);
    }

    public static BookResponse searchBooksByGenre(String genre) throws IOException {
        String encodedGenre = URLEncoder.encode(genre, StandardCharsets.UTF_8.toString());
        String url = API_URL + "?subject=" + encodedGenre;
        return executeSearch(url);
    }

    private static BookResponse executeSearch(String url) throws IOException {
        try (CloseableHttpClient httpClient = HttpClients.createDefault()) {
            HttpGet request = new HttpGet(url);
            try (CloseableHttpResponse response = httpClient.execute(request)) {
                ObjectMapper mapper = new ObjectMapper();
                String jsonResponse = new String(response.getEntity().getContent().readAllBytes());
                return mapper.readValue(jsonResponse, BookResponse.class);
            }
        }
    }
}
