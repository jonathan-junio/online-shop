package myapp.domain;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;

class ProductTest {
     // initialize the Product object
    Product product = new Product();

    @Test
    void getId() {
        // set the id of the product
        product.setId(1L);
        // check if the id is equal to 1
        assertEquals(1L, product.getId());
    }

    @Test
    void setId() {
        // set the id of the product
        product.setId(1L);
        // check if the id is equal to 1
        assertEquals(1L, product.getId());
    }

    @Test
    void getTitle() {
        // set the title of the product
        product.setTitle("Product Title");
        // check if the title is equal to "Product Title"
        assertEquals("Product Title", product.getTitle());
    }

    @Test
    void title() {
        // set the title of the product
        product.setTitle("Product Title");
        // check if the title is equal to "Product Title"
        assertEquals("Product Title", product.getTitle());
    }

    @Test
    void setTitle() {
        // set the title of the product
        product.setTitle("Product Title");
        // check if the title is equal to "Product Title"
        assertEquals("Product Title", product.getTitle());
    }

    @Test
    void getKeywords() {
    }

    @Test
    void keywords() {
    }

    @Test
    void setKeywords() {
    }

    @Test
    void getDescription() {
    }

    @Test
    void description() {
    }

    @Test
    void setDescription() {
    }

    @Test
    void getRating() {
    }

    @Test
    void rating() {
    }

    @Test
    void setRating() {
    }

    @Test
    void getPrice() {
    }

    @Test
    void price() {
    }

    @Test
    void setPrice() {
    }

    @Test
    void getQuantityInStock() {
    }

    @Test
    void quantityInStock() {
    }

    @Test
    void setQuantityInStock() {
    }

    @Test
    void getStatus() {
    }

    @Test
    void status() {
    }

    @Test
    void setStatus() {
    }

    @Test
    void getWeight() {
    }

    @Test
    void weight() {
    }

    @Test
    void setWeight() {
    }

    @Test
    void getDimensions() {
    }

    @Test
    void dimensions() {
    }

    @Test
    void setDimensions() {
    }

    @Test
    void getDateAdded() {
    }

    @Test
    void dateAdded() {
    }

    @Test
    void setDateAdded() {
    }

    @Test
    void getDateModified() {
    }

    @Test
    void dateModified() {
    }

    @Test
    void setDateModified() {
    }

    @Test
    void getWishList() {
    }

    @Test
    void setWishList() {
    }

    @Test
    void wishList() {
    }

    @Test
    void getOrder() {
    }

    @Test
    void setOrder() {
    }

    @Test
    void order() {
    }

    @Test
    void getCategories() {
    }

    @Test
    void setCategories() {
    }

    @Test
    void categories() {
    }

    @Test
    void addCategory() {
    }

    @Test
    void removeCategory() {
    }
}
