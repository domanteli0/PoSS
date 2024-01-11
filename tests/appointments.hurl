POST http://localhost:8088/api/Appointments
{
    "endDateTimeGMT": "2024-01-20 14:00:00",
    "customerId": 101,
    "order": {
        "staffUserId": 2,
        "items": [
            {"name":"steak", "quantity": 2, "priceOfUnit": 1.2},
            {"name":"coke", "quantity": 1, "priceOfUnit": 300.2}
        ],
        "startDateTimeGMT": "2024-01-20 14:00:00"
    }
}
HTTP 201
[Captures]
id: jsonpath "$.id"

PUT http://localhost:8088/api/Appointments/{{id}}
{
    "customerId": 102
}
HTTP 200

GET http://localhost:8088/api/Appointments/{{id}}
HTTP 200
[Asserts]
jsonpath "$.customerId" == 102

DELETE http://localhost:8088/api/Appointments/{{id}}

GET http://localhost:8088/api/Appointments/{{id}}
HTTP 404