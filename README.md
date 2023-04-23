# COP5339-project
Grad - class project

setup docker daemon on ur laptop run command to get postgres up java project will setup neccessary tables

docker run --name postgres -e POSTGRES_USER=admin -e POSTGRES_PASSWORD=password -e POSTGRES_DB=project -p 5432:5432 -d postgres


src
└── main
    ├── java
    │   └── com
    │       └── example
    │           └── shopping
    │               ├── controller
    │               │   ├── CustomerController.java
    │               │   ├── ProductController.java
    │               │   └── SellerController.java
    │               ├── model
    │               │   ├── CreditCard.java
    │               │   ├── Customer.java
    │               │   ├── Item.java
    │               │   ├── Product.java
    │               │   ├── ShoppingCart.java
    │               │   └── Seller.java
    │               ├── repository
    │               │   ├── CreditCardRepository.java
    │               │   ├── CustomerRepository.java
    │               │   ├── ItemRepository.java
    │               │   ├── ProductRepository.java
    │               │   ├── ShoppingCartRepository.java
    │               │   └── SellerRepository.java
    │               └── service
    │                   ├── CreditCardService.java
    │                   ├── CustomerService.java
    │                   ├── ItemService.java
    │                   ├── ProductService.java
    │                   ├── ShoppingCartService.java
    │                   └── SellerService.java
    └── resources
        └── application.properties
