# SETUP
POST http://localhost:8080/api/Products
{
  "category": "string",
  "name": "string",
  "price": 0
}
HTTP 201
[Captures]
product_id: jsonpath "$.id"
[Asserts]
jsonpath "$.category" == "string"
jsonpath "$.name" == "string"
jsonpath "$.price" == 0


# Test POST /api/Inventory
POST http://localhost:8080/api/Inventory
{
  "product_id": {{product_id}},
  "stockQuantity": 99
}
HTTP 201
[Captures]
id: jsonpath "$.id"
[Asserts]
jsonpath "$.id" exists
jsonpath "$.stockQuantity" exists

# Test PUT /api/Inventory/{{id}}
PUT http://localhost:8080/api/Inventory/{{id}}
{
   "product_id": {{product_id}},
   "stockQuantity": 100
}
HTTP 200
[Asserts]
jsonpath "$.product.id" exists

# Test GET /api/Inventory/{{id}}
GET http://localhost:8080/api/Inventory/{{id}}
HTTP 200
[Asserts]
jsonpath "$.product" exists

# Test DELETE /api/Inventory/{{id}}
DELETE http://localhost:8080/api/Inventory/{{id}}
HTTP 200

# Test GET /api/Inventory/{{id}} after DELETE
GET http://localhost:8080/api/Inventory/{{id}}
HTTP 404
