POST http://localhost:8080/api/Orders
{

     paymentType: "cash";

     tax;

     private BigDecimal totalDiscount;

     private BigDecimal price;
    "email": "asd",
    "loyaltyPoints": "23",
    "loyaltyLevel": "Level",
    "name": "asd"
}
HTTP 201
[Captures]
id: jsonpath "$.id"

PUT http://localhost:8080/api/Customers/{{id}}
{
    "email": "example@example.com"
}

GET http://localhost:8080/api/Customers/{{id}}
HTTP 200
[Asserts]
jsonpath "$.email" == "example@example.com"
jsonpath "$.loyaltyLevel" == "Level"
jsonpath "$.loyaltyPoints" == 23
jsonpath "$.name" == "asd"

DELETE http://localhost:8080/api/Customers/{{id}}

GET http://localhost:8080/api/Customers/{{id}}
HTTP 404
