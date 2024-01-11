POST http://localhost:8088/api/Appointments
{
    "endDateTimeGMT": "2024-01-20 14:00:00",
    "status": 0,
    "customerId": 101,
    "order": {
        "staffUserId": 1,
        "items": [
            {"name":"steak", "quantity": 2, "priceOfUnit": 1.2},
            {"name":"coke", "quantity": 1, "priceOfUnit": 300.2}
        ],
        "startDateTimeGMT": "2024-01-20 14:00:00"
    }
}
HTTP 201
[Captures]
id1: jsonpath "$.id"

POST http://localhost:8088/api/Appointments
{
    "endDateTimeGMT": "2024-01-20 16:00",
    "status": 0,
    "customerId": 102,
    "order": {
        "staffUserId": 2,
        "items": [
            {"name":"eggs", "quantity": 3, "priceOfUnit": 0.9},
            {"name":"milkshake", "quantity": 1, "priceOfUnit": 0.6}
        ],
        "startDateTimeGMT": "2024-01-20 16:00"
    }
}
HTTP 201
[Captures]
id2: jsonpath "$.id"

POST http://localhost:8088/api/Appointments
{
    "endDateTimeGMT": "2024-01-20 18:00",
    "status": 0,
    "customerId": 103,
    "order": {
        "staffUserId": 2,
        "items": [
            {"name":"eggs", "quantity": 3, "priceOfUnit": 0.9},
            {"name":"milkshake", "quantity": 1, "priceOfUnit": 0.6}
        ],
        "startDateTimeGMT": "2024-01-20 18:00"
    }
}

HTTP 201
[Captures]
id3: jsonpath "$.id"

POST http://localhost:8088/api/Appointments
{
    "endDateTimeGMT": "2024-01-20 17:00",
    "status": 0,
    "customerId": 104,
    "order": {
        "staffUserId": 3,
        "items": [
            {"name":"coffee", "quantity": 1, "priceOfUnit": 0.7}
        ],
        "startDateTimeGMT": "2024-01-20 17:00"
    }
}
HTTP 201
[Captures]
id4: jsonpath "$.id"


GET http://localhost:8088/api/Appointments
HTTP 200
[Asserts]
jsonpath "$" count == 4
jsonpath "$[0].customerId" == 101
jsonpath "$[1].customerId" == 102
jsonpath "$[2].customerId" == 104
# ascending order by endTime
jsonpath "$[3].customerId" == 103

GET http://localhost:8088/api/Appointments
[QueryStringParams]
fromDateTimeGMT: 2024-01-20 15:00
HTTP 200
[Asserts]
jsonpath "$" count == 3
jsonpath "$[0].customerId" == 102
jsonpath "$[1].customerId" == 104
jsonpath "$[2].customerId" == 103

GET http://localhost:8088/api/Appointments
[QueryStringParams]
tillDateTimeGMT: 2024-01-20 15:00
HTTP 200
[Asserts]
jsonpath "$" count == 1
jsonpath "$[0].customerId" == 101

GET http://localhost:8088/api/Appointments
[QueryStringParams]
fromDateTimeGMT: 2024-01-20 15:00
tillDateTimeGMT: 2024-01-20 16:30
HTTP 200
[Asserts]
jsonpath "$" count == 1
jsonpath "$[0].customerId" == 102

GET http://localhost:8088/api/Appointments
[QueryStringParams]
staffUserId: 2
HTTP 200
[Asserts]
jsonpath "$" count == 2
jsonpath "$[0].customerId" == 102
jsonpath "$[1].customerId" == 103

DELETE http://localhost:8088/api/Appointments/{{id1}}
DELETE http://localhost:8088/api/Appointments/{{id2}}
DELETE http://localhost:8088/api/Appointments/{{id3}}
DELETE http://localhost:8088/api/Appointments/{{id4}}

GET http://localhost:8088/api/Appointments
HTTP 200
[Asserts]
jsonpath "$" count == 0