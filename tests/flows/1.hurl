###  PRODUCT, INVENTORY & STAFF SET-UP  ###
POST http://localhost:8080/api/Products
{
  "category": "TODO-1",
  "name": "Pizza",
  "price": 1100
}
HTTP 201
[Captures]
product-id1: jsonpath "$.id"

POST http://localhost:8080/api/Products
{
  "category": "TODO-1",
  "name": "Blynai",
  "price": 780
}
HTTP 201
[Captures]
product-id2: jsonpath "$.id"

POST http://localhost:8080/api/Products
{
  "category": "TODO-2",
  "name": "Alus",
  "price": 320
}
HTTP 201
[Captures]
product-id3: jsonpath "$.id"

POST http://localhost:8080/api/Inventory
{
  "product_id": {{product-id1}},
  "stockQuantity": 100
}
HTTP 201

POST http://localhost:8080/api/Inventory
{
  "product_id": {{product-id2}},
  "stockQuantity": 50
}
HTTP 201

POST http://localhost:8080/api/Inventory
{
  "product_id": {{product-id3}},
  "stockQuantity": 5
}
HTTP 201

POST http://localhost:8080/api/Staff
{
  "name": "Vardenis Pavardenis",
  "email": "example@example.com",
  "password": "password"
}
HTTP 201
[Captures]
staff-id: jsonpath "$.id"


###  Create Order  ###

#POST http://localhost:8080/api/OrderItem
#{
#  "name": "Blynai",
#  "quantity": 1,
#  "priceOfUnit": 780
#}
#HTTP 201

#POST http://localhost:8080/api/OrderItem
#{
#  "name": "Alus",
#  "quantity": 2,
#  "priceOfUnit": 320
#}

POST http://localhost:8080/api/Orders
{
  "staffUserId": {{staff-id}},
  "items": [
    {
      "name": "Alus",
      "quantity": 2,
      "priceOfUnit": 320
    },
    {
      "name": "Blynai",
      "quantity": 1,
      "priceOfUnit": 780
    }
  ],
  "status": "ACTIVE"
}
HTTP 201

###  ADD ITEMS TO THE ORDER ###

# TODO: implement add product
# TODO: implement remove product

