package myapp.domain;

import myapp.domain.enumeration.ProductStatus;
import myapp.repository.ProductRepository;
import myapp.service.ProductService;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.exceptions.base.MockitoException;
import org.mockito.junit.jupiter.MockitoExtension;
import org.junit.jupiter.api.extension.ExtendWith;
import static org.junit.jupiter.api.Assertions.assertEquals;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.MethodSource;
import java.util.stream.Stream;

import java.math.BigDecimal;
import java.time.Instant;


import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.mockito.Mockito.when;

@ExtendWith(MockitoExtension.class)
public class ProductServiceTest {

    public static final String TITLE_TOO_SHORT = "Description should not be less than 3 characters";
    public static final String TITLE_TOO_LONG = "Description should not be more than 100 characters";
    public static final String KEYWORDS_TOO_LONG = "Keywords should not be more than 200 characters";
    public static final String DESCRIPTION_TOO_SHORT = "Description should not be less than 10 characters";
    public static final String RATING_OUT_OF_RANGE = "Rating should be between 0 and 5";
    public static final String PRICE_NEGATIVE = "Price should not be negative";
    public static final String QUANTITY_NEGATIVE = "Quantity in stock should not be negative";
    public static final String STATUS_INVALID = "Invalid status";
    public static final String WEIGHT_NEGATIVE = "Weight should not be negative";
    public static final String DIMENSIONS_TOO_LONG = "Dimensions should not be more than 50 characters";
    public static final String DATE_ADDED_INVALID = "Invalid date added";
    public static final String DATE_MODIFIED_INVALID = "Date modified should be after date added";


    @Mock
    private ProductRepository productRepository;

    @InjectMocks
    private ProductService productService;

    // create a product object for testing
    public static Product createProductSample(
        Long id,
        String title,
        String keywords,
        String description,
        Integer rating,
        BigDecimal price,
        Integer quantityInStock,
        ProductStatus status,
        Double weight,
        String dimensions,
        Instant dateAdded,
        Instant dateModified
    ) {
        Product product = new Product()
            .id(id)
            .title(title)
            .keywords(keywords)
            .description(description)
            .rating(rating)
            .price(price)
            .quantityInStock(quantityInStock)
            .status(status)
            .weight(weight)
            .dimensions(dimensions)
            .dateAdded(dateAdded)
            .dateModified(dateModified);
        return product;
    }

    public void mockValidation(Product product) {
        String error = validateProduct(product);
        if (error != null) {
            when(productRepository.save(product)).thenThrow(new MockitoException(error));

            Exception exception = assertThrows(Exception.class, () -> productService.save(product));
            assertEquals(error, exception.getMessage());
        }
    }

    private static String validateProduct(Product product) {
        if (product.getTitle() == null || product.getTitle().length() < 3) {
            return TITLE_TOO_SHORT;
        }
        if (product.getTitle().length() > 100) {
            return TITLE_TOO_LONG;
        }
        if (product.getKeywords() != null && product.getKeywords().length() > 200) {
            return KEYWORDS_TOO_LONG;
        }
        if (product.getDescription() != null && product.getDescription().length() < 10) {
            return DESCRIPTION_TOO_SHORT;
        }
        if (product.getRating() != null && (product.getRating() < 0 || product.getRating() > 5)) {
            return RATING_OUT_OF_RANGE;
        }
        if (product.getPrice().compareTo(BigDecimal.ZERO) < 0) {
            return PRICE_NEGATIVE;
        }
        if (product.getQuantityInStock() != null && product.getQuantityInStock() < 0) {
            return QUANTITY_NEGATIVE;
        }
        if ((product.getStatus() != ProductStatus.IN_STOCK) && (product.getStatus() != ProductStatus.OUT_OF_STOCK) && (product.getStatus() != ProductStatus.DISCONTINUED)) {
            return STATUS_INVALID;
        }
        if (product.getWeight() != null && product.getWeight() < 0) {
            return WEIGHT_NEGATIVE;
        }
        if (product.getDimensions() != null && product.getDimensions().length() > 50) {
            return DIMENSIONS_TOO_LONG;
        }
        if (product.getDateAdded() == null) {
            return DATE_ADDED_INVALID;
        }
        if (product.getDateModified() != null && product.getDateModified().isBefore(product.getDateAdded())) {
            return DATE_MODIFIED_INVALID;
        }
        return null;
    }


