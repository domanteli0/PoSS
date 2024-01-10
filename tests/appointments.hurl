POST http://localhost:8088/api/Appointments
{
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
id: jsonpath "$.id"

PUT http://localhost:8088/api/Appointments/{{id}}
{
    "durationMinutes": 60
}
HTTP 200

GET http://localhost:8088/api/Appointments/{{id}}
HTTP 200
[Asserts]
jsonpath "$.durationMinutes" == 60

DELETE http://localhost:8088/api/Appointments/{{id}}

GET http://localhost:8088/api/Appointments/{{id}}
HTTP 404