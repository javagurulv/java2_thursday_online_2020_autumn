package store.dtos;

import store.entity.orders.Order;

public class OrderDTO {
    private Long orderId;
    private String email;
    private String address;
    private String phone;
    private String additionalInformation;

    public OrderDTO() {
    }

    public OrderDTO(String email, String address, String phone, String additionalInformation) {
        this.email = email;
        this.address = address;
        this.phone = phone;
        this.additionalInformation = additionalInformation;
    }

    public OrderDTO(Order order) {
        this.orderId = order.getOrderId();
        this.address = order.getAddress();
        this.email = order.getEmail();
        this.phone = order.getPhone();
        this.additionalInformation = order.getAdditionalInformation();
    }


    public Long getOrderId() {
        return orderId;
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