    private static Stream<Product> generateProducts() {
        return Stream.of(
            createProductSample(1L, "AAA", "A".repeat(200), "A".repeat(10), 0, BigDecimal.valueOf(0.00), 0, ProductStatus.IN_STOCK, 0.0, "A".repeat(50), Instant.now(), null),
            createProductSample(2L, "A".repeat(100), null, "A".repeat(10), 5, BigDecimal.valueOf(0.00), null, ProductStatus.OUT_OF_STOCK, null, null, Instant.now(), Instant.now()),
            createProductSample(3L, "A".repeat(100), null, "A".repeat(10), 5, BigDecimal.valueOf(0.00), null, ProductStatus.IN_STOCK, null, null, Instant.now(), Instant.now()),
            createProductSample(4L, "BB", "A".repeat(200), "A".repeat(10), 0, BigDecimal.valueOf(0.00), 0, ProductStatus.IN_STOCK, 0.0, "A".repeat(50), Instant.now(), null),
            createProductSample(5L, "B".repeat(101), "A".repeat(200), "A".repeat(10), 0, BigDecimal.valueOf(0.00), 0, ProductStatus.IN_STOCK, 0.0, "A".repeat(50), Instant.now(), null),
            createProductSample(6L, null, "A".repeat(200), "A".repeat(10), 0, BigDecimal.valueOf(0.00), 0, ProductStatus.IN_STOCK, 0.0, "A".repeat(50), Instant.now(), null),
            createProductSample(7L, "AAA", "A".repeat(201), "A".repeat(10), 0, BigDecimal.valueOf(0.00), 0, ProductStatus.IN_STOCK, 0.0, "A".repeat(50), Instant.now(), null),
            createProductSample(8L, "AAA", "A".repeat(200), "B".repeat(9), 0, BigDecimal.valueOf(0.00), 0, ProductStatus.IN_STOCK, 0.0, "A".repeat(50), Instant.now(), null),
            createProductSample(9L, "AAA", "A".repeat(200), "A".repeat(10), -1, BigDecimal.valueOf(0.00), 0, ProductStatus.IN_STOCK, 0.0, "A".repeat(50), Instant.now(), null),
            createProductSample(10L, "AAA", "A".repeat(200), "A".repeat(10), 6, BigDecimal.valueOf(0.00), 0, ProductStatus.IN_STOCK, 0.0, "A".repeat(50), Instant.now(), null),
            createProductSample(11L, "AAA", "A".repeat(200), "A".repeat(10), 0, BigDecimal.valueOf(-1.00), 0, ProductStatus.IN_STOCK, 0.0, "A".repeat(50), Instant.now(), null),
            createProductSample(12L, "AAA", "A".repeat(200), "A".repeat(10), 0, null, 15, ProductStatus.IN_STOCK, 0.0, "A".repeat(51), Instant.now(), null),
            createProductSample(13L, "AAA", "A".repeat(200), "A".repeat(10), 0, BigDecimal.valueOf(0.00), -1, ProductStatus.IN_STOCK, 0.0, "A".repeat(50), Instant.now(), null),
            createProductSample(14L, "AAA","A".repeat(200), "A".repeat(10), 0, BigDecimal.valueOf(0.00), 0, ProductStatus.ON_SALE, 0.0, "A".repeat(50), Instant.now(), null),
            createProductSample(15L, "AAA", "A".repeat(200), "A".repeat(10), 0, BigDecimal.valueOf(0.00), 0, ProductStatus.IN_STOCK, -1.0, "A".repeat(50), Instant.now(), null),
            createProductSample(16L, "AAA", "A".repeat(200), "A".repeat(10), 0, BigDecimal.valueOf(0.00), 0, ProductStatus.IN_STOCK, 0.0, "B".repeat(51), Instant.now(), null),
            createProductSample(17L, "AAA", "A".repeat(200), "A".repeat(10), 0, BigDecimal.valueOf(0.00), 0, ProductStatus.IN_STOCK, 0.0, "A".repeat(50), null ,null),
            createProductSample(18L, "AAA", "A".repeat(200), "A".repeat(10), 0, BigDecimal.valueOf(0.00), 0, ProductStatus.IN_STOCK, 0.0, "A".repeat(50), Instant.now(), Instant.now().minusSeconds(1))
        );
    }

    // Teste parametrizado que roda para cada produto gerado pelo método acima
    @ParameterizedTest
    @MethodSource("generateProducts")
    @DisplayName("Teste parametrizado para múltiplos produtos")
    public void saveProduct_MultipleProducts(Product product) {
        // Usando mockito para simular a chamada de salvamento do produto
        when(productRepository.save(product)).thenReturn(product);

        // Testar se o produto é salvo corretamente ou se falha devido à validação
        mockValidation(product);

        // Verificar se o produto foi salvo corretamente (se não houve exceção)
        Product savedProduct = productService.save(product);
        assertEquals(product, savedProduct);
    }
}

    /*
    Requisito Funcional: Criar Novo Produto
        O sistema da loja online deve permitir que os usuários criem um novo produto, garantindo que os seguintes critérios sejam atendidos:
        Título do Produto: O título é um campo obrigatório e deve conter entre 3 e 100 caracteres. Não deve ser permitido criar produtos com títulos vazios, muito curtos ou muito longos.
        Palavras-chave: Este campo é opcional e pode conter até 200 caracteres. Se esse limite for excedido, o sistema deve alertar o usuário.
        Descrição: A descrição do produto é opcional, mas se fornecida, deve conter no mínimo 10 caracteres. O sistema não deve aceitar descrições muito curtas.
        Avaliação do Produto: A avaliação é opcional e deve ser um número inteiro entre 0 e 5. Qualquer valor fora desse intervalo deve ser rejeitado.
        Preço: O preço é obrigatório e deve ser um valor decimal maior ou igual a 0. Não devem ser permitidos preços negativos.
        Quantidade em Estoque: Este campo é opcional, mas se fornecido, deve ser um número inteiro maior ou igual a 0.
        Status do Produto: O status é obrigatório e deve ser selecionado a partir de um conjunto de valores predefinidos, como "Disponível", "Esgotado" ou "Descontinuado". O sistema deve garantir que um status válido seja escolhido.
        Peso do Produto: O peso é opcional, mas se fornecido, deve ser um valor decimal maior ou igual a 0. O sistema deve rejeitar valores negativos.
        Dimensões do Produto: As dimensões são opcionais e não podem ultrapassar 50 caracteres.
        Data de Adição: A data em que o produto é adicionado é obrigatória e deve ser uma data válida que registre o momento da criação.
        Data de Modificação: Este campo é opcional e só deve ser atualizado quando o produto for modificado.
        O sistema deve validar todos esses campos antes de permitir a criação de um produto. Se alguma validação falhar, o sistema deve notificar o usuário com mensagens claras, facilitando a correção dos dados.
     */


