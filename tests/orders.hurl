POST http://localhost:8088/api/Orders
{
    "staffUserId": 2,
    "items": [
        {"name":"steak", "quantity": 2, "priceOfUnit": 1.2},
        {"name":"coke", "quantity": 1, "priceOfUnit": 3.2}
    ]
}
HTTP 201
[Captures]
id: jsonpath "$.id"
steak_id: jsonpath "$.items[0].id"

PUT http://localhost:8088/api/Orders/{{id}}
{
    "staffUserId": 2,
    "items": [
        {"name":"sprite", "quantity": 1, "priceOfUnit": 1.2}
    ]
}

GET http://localhost:8088/api/Orders/{{id}}
HTTP 200
[Asserts]
jsonpath "$.staffUserId" == 2

DELETE http://localhost:8088/api/Orders/{{id}}

GET http://localhost:8088/api/Orders/{{id}}
HTTP 404