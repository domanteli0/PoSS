### Test GET /{id}  &&  POST &&  PUT ###
POST http://localhost:8080/api/Staff
{
    "email": "asd",
    "name": "name",
    "password": "password"
}
HTTP 201
[Captures]
id: jsonpath "$.id"
[Asserts]
#jsonpath "$.password" not exists
#jsonpath "$.passwordHash" not exists

PUT http://localhost:8080/api/Staff/{{id}}
{
    "email": "1@example.com"
}
HTTP 200
[Asserts]
#jsonpath "$.password" not exists
#jsonpath "$.passwordHash" not exists

GET http://localhost:8080/api/Staff/{{id}}
HTTP 200
[Asserts]
jsonpath "$.email" == "1@example.com"
jsonpath "$.name" == "name"
#jsonpath "$.password" not exists
#jsonpath "$.passwordHash" not exists

### Test DELETE ###
DELETE http://localhost:8080/api/Staff/{{id}}

GET http://localhost:8080/api/Staff/{{id}}
HTTP 404
