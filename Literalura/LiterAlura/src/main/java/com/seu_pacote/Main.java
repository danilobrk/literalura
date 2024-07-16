import com.seu_pacote.BookResponse;

public class Main {
    public static void main(String[] args) {
        try {
            // Exemplos de chamadas de busca
            BookResponse responseByTitle = BookApiClient.searchBooksByTitle("Java");
            BookResponse responseByAuthor = BookApiClient.searchBooksByAuthor("Raden Adjeng Kartini");
            BookResponse responseByYear = BookApiClient.searchBooksByYear(1900);
            BookResponse responseByGenre = BookApiClient.searchBooksByGenre("Fiction");

            // Exibir resultados
            displayBooks("Books found by title 'Java':", responseByTitle);
            displayBooks("Books found by author 'Raden Adjeng Kartini':", responseByAuthor);
            displayBooks("Books found by year 1900:", responseByYear);
            displayBooks("Books found by genre 'Fiction':", responseByGenre);

        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    private static void displayBooks(String message, BookResponse response) {
        System.out.println(message);
        System.out.println("Total books found: " + response.getCount());
        for (Book book : response.getResults()) {
            System.out.println("Title: " + book.getTitle());
            System.out.println("Authors: " + book.getAuthors().stream().map(Book.Author::getName).reduce((a, b) -> a + ", " + b).orElse(""));
            System.out.println("Download URL: " + book.getDownloadUrl());
            System.out.println();
        }
        System.out.println();
    }
}
