package ua.foodstore.entities;

import java.io.Serializable;
import javax.persistence.*;

import java.math.BigDecimal;
import javax.xml.bind.annotation.XmlRootElement;

@Entity
@Table(name = "MENUPOSITION")
@XmlRootElement
@NamedQueries(
        {
    @NamedQuery(name = "MenuPosition.findAll", query = "SELECT m FROM MenuPosition m"),
    @NamedQuery(name = "MenuPosition.findById", query = "SELECT m FROM MenuPosition m WHERE m.id = :id"),
    @NamedQuery(name = "MenuPosition.findByName", query = "SELECT m FROM MenuPosition m WHERE m.name = :name"),
    @NamedQuery(name = "MenuPosition.findByMenuCategory", query = "SELECT m FROM MenuPosition m WHERE m.category = :category"),
    @NamedQuery(name = "MenuPosition.findByKitchenmade", query = "SELECT m FROM MenuPosition m WHERE m.kitchenmade = :kitchenmade"),
    @NamedQuery(name = "MenuPosition.findByActive", query = "SELECT m FROM MenuPosition m WHERE m.active = :active")
})
public class MenuPosition implements Serializable {

    private static final long serialVersionUID = 1L;
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;
    private String name;
    private String description;
    @ManyToOne
    @JoinColumn(name = "menucategoryid")
    private MenuCategory category;
    private int weight;
    private BigDecimal price;
    private int kitchenmade;
    private int active;
    private String imagePath;
    @Transient
    //@Version
    private long version;

    public MenuPosition() {
    }

    /**
     * @param id
     * @param name
     * @param description
     * @param category
     * @param weight
     * @param price
     * @param kitchenmade
     */
    public MenuPosition(int id, String name, String description,
            MenuCategory category, int weight, BigDecimal price,
            int kitchenmade, int active) {
        this.id = id;
        this.name = name;
        this.description = description;
        this.category = category;
        this.weight = weight;
        this.price = price;
        this.kitchenmade = kitchenmade;
        this.active = active;
    }

    /**
     * @return the id
     */
    public int getId() {
        return id;
    }

    /**
     * @param id the id to set
     */
    public void setId(int id) {
        this.id = id;
    }

    /**
     * @return the name
     */
    public String getName() {
        return name;
    }

    /**
     * @param name the name to set
     */
    public void setName(String name) {
        this.name = name;
    }

    /**
     * @return the description
     */
    public String getDescription() {
        return description;
    }

    /**
     * @param description the description to set
     */
    public void setDescription(String description) {
        this.description = description;
    }

    /**
     * @return the category
     */
    public MenuCategory getCategory() {
        return category;
    }

    /**
     * @param category the category to set
     */
    public void setCategory(MenuCategory category) {
        this.category = category;
    }

    /**
     * @return the weight
     */
    public int getWeight() {
        return weight;
    }

    /**
     * @param weight the weight to set
     */
    public void setWeight(int weight) {
        this.weight = weight;
    }

    /**
     * @return the price
     */
    public BigDecimal getPrice() {
        return price;
    }

    /**
     * @param price the price to set
     */
    public void setPrice(BigDecimal price) {
        this.price = price;
    }

    /**
     * @return the kitchenmade
     */
    public int getKitchenmade() {
        return kitchenmade;
    }

    /**
     * @param kitchenmade the kitchenmade to set
     */
    public void setKitchenmade(int kitchenmade) {
        this.kitchenmade = kitchenmade;
    }

    public int getActive() {
        return active;
    }

    public void setActive(int active) {
        this.active = active;
    }

    public String getImagePath() {
        return imagePath;
    }

    public void setImagePath(String imagePath) {
        this.imagePath = imagePath;
    }

    public long getVersion() {
        return version;
    }

    public void setVersion(long version) {
        this.version = version;
    }
    
    /* (non-Javadoc)
     * @see java.lang.Object#equals(java.lang.Object)
     */
    @Override
    public boolean equals(Object obj) {
        if (this == obj) {
            return true;
        }
        if (obj == null) {
            return false;
        }
        if (!(obj instanceof MenuPosition)) {
            return false;
        }
        MenuPosition other = (MenuPosition) obj;
        if (category == null) {
            if (other.category != null) {
                return false;
            }
        } else if (!category.equals(other.category)) {
            return false;
        }
        if (description == null) {
            if (other.description != null) {
                return false;
            }
        } else if (!description.equals(other.description)) {
            return false;
        }
        if (id != other.id) {
            return false;
        }
        if (kitchenmade != other.kitchenmade) {
            return false;
        }
        if (active != other.active) {
            return false;
        }
        if (name == null) {
            if (other.name != null) {
                return false;
            }
        } else if (!name.equals(other.name)) {
            return false;
        }
        if (price == null) {
            if (other.price != null) {
                return false;
            }
        } else if (price.compareTo(other.price) != 0) {
            return false;
        }
        if (weight != other.weight) {
            return false;
        }
        return true;
    }
    /* (non-Javadoc)
     * @see java.lang.Object#toString()
     */

    @Override
    public String toString() {
        StringBuilder builder = new StringBuilder();
        builder.append("MenuPosition [name=");
        builder.append(name);
        builder.append(", category=");
        builder.append(category);
        builder.append(", weight=");
        builder.append(weight);
        builder.append(", price=");
        builder.append(price);
        builder.append(", active=");
        builder.append(active);
        builder.append("]");
        return builder.toString();
    }
}
