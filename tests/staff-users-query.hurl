### SET-UP ###
POST http://localhost:8080/api/Staff
{
    "email": "1@example.com",
    "name": "name",
    "password": "password"
}
HTTP 201
[Captures]
id1: jsonpath "$.id"

POST http://localhost:8080/api/Staff
{
    "email": "2@example.com",
    "name": "name",
    "password": "password"
}
HTTP 201
[Captures]
id2: jsonpath "$.id"

### Test GET / ###
GET http://localhost:8080/api/Staff
HTTP 200
[Asserts]
jsonpath "$[*].email" includes "1@example.com"
jsonpath "$[*].email" includes "2@example.com"
jsonpath "$" count == 2

GET http://localhost:8080/api/Staff
[QueryStringParams]
pageSize: 1
HTTP 200
[Asserts]
jsonpath "$" count == 1

GET http://localhost:8080/api/Staff
[QueryStringParams]
email: 1
HTTP 200
[Asserts]
jsonpath "$" count == 1
jsonpath "$[*].email" includes "1@example.com"
jsonpath "$[*].email" not includes "2@example.com"

GET http://localhost:8080/api/Staff
[QueryStringParams]
name: nam
HTTP 200
[Asserts]
jsonpath "$" count == 2
jsonpath "$[*].email" includes "1@example.com"
jsonpath "$[*].email" includes "2@example.com"

### Clean-up ###
DELETE http://localhost:8080/api/Staff/{{id1}}
DELETE http://localhost:8080/api/Staff/{{id2}}
