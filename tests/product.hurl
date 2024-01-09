# Test POST /api/Products
POST http://localhost:8080/api/Products
{
  "category": "string",
  "name": "string",
  "price": 0
}
HTTP 201
[Captures]
id: jsonpath "$.id"
[Asserts]
jsonpath "$.category" == "string"
jsonpath "$.name" == "string"
jsonpath "$.price" == 0

# Test PUT /api/Products/{{id}}
PUT http://localhost:8080/api/Products/{{id}}
{
   "category": "updatedCategory",
   "name": "updatedName",
   "price": 10
}
HTTP 200
[Asserts]
jsonpath "$.category" == "updatedCategory"
jsonpath "$.name" == "updatedName"
jsonpath "$.price" == 10

# Test GET /api/Products/{{id}}
GET http://localhost:8080/api/Products/{{id}}
HTTP 200
[Asserts]
jsonpath "$.category" == "updatedCategory"
jsonpath "$.name" == "updatedName"
jsonpath "$.price" == 10

# Test DELETE /api/Products/{{id}}
DELETE http://localhost:8080/api/Products/{{id}}
HTTP 200

# Test GET /api/Products/{{id}} after DELETE
GET http://localhost:8080/api/Products/{{id}}
HTTP 404
