package exA.a10;


class Product{

    private String code;
    private String name;
    private String price;

    public String getCode(){
        return this.code;
    }

    public String getName(){
        return this.name;
    }

    public String getPrice(){
        return this.price;
    }

    Product (String code,String name, String price){
        this.code = code;
        this.name = name;
        this.price = price;
    }
}