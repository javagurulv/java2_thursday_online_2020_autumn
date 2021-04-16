package store.entity.orders;

import javax.persistence.CollectionTable;
import javax.persistence.Column;
import javax.persistence.ElementCollection;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.Table;

import java.util.List;

@Entity
@Table(name = "orders")
public class Order {

    @Id
    private Long orderId;

    @ElementCollection
    @CollectionTable(name = "order_wares", joinColumns = @JoinColumn(name = "orderId"))
    @Column(name = "itemId")
    private List<Long> itemList;

    private String email;

    private String address;

    private String phone;

    @Column(name ="addInfo")
    private String additionalInformation;

    public Order() {
    }

    public Order(Long orderId, List<Long> itemList, String email, String address, String phone, String additionalInformation) {
        this.orderId = orderId;
        this.itemList = itemList;
        this.email = email;
        this.address = address;
        this.phone = phone;
        this.additionalInformation = additionalInformation;
    }

    public Long getOrderId() {
        return orderId;
    }

    public List<Long> getItemList() {
        return itemList;
    }

    public void setItemList(List<Long> itemList) {
        this.itemList = itemList;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    public String getPhone() {
        return phone;
    }

    public void setPhone(String phone) {
        this.phone = phone;
    }

    public String getAdditionalInformation() {
        return additionalInformation;
    }

    public void setAdditionalInformation(String additionalInformation) {
        this.additionalInformation = additionalInformation;
    }
}
