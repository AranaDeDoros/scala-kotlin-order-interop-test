# Interop experiment between Scala and Kotlin

## roles

### Scala: 
Mocks an event-driven service for orders.
#### endpoints
- __POST /orders__
- __POST /orders/{id}/accept__
- __POST /orders/{id}/cancel__

#### rough arch description
- **OrderDispatcher:** dispatches events.
- **OrderEventLog:** handles logging of events.
- **OrderStore:** used to retrieve orders.

### response samples
```javascript
//GET /orders
[
    {
        "id": 164245611363580875,
        "items": [
            {
                "id": 2372687879875032178,
                "name": "item-Jp39BR",
                "price": 48.13
            },
            {
                "id": 8004487085585549377,
                "name": "item-w5ysmH",
                "price": 15.13
            }
        ],
        "driver": {
            "id": 3264677392163160040,
            "name": "driver-DSBEyw"
        },
        "dateCreated": "2026-01-20T04:33:28.715904400Z",
        "establishment": {
            "id": 1247032226061278965,
            "name": "restaurant-4afxdH",
            "isActive": true
        },
        "status": "CREATED",
        "comments": []
    }
]  

//POST /orders
{
    "id": 4166173235558826795,
    "items": [
        {
            "id": 1644334830942116819,
            "name": "item-zBIqTL",
            "price": 46.95
        },
        {
            "id": 2725660677293329102,
            "name": "item-FfebdZ",
            "price": 32.73
        },
        {
            "id": 1681843759061772175,
            "name": "item-6takUS",
            "price": 8.06
        },
        {
            "id": 1339667779963598606,
            "name": "item-o7Xjb8",
            "price": 34.72
        }
    ],
    "driver": {
        "id": 6889789159513136183,
        "name": "driver-vMru3s"
    },
    "dateCreated": "2026-01-20T04:16:09.728250Z",
    "establishment": {
        "id": 3937712359063777201,
        "name": "restaurant-ksnfvH",
        "isActive": true
    },
    "status": "CREATED",
    "comments": []
}

//POST /accept
{
    "id": 4166173235558826795,
    "items": [
        {
            "id": 1644334830942116819,
            "name": "item-zBIqTL",
            "price": 46.95
        },
        {
            "id": 2725660677293329102,
            "name": "item-FfebdZ",
            "price": 32.73
        },
        {
            "id": 1681843759061772175,
            "name": "item-6takUS",
            "price": 8.06
        },
        {
            "id": 1339667779963598606,
            "name": "item-o7Xjb8",
            "price": 34.72
        }
    ],
    "driver": {
        "id": 6889789159513136183,
        "name": "driver-vMru3s"
    },
    "dateCreated": "2026-01-20T04:16:09.728250Z",
    "establishment": {
        "id": 3937712359063777201,
        "name": "restaurant-ksnfvH",
        "isActive": true
    },
    "status": "ACCEPTED",
    "comments": []
}

//POST /cancel
{
    "id": 4166173235558826795,
    "items": [
        {
            "id": 1644334830942116819,
            "name": "item-zBIqTL",
            "price": 46.95
        },
        {
            "id": 2725660677293329102,
            "name": "item-FfebdZ",
            "price": 32.73
        },
        {
            "id": 1681843759061772175,
            "name": "item-6takUS",
            "price": 8.06
        },
        {
            "id": 1339667779963598606,
            "name": "item-o7Xjb8",
            "price": 34.72
        }
    ],
    "driver": {
        "id": 6889789159513136183,
        "name": "driver-vMru3s"
    },
    "dateCreated": "2026-01-20T04:16:09.728250Z",
    "establishment": {
        "id": 3937712359063777201,
        "name": "restaurant-ksnfvH",
        "isActive": true
    },
    "status": "CANCELLED",
    "comments": []
}
```

---
### kotlin: 
Listen by polling/web sockets (to be defined) orders and do something with them (also to be defined)
