POST http://localhost:8080/api/Customers
{
    "id": 4,
    "email": "asd",
    "loyaltyPoints": "23",
    "loyaltyLevel": "Level",
    "name": "asd"
}
HTTP 201
[Captures]
id: jsonpath "$.id"

GET http://localhost:8080/api/Customers/{{id}}
HTTP 200
[Asserts]
jsonpath "$.email" == "asd"
jsonpath "$.loyaltyLevel" == "Level"
jsonpath "$.loyaltyPoints" == 23
jsonpath "$.name" == "asd"

DELETE http://localhost:8080/api/Customers/{{id}}

GET http://localhost:8080/api/Customers/{{id}}
HTTP 404
