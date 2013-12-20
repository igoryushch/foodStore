package ua.foodstore.entities;

import java.io.Serializable;
import java.math.BigDecimal;

import javax.persistence.*;
import javax.xml.bind.annotation.XmlRootElement;

@Entity
@Table(name = "ORDERITEM")
@XmlRootElement
@NamedQueries(
        {
    @NamedQuery(name = "OrderItem.findAll", query = "SELECT o FROM OrderItem o"),
    @NamedQuery(name = "OrderItem.findById", query = "SELECT o FROM OrderItem o WHERE o.id = :id"),
    @NamedQuery(name = "OrderItem.findByOrder", query = "SELECT o FROM OrderItem o WHERE o.order = :order"),
    @NamedQuery(name = "OrderItem.findByMenuposition", query = "SELECT o FROM OrderItem o WHERE o.position = :position"),
    @NamedQuery(name = "OrderItem.findByReady", query = "SELECT o FROM OrderItem o WHERE o.ready = :ready"),
    @NamedQuery(name = "OrderItem.findNotReadyOrdered", query = "SELECT o FROM OrderItem o WHERE o.ready = 0 ORDER BY o.order.orderDate, o.id")
})
public class OrderItem implements Serializable
{

    private static final long serialVersionUID = 1L;
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;
    @ManyToOne
    @JoinColumn(name = "orderId")
    private PurchaseOrder order;
    @ManyToOne
    @JoinColumn(name = "menupositionid")
    private MenuPosition position;
    private int quantity;
    private BigDecimal price;
    private BigDecimal amount;
    private int ready;

    /**
     * @param id
     * @param order
     * @param position
     * @param quantity
     * @param price
     * @param amount
     * @param ready
     */
    public OrderItem( int id, PurchaseOrder order, MenuPosition position, int quantity,
                      BigDecimal price, BigDecimal amount, int ready )
    {
        this.id = id;
        this.order = order;
        this.position = position;
        this.quantity = quantity;
        this.price = price;
        this.amount = amount;
        this.ready = ready;
    }

    /**
     *
     */
    public OrderItem()
    {
    }

    /**
     * @return the id
     */
    public int getId()
    {
        return id;
    }

    /**
     * @param id the id to set
     */
    public void setId( int id )
    {
        this.id = id;
    }

    /**
     * @return the order
     */
    public PurchaseOrder getOrder()
    {
        return order;
    }

    /**
     * @param order the order to set
     */
    public void setOrder( PurchaseOrder order )
    {
        this.order = order;
    }

    /**
     * @return the position
     */
    public MenuPosition getPosition()
    {
        return position;
    }

    /**
     * @param position the position to set
     */
    public void setPosition( MenuPosition position )
    {
        this.position = position;
    }

    /**
     * @return the quantity
     */
    public int getQuantity()
    {
        return quantity;
    }

    /**
     * @param quantity the quantity to set
     */
    public void setQuantity( int quantity )
    {
        this.quantity = quantity;
    }

    /**
     * @return the price
     */
    public BigDecimal getPrice()
    {
        return price;
    }

    /**
     * @param price the price to set
     */
    public void setPrice( BigDecimal price )
    {
        this.price = price;
    }

    /**
     * @return the amount
     */
    public BigDecimal getAmount()
    {
        return amount;
    }

    /**
     * @param amount the amount to set
     */
    public void setAmount( BigDecimal amount )
    {
        this.amount = amount;
    }

    /**
     * @return the ready
     */
    public int getReady()
    {
        return ready;
    }

    /**
     * @param ready the ready to set
     */
    public void setReady( int ready )
    {
        this.ready = ready;
    }
    /* (non-Javadoc)
     * @see java.lang.Object#toString()
     */

    @Override
    public String toString()
    {
        StringBuilder builder = new StringBuilder();
        builder.append( "Order item [id=" );
        builder.append( id );
        builder.append( ", order=" );
        builder.append( order );
        builder.append( ", position=" );
        builder.append( position );
        builder.append( ", quantity=" );
        builder.append( quantity );
        builder.append( ", price=" );
        builder.append( price );
        builder.append( ", amount=" );
        builder.append( amount );
        builder.append( ", ready=" );
        builder.append( ready );
        builder.append( "]" );
        return builder.toString();
    }


    /* (non-Javadoc)
     * @see java.lang.Object#equals(java.lang.Object)
     */
    @Override
    public boolean equals( Object obj )
    {
        if ( this == obj )
        {
            return true;
        }
        if ( obj == null )
        {
            return false;
        }
        if ( !( obj instanceof OrderItem ) )
        {
            return false;
        }
        OrderItem other = (OrderItem) obj;
        if ( amount == null )
        {
            if ( other.amount != null )
            {
                return false;
            }
        }
        else if ( amount.compareTo( other.amount ) != 0 )
        {
            return false;
        }
        if ( id != other.id )
        {
            return false;
        }
        if ( order == null )
        {
            if ( other.order != null )
            {
                return false;
            }
        }
        else if ( !order.equals( other.order ) )
        {
            return false;
        }
        if ( position == null )
        {
            if ( other.position != null )
            {
                return false;
            }
        }
        else if ( !position.equals( other.position ) )
        {
            return false;
        }
        if ( price == null )
        {
            if ( other.price != null )
            {
                return false;
            }
        }
        else if ( price.compareTo( other.price ) != 0 )
        {
            return false;
        }
        if ( quantity != other.quantity )
        {
            return false;
        }
        if ( ready != other.ready )
        {
            return false;
        }
        return true;
    }
}
