package ua.foodstore.managedbeans;

import java.util.ArrayList;
import java.util.List;
import javax.annotation.PostConstruct;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.RequestScoped;

@ManagedBean
@RequestScoped
public class ImagesHolder {

    private List<String> accessibleImages;
    
    public ImagesHolder() {
    }

    public List<String> getAccessibleImages() {
        return accessibleImages;
    }

    public void setAccessibleImages(List<String> accessibleImages) {
        this.accessibleImages = accessibleImages;
    }
    
    
    
    @PostConstruct
    public void init()
    {
        accessibleImages = new ArrayList<String>();
        accessibleImages.add("images/dishes/salad/summersalad.jpg");
        accessibleImages.add("images/dishes/salad/saladavocado.jpg");
        accessibleImages.add("images/dishes/salad/Saladprawn.jpg");
        accessibleImages.add("images/dishes/apetaizer/appetizerchisse.jpeg");
        accessibleImages.add("images/dishes/apetaizer/appetizerprawn.jpg");
        accessibleImages.add("images/dishes/apetaizer/appetizermozarella.jpg");
        accessibleImages.add("images/dishes/apetaizer/appetizercucumber.jpg");
        accessibleImages.add("images/dishes/apetaizer/appetizersalmon.jpg");
        accessibleImages.add("images/dishes/pizza/pizzamargherita.jpeg");
        accessibleImages.add("images/dishes/pizza/pizza4season.jpg");
        accessibleImages.add("images/dishes/pizza/pizzasalami.jpg");
        accessibleImages.add("images/dishes/pizza/pizzaseafood.jpg");
        accessibleImages.add("images/dishes/pizza/pizzaquattroformaggi.jpg");
        accessibleImages.add("images/dishes/desert/tiramisu.jpg");
        accessibleImages.add("images/dishes/desert/Ice_Cream.jpg");
        accessibleImages.add("images/dishes/desert/milkjelly.jpeg");
        accessibleImages.add("images/dishes/desert/fruitsalad.jpg");
        accessibleImages.add("images/dishes/desert/cheedecake.jpeg");
        accessibleImages.add("images/dishes/drinks/tea.jpg");
        accessibleImages.add("images/dishes/drinks/kofe.jpg");
        accessibleImages.add("images/dishes/drinks/koka kola.jpg");
        accessibleImages.add("images/dishes/drinks/voda.jpg");
        accessibleImages.add("images/dishes/drinks/juice.jpeg");

        accessibleImages.add("images/dishes/drinks/beers.jpeg");
        accessibleImages.add("images/dishes/drinks/latte.jpeg");
       
    }
}
