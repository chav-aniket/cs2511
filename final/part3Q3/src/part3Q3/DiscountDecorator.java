package part3Q3;

public class DiscountDecorator extends Product {
    private Product product;
    private int discount;

    public DiscountDecorator(Product product, int discount) {
        this.product = product;
        this.discount = discount;
    }

    @Override
    public double getPrice() {
        return product.getPrice() * (100 - discount)/100;
    }

    @Override
    public int getWeight() {
        return product.getWeight();
    }

}