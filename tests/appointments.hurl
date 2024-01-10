POST http://localhost:8088/api/OrderItems
{
   "name":"salad",
   "quantity": 1,
   "priceOfUnit": 1.2
}
HTTP 201
[Captures]
id: jsonpath "$.id"
steak_id: jsonpath "$.items[0].id"

PUT http://localhost:8088/api/Orders/{{id}}
{
    "quantity": 2
    "priceOfUnit": 1.3
}
HTTP 200

GET http://localhost:8088/api/Orders/{{id}}
HTTP 200
[Asserts]
jsonpath "$.quantity" == 2
jsonpath "$.priceOfUnit" == 1.3

DELETE http://localhost:8088/api/Orders/{{id}}

GET http://localhost:8088/api/Orders/{{id}}
HTTP 404