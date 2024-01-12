POST http://localhost:8088/api/Orders
{
    "staffUserId": 2,
    "items": [
        {"name":"steak", "quantity": 2, "priceOfUnit": 1.2},
        {"name":"coke", "quantity": 1, "priceOfUnit": 300.2}
    ]
}
HTTP 201
[Captures]
id: jsonpath "$.id"

PUT http://localhost:8088/api/Orders/{{id}}
{
    "staffUserId": 3,
    "items": [
        {"name":"sprite", "quantity": 1, "priceOfUnit": 1.2}
    ]
}
HTTP 200

GET http://localhost:8088/api/Orders/{{id}}
HTTP 200
[Asserts]
jsonpath "$.staffUserId" == 3
jsonpath "$.items" count == 3
jsonpath "$.items[2].name" == "sprite"

DELETE http://localhost:8088/api/Orders/{{id}}

GET http://localhost:8088/api/Orders/{{id}}
HTTP 404