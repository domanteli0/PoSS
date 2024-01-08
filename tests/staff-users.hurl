POST http://localhost:8080/api/Staff
{
    "email": "asd",
    "name": "asd",
    "password": "password"
}
HTTP 201
[Captures]
id: jsonpath "$.id"
password: jsonpath "$.password"

PUT http://localhost:8080/api/Staff/{{id}}
{
    "email": "example@example.com"
}

GET http://localhost:8080/api/Staff/{{id}}
HTTP 200
[Asserts]
jsonpath "$.email" == "example@example.com"
jsonpath "$.name" == "asd"
jsonpath "$.password" == null

DELETE http://localhost:8080/api/Staff/{{id}}

GET http://localhost:8080/api/Staff/{{id}}
HTTP 404
