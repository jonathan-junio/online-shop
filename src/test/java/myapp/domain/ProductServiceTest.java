package myapp.domain;

import myapp.domain.enumeration.ProductStatus;
import myapp.repository.ProductRepository;
import myapp.service.ProductService;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.junit.jupiter.api.extension.ExtendWith;
import static org.junit.jupiter.api.Assertions.assertEquals;

import java.math.BigDecimal;
import java.time.Instant;



import static org.mockito.Mockito.when;

@ExtendWith(MockitoExtension.class)
public class ProductServiceTest {

    @Mock
    private ProductRepository productRepository;

    @InjectMocks
    private ProductService productService;

    @Test
    public void saveProduct_Success() {
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

        // check if the product was saved correctly
        assertEquals(product, savedProduct);
        assertEquals(product.getId(), savedProduct.getId());
        assertEquals(product.getTitle(), savedProduct.getTitle());
        assertEquals(product.getKeywords(), savedProduct.getKeywords());
        assertEquals(product.getDescription(), savedProduct.getDescription());
        assertEquals(product.getRating(), savedProduct.getRating());
        assertEquals(product.getPrice(), savedProduct.getPrice());
        assertEquals(product.getQuantityInStock(), savedProduct.getQuantityInStock());
        assertEquals(product.getStatus(), savedProduct.getStatus());
        assertEquals(product.getWeight(), savedProduct.getWeight());
        assertEquals(product.getDimensions(), savedProduct.getDimensions());
        assertEquals(product.getDateAdded(), savedProduct.getDateAdded());
        assertEquals(product.getDateModified(), savedProduct.getDateModified());
    }

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
        Product product = new Product();
        product.setId(id);
        product.setTitle(title);
        product.setKeywords(keywords);
        product.setDescription(description);
        product.setRating(rating);
        product.setPrice(price);
        product.setQuantityInStock(quantityInStock);
        product.setStatus(status);
        product.setWeight(weight);
        product.setDimensions(dimensions);
        product.setDateAdded(dateAdded);
        product.setDateModified(dateModified);
        return product;
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

    Product[] produtos = {
//        createProductSample(1L, "AAA", "AAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAA", "AAAAAAAAAA", 0, BigDecimal.valueOf(0.00), 0, ProductStatus.Disponível, 0.00, "AAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAA", Instant.parse("2020-12-12T12:57:00Z"), null)

//        new Produto("AAA", "AAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAA ", "AAAAAAAAAA", 0, 0.00, 0, "Disponível", 0.00, "AAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAA", LocalDateTime.of(2020, 12, 12, 12, 57), null),
//        new Produto("AAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAA", null, "AAAAAAAAAA", 5, 0.00, null, "Esgotado", 0.00, null, LocalDateTime.of(2020, 12, 12, 12, 57), null),
//        new Produto("AAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAA ", null, "AAAAAAAAAA", 5, 0.00, null, "Descontinuado", 0.00, null, LocalDateTime.of(2020, 12, 12, 12, 57), null),
//        new Produto("BB", "AAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAA", "AAAAAAAAAA", 0, 0.00, 0, "Disponível", 0.00, "AAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAA", LocalDateTime.of(2020, 12, 12, 12, 57), null),
//        new Produto("BBBBBBBBBBBBBBBBBBBBBBBBBBBBBBBBBBBBBBBBBBBBBBBBBBBBBBBBBBBBBBBBBBBBBBBBBBBBBBBBBBBBBBBBBBBBBBBBBBBBB", "AAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAA", "AAAAAAAAAA", 0, 0.00, 0, "Disponível", 0.00, "AAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAA", LocalDateTime.of(2020, 12, 12, 12, 57), null),
//        new Produto(null, "AAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAA", "AAAAAAAAAA", 0, 0.00, 0, "Disponível", 0.00, "AAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAA", LocalDateTime.of(2020, 12, 12, 12, 57), null),
//        new Produto("AAA", "BBBBBBBBBBBBBBBBBBBBBBBBBBBBBBBBBBBBBBBBBBBBBBBBBBBBBBBBBBBBBBBBBBBBBBBBBBBBBBBBBBBBBBBBBBBBBBBBBBBBBBBBBBBBBBBBBBBBBBBBBBBBBBBBBBBBBBBBBBBBBBBBBBBBBBBBBBBBBBBBBBBBBBBBBBBBBBBBBBBBBBBBBBBBBBBBBBBBBBBBB", "AAAAAAAAAA", 0, 0.00, 0, "Disponível", 0.00, "AAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAA", LocalDateTime.of(2020, 12, 12, 12, 57), null),
//        new Produto("AAA", "AAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAA", "BBBBBBBBB", 0, 0.00, 0, "Disponível", 0.00, "AAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAA", LocalDateTime.of(2020, 12, 12, 12, 57), null),
//        new Produto("AAA", "AAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAA", "AAAAAAAAAA", -1, 0.00, 0, "Disponível", 0.00, "AAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAA", LocalDateTime.of(2020, 12, 12, 12, 57), null),
//        new Produto("AAA", "AAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAA", "AAAAAAAAAA", 6, 0.00, 0, "Disponível", 0.00, "AAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAA", LocalDateTime.of(2020, 12, 12, 12, 57), null),
//        new Produto("AAA", "AAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAA", "AAAAAAAAAA", 0, -1.00, 0, "Disponível", 0.00, "AAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAA", LocalDateTime.of(2020, 12, 12, 12, 57), null),
//        new Produto("AAA", "AAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAA", "AAAAAAAAAA", 0, 0.00, -1, "Disponível", 0.00, "AAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAA", LocalDateTime.of(2020, 12, 12, 12, 57), null),
//        new Produto("AAA", "AAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAA", "AAAAAAAAAA", 0, 0.00, 0, "Pronta Entrega", 0.00, "AAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAA", LocalDateTime.of(2020, 12, 12, 12, 57), null),
//        new Produto("AAA", "AAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAA", "AAAAAAAAAA", 0, 0.00, 0, "Disponível", -1.00, "AAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAA", LocalDateTime.of(2020, 12, 12, 12, 57), null),
//        new Produto("AAA", "AAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAA", "AAAAAAAAAA", 0, 0.00, 0, "Disponível", 0.00, "BBBBBBBBBBBBBBBBBBBBBBBBBBBBBBBBBBBBBBBBBBBBBBBBBB", LocalDateTime.of(2020, 12, 12, 12, 57), null),
//        new Produto("AAA", "AAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAA", "AAAAAAAAAA", 0, 0.00, 0, "Disponível", 0.00, "AAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAA", LocalDateTime.of(2020, 12, 12, 12, 57), null)
    };


}
