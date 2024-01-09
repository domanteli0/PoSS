POST http://localhost:8082/api/Orders
{
    "staffUserId": 1;
    "appointmentId": 2;
}
HTTP 201
[Captures]
id: jsonpath "$.id"

PUT http://localhost:8082/api/Orders/{{id}}
{
    "staffUserId": 3;
}

GET http://localhost:8082/api/Orders/{{id}}
HTTP 200
[Asserts]
jsonpath "$.staffUserId" == 1
jsonpath "$.appointmentId" == 2

DELETE http://localhost:8082/api/Orders/{{id}}

GET http://localhost:8082/api/Orders/{{id}}
HTTP 404