POST http://localhost:8088/api/Appointments
{
    "dateTimeGMT": "2024-01-20 14:00:00",
    "durationMinutes": 30,
    "status": 0,
    "customerId": 101,
    "order": {
        "staffUserId": 2,
        "items": [
            {"name":"steak", "quantity": 2, "priceOfUnit": 1.2},
            {"name":"coke", "quantity": 1, "priceOfUnit": 300.2}
        ],
        "dateTimeGMT": "2024-01-20 14:00:00"
    }
}
HTTP 201
[Captures]
id1: jsonpath "$.id"

POST http://localhost:8088/api/Appointments
{
    "dateTimeGMT": "2024-01-20 16:00",
    "durationMinutes": 50,
    "status": 0,
    "customerId": 102,
    "order": {
        "staffUserId": 2,
        "items": [
            {"name":"eggs", "quantity": 3, "priceOfUnit": 0.9},
            {"name":"milkshake", "quantity": 1, "priceOfUnit": 0.6}
        ],
        "dateTimeGMT": "2024-01-20 16:00"
    }
}
HTTP 201
[Captures]
id2: jsonpath "$.id"

POST http://localhost:8088/api/Appointments
{
    "dateTimeGMT": "2024-01-20 17:00",
    "durationMinutes": 10,
    "status": 0,
    "customerId": 103,
    "order": {
        "staffUserId": 3,
        "items": [
            {"name":"coffee", "quantity": 1, "priceOfUnit": 0.7}
        ],
        "dateTimeGMT": "2024-01-20 17:00"
    }
}
HTTP 201
[Captures]
id3: jsonpath "$.id"


GET http://localhost:8088/api/Appointments
HTTP 200
[Asserts]
jsonpath "$" count == 3
jsonpath "$[0].durationMinutes" == 30
jsonpath "$[1].durationMinutes" == 50
jsonpath "$[2].durationMinutes" == 10

GET http://localhost:8088/api/Appointments
[QueryStringParams]
fromDateTimeGMT: 2024-01-20 15:00
HTTP 200
[Asserts]
jsonpath "$" count == 2
jsonpath "$[0].durationMinutes" == 50
jsonpath "$[1].durationMinutes" == 10

GET http://localhost:8088/api/Appointments
[QueryStringParams]
tillDateTimeGMT: 2024-01-20 15:00
HTTP 200
[Asserts]
jsonpath "$" count == 1
jsonpath "$[0].durationMinutes" == 30

GET http://localhost:8088/api/Appointments
[QueryStringParams]
fromDateTimeGMT: 2024-01-20 15:00
tillDateTimeGMT: 2024-01-20 16:30
HTTP 200
[Asserts]
jsonpath "$" count == 1
jsonpath "$[0].durationMinutes" == 50

DELETE http://localhost:8088/api/Appointments/{{id1}}
DELETE http://localhost:8088/api/Appointments/{{id2}}
DELETE http://localhost:8088/api/Appointments/{{id3}}

GET http://localhost:8088/api/Appointments
HTTP 200
[Asserts]
jsonpath "$" count == 0