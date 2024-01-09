POST http://localhost:8088/api/Orders
{
    "staffUserId": 2,
}
HTTP 201
[Captures]
id: jsonpath "$.id"

PUT http://localhost:8088/api/Orders/{{id}}
{
    "staffUserId": 2
}

GET http://localhost:8088/api/Orders/{{id}}
HTTP 200
[Asserts]
jsonpath "$.staffUserId" == 1

DELETE http://localhost:8088/api/Orders/{{id}}

GET http://localhost:8088/api/Orders/{{id}}
HTTP 404