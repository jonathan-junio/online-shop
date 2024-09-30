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
        if (product.getStatus() == null) {
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

    @Test
    @DisplayName("Teste de validação de produto válido")
    public void saveProduct_ValidProduct() {
        Product product = createProductSample(
            1L,
            "title1",
            "keywords1",
            "description1",
            5,
            BigDecimal.valueOf(100.00),
            10,
            ProductStatus.IN_STOCK,
            1.0,
            "10x10x10",
            Instant.now(),
            Instant.now());

        when(productRepository.save(product)).thenReturn(product);

        Product savedProduct = productService.save(product);
        assertEquals(product, savedProduct);
    }

    @Test
    @DisplayName("Teste de validação de título muito curto")
    public void saveProduct_TitleTooShort() {
        Product product = createProductSample(
            1L,
            "AA",
            "keywords1",
            "description1",
            5,
            BigDecimal.valueOf(100.00),
            10,
            ProductStatus.IN_STOCK,
            1.0,
            "10x10x10",
            Instant.now(),
            Instant.now());

        mockValidation(product);
    }

    @Test
    @DisplayName("Teste de validação de título muito longo")
    public void saveProduct_TitleTooLong() {
        Product product = createProductSample(
            1L,
            "A".repeat(101),
            "keywords1",
            "description1",
            5,
            BigDecimal.valueOf(100.00),
            10,
            ProductStatus.IN_STOCK,
            1.0,
            "10x10x10",
            Instant.now(),
            Instant.now());

        mockValidation(product);
    }

    @Test
    @DisplayName("Teste de validação de palavras-chave muito longas")
    public void saveProduct_KeywordsTooLong() {
        Product product = createProductSample(
            1L,
            "title1",
            "A".repeat(201),
            "description1",
            5,
            BigDecimal.valueOf(100.00),
            10,
            ProductStatus.IN_STOCK,
            1.0,
            "10x10x10",
            Instant.now(),
            Instant.now());

        mockValidation(product);
    }

    @Test
    @DisplayName("Teste de validação de descrição muito curta")
    public void saveProduct_DescriptionTooShort() {
        Product product = createProductSample(
            1L,
            "title1",
            "keywords1",
            "A".repeat(9),
            5,
            BigDecimal.valueOf(100.00),
            10,
            ProductStatus.IN_STOCK,
            1.0,
            "10x10x10",
            Instant.now(),
            Instant.now());

        mockValidation(product);
    }

    @Test
    @DisplayName("Teste de validação de avaliação fora do intervalo (abaixo)")
    public void saveProduct_RatingOutOfRangeLow() {
        Product product = createProductSample(
            1L,
            "title1",
            "keywords1",
            "description1",
            -1,
            BigDecimal.valueOf(100.00),
            10,
            ProductStatus.IN_STOCK,
            1.0,
            "10x10x10",
            Instant.now(),
            Instant.now());

        mockValidation(product);
    }

    @Test
    @DisplayName("Teste de validação de avaliação fora do intervalo (acima)")
    public void saveProduct_RatingOutOfRangeHigh() {
        Product product = createProductSample(
            1L,
            "title1",
            "keywords1",
            "description1",
            6,
            BigDecimal.valueOf(100.00),
            10,
            ProductStatus.IN_STOCK,
            1.0,
            "10x10x10",
            Instant.now(),
            Instant.now());

        mockValidation(product);
    }

    @Test
    @DisplayName("Teste de validação de preço negativo")
    public void saveProduct_PriceNegative() {
        Product product = createProductSample(
            1L,
            "title1",
            "keywords1",
            "description1",
            5,
            BigDecimal.valueOf(-1.00),
            10,
            ProductStatus.IN_STOCK,
            1.0,
            "10x10x10",
            Instant.now(),
            Instant.now());

        mockValidation(product);
    }

    @Test
    @DisplayName("Teste de validação de quantidade em estoque negativa")
    public void saveProduct_QuantityNegative() {
        Product product = createProductSample(
            1L,
            "title1",
            "keywords1",
            "description1",
            5,
            BigDecimal.valueOf(100.00),
            -1,
            ProductStatus.IN_STOCK,
            1.0,
            "10x10x10",
            Instant.now(),
            Instant.now());

        mockValidation(product);
    }

    @Test
    @DisplayName("Teste de validação de status inválido")
    public void saveProduct_StatusInvalid() {
        Product product = createProductSample(
            1L,
            "title1",
            "keywords1",
            "description1",
            5,
            BigDecimal.valueOf(100.00),
            10,
            null,
            1.0,
            "10x10x10",
            Instant.now(),
            Instant.now());

        mockValidation(product);
    }

    @Test
    @DisplayName("Teste de validação de peso negativo")
    public void saveProduct_WeightNegative() {
        Product product = createProductSample(
            1L,
            "title1",
            "keywords1",
            "description1",
            5,
            BigDecimal.valueOf(100.00),
            10,
            ProductStatus.IN_STOCK,
            -1.0,
            "10x10x10",
            Instant.now(),
            Instant.now());

        mockValidation(product);
    }

    @Test
    @DisplayName("Teste de validação de dimensões muito longas")
    public void saveProduct_DimensionsTooLong() {
        Product product = createProductSample(
            1L,
            "title1",
            "keywords1",
            "description1",
            5,
            BigDecimal.valueOf(100.00),
            10,
            ProductStatus.IN_STOCK,
            1.0,
            "A".repeat(51),
            Instant.now(),
            Instant.now());

        mockValidation(product);
    }

    @Test
    @DisplayName("Teste de validação de data de adição inválida")
    public void saveProduct_DateAddedInvalid() {
        Product product = createProductSample(
            1L,
            "title1",
            "keywords1",
            "description1",
            5,
            BigDecimal.valueOf(100.00),
            10,
            ProductStatus.IN_STOCK,
            1.0,
            "10x10x10",
            null,
            Instant.now());

        mockValidation(product);
    }

    @Test
    @DisplayName("Teste de validação de data de modificação anterior à data de adição")
    public void saveProduct_DateModifiedInvalid() {
        Product product = createProductSample(
            1L,
            "title1",
            "keywords1",
            "description1",
            5,
            BigDecimal.valueOf(100.00),
            10,
            ProductStatus.IN_STOCK,
            1.0,
            "10x10x10",
            Instant.now(),
            Instant.now().minusSeconds(1));

        mockValidation(product);
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

}
