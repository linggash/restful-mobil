# API SPEC

## Create Mobil

Request :
- Method : POST
- Endpoint : `/api/products`
- Header :
  - Content-Type: application/json
  - Accept: application/json
- Body : 

```json
{
  "id" : "string, unique",
  "name" : "string",
  "brand" : "string",
  "price" : "long",
  "performance" : "integer",
  "security" : "integer",
  "convenience" : "integer",
  "appearance" : "integer",
  "efficiency" : "integer"
}
```

Response : 

```json
{
  "code" : "number",
  "status" : "string",
  "data" :{
    "id" : "string, unique",
    "name" : "string",
    "brand" : "string",
    "price" : "long",
    "performance" : "integer",
    "security" : "integer",
    "convenience" : "integer",
    "appearance" : "integer",
    "efficiency" : "integer",
    "createdAt" : "date",
    "updatedAt" : "date"
  }
}
```

## Get Mobil

Request :
- Method : GET
- Endpoint : `/api/products/{id_product}`
- Header :
    - Accept: application/json

Response :

```json
{
  "code" : "number",
  "status" : "string",
  "data" :{
    "id" : "string, unique",
    "name" : "string",
    "brand" : "string",
    "price" : "long",
    "performance" : "integer",
    "security" : "integer",
    "convenience" : "integer",
    "appearance" : "integer",
    "efficiency" : "integer",
    "createdAt" : "date",
    "updatedAt" : "date"
  }
}
```

## Update Mobil

Request :
- Method : PUT
- Endpoint : `/api/products/{id_product}`
- Header :
    - Content-Type: application/json
    - Accept: application/json
- Body :

```json
{
  "name" : "string",
  "brand" : "string",
  "price" : "long",
  "performance" : "integer",
  "security" : "integer",
  "convenience" : "integer",
  "appearance" : "integer",
  "efficiency" : "integer"
}
```

Response :

```json
{
  "code" : "number",
  "status" : "string",
  "data" :{
    "id" : "string, unique",
    "name" : "string",
    "brand" : "string",
    "price" : "long",
    "performance" : "integer",
    "security" : "integer",
    "convenience" : "integer",
    "appearance" : "integer",
    "efficiency" : "integer",
    "createdAt" : "date",
    "updatedAt" : "date"
  }
}
```

## List Mobil

Request :
- Method : GET
- Endpoint : `/api/products`
- Header :
    - Accept: application/json
- Query param :
  - size : number,
  - page : number

Response :

```json
{
  "code" : "number",
  "status" : "string",
  "data" : [
    {
      "id" : "string, unique",
      "name" : "string",
      "brand" : "string",
      "price" : "long",
      "performance" : "integer",
      "security" : "integer",
      "convenience" : "integer",
      "appearance" : "integer",
      "efficiency" : "integer",
      "createdAt" : "date",
      "updatedAt" : "date"
    },
    {
      "id" : "string, unique",
      "name" : "string",
      "brand" : "string",
      "price" : "long",
      "performance" : "integer",
      "security" : "integer",
      "convenience" : "integer",
      "appearance" : "integer",
      "efficiency" : "integer",
      "quantity" : "integer",
      "createdAt" : "date",
      "updatedAt" : "date"
    }
  ]
}
```

## Delete Mobil
Request :
- Method : DELETE
- Endpoint : `/api/products/{id_product}`
- Header :
    - Accept: application/json

Response :

```json
{
  "code" : "number",
  "status" : "string"
}
```