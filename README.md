# HTTP VERBS

The HTTP verbs comprise a major portion of our “uniform interface” constraint and provide us the action counterpart to the noun-based resource. 
The primary or most-commonly-used HTTP verbs (or methods, as they are properly called) are POST, GET, PUT, PATCH, and DELETE. 
These correspond to create, read, update, and delete (or CRUD) operations, respectively. 
There are a number of other verbs, too, but are utilized less frequently. Of those less-frequent methods, OPTIONS and HEAD are used more often than others. [[1]]

| HTTP Verb | CRUD | Entire Collection (e.g. /customers) | Specific Item (e.g. /customers/{id}) |
| --- | --- | --- | --- |
| POST	| Create        | 201 (Created), 'Location' header with link to /customers/{id} containing new ID.                     | 404 (Not Found), 409 (Conflict) if resource already exists.. |
| GET   | Read          | 200 (OK), list of customers. Use pagination, sorting and filtering to navigate big lists.            | 200 (OK), list of customers. Use pagination, sorting and filtering to navigate big lists. |
| PUT   | Update        | 405 (Method Not Allowed), unless you want to update/replace every resource in the entire collection. | 200 (OK) or 204 (No Content). 404 (Not Found), if ID not found or invalid. |
| PATCH | Update/Modify | 405 (Method Not Allowed), unless you want to modify the collection itself.                           | 200 (OK) or 204 (No Content). 404 (Not Found), if ID not found or invalid. |

# GET
The HTTP GET method is used to **read** (or retrieve) a representation of a resource. In the “happy” (or non-error) path, GET returns a representation in XML or JSON and an HTTP response code of 200 (OK). In an error case, it most often returns a 404 (NOT FOUND) or 400 (BAD REQUEST).

GET http://www.example.com/customers/12345  
GET http://www.example.com/customers/12345/orders  
GET http://www.example.com/buckets/sample  


# POST
The POST verb is most-often utilized to **create** new resources. In particular, it's used to create subordinate resources. That is, subordinate to some other (e.g. parent) resource. In other words, when creating a new resource, POST to the parent and the service takes care of associating the new resource with the parent, assigning an ID (new resource URI), etc.

POST http://www.example.com/customers  
POST http://www.example.com/customers/12345/orders


# PUT
PUT is most-often utilized for **update** capabilities, PUT-ing to a known resource URI with the request body containing the newly-updated representation of the original resource.

PUT http://www.example.com/customers/12345  
PUT http://www.example.com/customers/12345/orders/  
PUT http://www.example.com/buckets/secret_stuff  

# POST VS PUT

### Idempotent

POST is not idempotent but PUT. 

But what does idempotent mean? In the context of REST APIs, when making multiple identical requests has the same effect as making a single request – then that REST API is called idempotent [[2]].   

Making N POST requests will result in N new resources on the server and contain the same information. However, making N PUT requests will result in only 1 resource on the server. The first request will update the resource; then rest N-1 requests will just overwrite the same resource.

### Resource id
PUT can be used to create a resource in the case where the resource ID is chosen by *the client* instead of by the server. However, when we create a new resource with POST, *the server* determines the resource ID.

# PATCH 
PATCH is used for **modify** capabilities. The PATCH request only needs to contain the changes to the resource, not the complete resource.

PATCH http://www.example.com/customers/12345  
PATCH http://www.example.com/customers/12345/orders/98765  
PATCH http://www.example.com/buckets/secret_stuff  


[1]:https://www.restapitutorial.com/lessons/httpmethods.html
[2]:https://restfulapi.net/idempotent-rest-apis/
[3]:https://nullbeans.com/using-put-vs-patch-when-building-a-rest-api-in-spring/
[4]:https://www.javadevjournal.com/spring/rest/http-put-vs-http-patch-in-a-rest-api/
[5]:https://www.logicbig.com/tutorials/spring-framework/spring-web-mvc/http-patch-test.html
