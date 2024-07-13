package unitTesting.ProductManagement;

import com.isnapgaming.ProductManagement.Product;

import org.junit.jupiter.api.Test;

import static com.isnapgaming.ProductManagement.Product.Category.RPG;
import static com.isnapgaming.ProductManagement.Product.Pegi.PEGI18;
import static com.isnapgaming.ProductManagement.Product.Platform.PC;
import static org.junit.jupiter.api.Assertions.*;

public class ProductTest {
    @Test
    void makeProduct_P1_N1_SH1_PL1_PR1_Q1_C1_PE1_RY1_IP1() {
        int prodCode=1001;
        String name= "The Witcher 3: Wild Hunt";
        String softwareHouse= "CD";
        Product.Platform platform= PC;
        int price  = 50;
        int quantity  = 10;
        Product.Category category =RPG;
        Product.Pegi pegi=PEGI18;
        int releaseYear=2015;
        String imagePath="images/the_witcher_3.jpg";
        boolean available=true;
        Product product;

        try {
            product=Product.makeProduct(prodCode,name,softwareHouse,platform,price,quantity,category,pegi,releaseYear,imagePath,available);
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
        assertEquals(name,product.getName());
        assertEquals(softwareHouse,product.getSoftwareHouse());
        assertEquals(platform,product.getPlatform());
        assertEquals(price,product.getPrice());
        assertEquals(quantity,product.getQuantity());
        assertEquals(category,product.getCategory());
        assertEquals(releaseYear,product.getReleaseYear());
        assertEquals(imagePath,product.getImagePath());
        assertEquals(available,product.isAvailable());
    }

    @Test
    void makeProduct_P1_N1_SH1_PL1_PR1_Q1_C1_PE1_RY2_IP2()throws IllegalArgumentException  {
        int prodCode=1001;
        String name= "The Witcher 3: Wild Hunt";
        String softwareHouse= "CD";
        Product.Platform platform= PC;
        int price  = 50;
        int quantity  = 10;
        Product.Category category =RPG;
        Product.Pegi pegi=PEGI18;
        int releaseYear=-1;
        String imagePath=null;
        boolean available=true;
        assertThrows(Exception.class,()->Product.makeProduct(prodCode,name,softwareHouse,platform,price,quantity,category,pegi,releaseYear,imagePath,available));

    }
    @Test
    void makeProduct_P1_N1_SH1_PL1_PR1_Q1_C1_PE1_RY2_IP1()throws IllegalArgumentException  {
        int prodCode=1001;
        String name= "The Witcher 3: Wild Hunt";
        String softwareHouse= "CD";
        Product.Platform platform= PC;
        int price  = 50;
        int quantity  = 10;
        Product.Category category =RPG;
        Product.Pegi pegi=PEGI18;
        int releaseYear=-1;
        String imagePath="images/the_witcher_3.jpg";
        boolean available=true;
        assertThrows(Exception.class,()->Product.makeProduct(prodCode,name,softwareHouse,platform,price,quantity,category,pegi,releaseYear,imagePath,available));

    }
    @Test
    void makeProduct_P1_N1_SH1_PL1_PR1_Q1_C1_PE2_RY1_IP1()throws IllegalArgumentException  {
        int prodCode=1001;
        String name= "The Witcher 3: Wild Hunt";
        String softwareHouse= "CD";
        Product.Platform platform= PC;
        int price  = 50;
        int quantity  = 10;
        Product.Category category =RPG;
        Product.Pegi pegi=null;
        int releaseYear=2015;
        String imagePath="images/the_witcher_3.jpg";
        boolean available=true;
        assertThrows(Exception.class,()->Product.makeProduct(prodCode,name,softwareHouse,platform,price,quantity,category,pegi,releaseYear,imagePath,available));

    }

    @Test
    void makeProduct_P1_N1_SH1_PL1_PR1_Q1_C2_PE1_RY1_IP1()throws IllegalArgumentException  {
        int prodCode=1001;
        String name= "The Witcher 3: Wild Hunt";
        String softwareHouse= "CD";
        Product.Platform platform= PC;
        int price  = 50;
        int quantity  = 10;
        Product.Category category =null;
        Product.Pegi pegi=PEGI18;
        int releaseYear=2015;
        String imagePath="images/the_witcher_3.jpg";
        boolean available=true;
        assertThrows(Exception.class,()->Product.makeProduct(prodCode,name,softwareHouse,platform,price,quantity,category,pegi,releaseYear,imagePath,available));

    }
    @Test
    void makeProduct_P1_N1_SH1_PL1_PR1_Q2_C1_PE1_RY1_IP1()throws IllegalArgumentException  {
        int prodCode=1001;
        String name= "The Witcher 3: Wild Hunt";
        String softwareHouse= "CD";
        Product.Platform platform= PC;
        int price  = 50;
        int quantity  =-1 ;
        Product.Category category =RPG;
        Product.Pegi pegi=PEGI18;
        int releaseYear=2015;
        String imagePath="images/the_witcher_3.jpg";
        boolean available=true;
        assertThrows(Exception.class,()->Product.makeProduct(prodCode,name,softwareHouse,platform,price,quantity,category,pegi,releaseYear,imagePath,available));

    }
    @Test
    void makeProduct_P1_N1_SH1_PL1_PR2_Q1_C1_PE1_RY1_IP1()throws IllegalArgumentException  {
        int prodCode=1001;
        String name= "The Witcher 3: Wild Hunt";
        String softwareHouse= "CD";
        Product.Platform platform= PC;
        int price  = -1;
        int quantity  =10 ;
        Product.Category category =RPG;
        Product.Pegi pegi=PEGI18;
        int releaseYear=2015;
        String imagePath="images/the_witcher_3.jpg";
        boolean available=true;
        assertThrows(Exception.class,()->Product.makeProduct(prodCode,name,softwareHouse,platform,price,quantity,category,pegi,releaseYear,imagePath,available));

    }
    @Test
    void makeProduct_P1_N1_SH1_PL2_PR1_Q1_C1_PE1_RY1_IP1()throws IllegalArgumentException  {
        int prodCode=1001;
        String name= "The Witcher 3: Wild Hunt";
        String softwareHouse= "CD";
        Product.Platform platform= null;
        int price  = 50;
        int quantity  =10 ;
        Product.Category category =RPG;
        Product.Pegi pegi=PEGI18;
        int releaseYear=2015;
        String imagePath="images/the_witcher_3.jpg";
        boolean available=true;
        assertThrows(Exception.class,()->Product.makeProduct(prodCode,name,softwareHouse,platform,price,quantity,category,pegi,releaseYear,imagePath,available));

    }
    @Test
    void makeProduct_P1_N1_SH2_PL1_PR1_Q1_C1_PE1_RY1_IP1()throws IllegalArgumentException  {
        int prodCode=1001;
        String name= "The Witcher 3: Wild Hunt";
        String softwareHouse= null;
        Product.Platform platform= PC;
        int price  = 50;
        int quantity  =10 ;
        Product.Category category =RPG;
        Product.Pegi pegi=PEGI18;
        int releaseYear=2015;
        String imagePath="images/the_witcher_3.jpg";
        boolean available=true;
        assertThrows(Exception.class,()->Product.makeProduct(prodCode,name,softwareHouse,platform,price,quantity,category,pegi,releaseYear,imagePath,available));

    }
    @Test
    void makeProduct_P1_N2_SH1_PL1_PR1_Q1_C1_PE1_RY1_IP1()throws IllegalArgumentException  {
        int prodCode=1001;
        String name= null;
        String softwareHouse= "CD";
        Product.Platform platform= PC;
        int price  = 50;
        int quantity  =10 ;
        Product.Category category =RPG;
        Product.Pegi pegi=PEGI18;
        int releaseYear=2015;
        String imagePath="images/the_witcher_3.jpg";
        boolean available=true;
        assertThrows(Exception.class,()->Product.makeProduct(prodCode,name,softwareHouse,platform,price,quantity,category,pegi,releaseYear,imagePath,available));

    }
    @Test
    void makeProduct_P2_N1_SH1_PL1_PR1_Q1_C1_PE1_RY1_IP1()throws IllegalArgumentException  {
        int prodCode=0;
        String name= "The Witcher 3: Wild Hunt";
        String softwareHouse= "CD";
        Product.Platform platform= PC;
        int price  = 50;
        int quantity  =10 ;
        Product.Category category =RPG;
        Product.Pegi pegi=PEGI18;
        int releaseYear=2015;
        String imagePath="images/the_witcher_3.jpg";
        boolean available=true;
        assertThrows(Exception.class,()->Product.makeProduct(prodCode,name,softwareHouse,platform,price,quantity,category,pegi,releaseYear,imagePath,available));

    }

}
