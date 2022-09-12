# How to run application

Alternative 1:
In your IDE, select the [Main](src/main/java/Main.java) -class, and press Ctrl + Shift + F10. This starts the REST
endpoint.

Alternative 2:
In your terminal/cmd, navigate to you project folder path and run "mvn clean install". This will install the project in
you local .m2 repository assuming you have Maven installed. From there, navigate to .\.m2\repository\henrik\hb\1.0.0\
and run the hb-1.0.0.jar -file.

# Application functionality

This application runs at base URL [localhost:8080](http://localhost:8080) and serves two endpoints.

## POST /account

This endpoint creates a new [Account](src/main/java/assignments/one/Account.java) for a
given [Person](src/main/java/assignments/four/Person.java). If the person does not already exist a new Person will be created.

Example request body:
`{
"accountId": "1",
"personId": "19990101XXXX"
}`

Example successful response body: `Account{id='1', owner=Person{id='19990101XXXX'}}`

If an account already exists 403 Forbidden will be returned with the following body: `Account with id {id} already exists.`

## GET /account

This endpoint fetches an [Account](src/main/java/assignments/one/Account.java) by its id.

Example request body:
`{
"id": "1"
}`

Example successful response body: `Account{id='1', owner=Person{id='19990101XXXX'}}`

If no accounts exits 500 Internal Server Error will be returned with the following body: `No account with id {id} exists.`

## GET /person

This endpoint fetches all [Accounts](src/main/java/assignments/one/Account.java) for a
given [Person](src/main/java/assignments/four/Person.java).

Example request body:
`{
"id": "19990101XXXX"
}`

If no person exits 500 Internal Server Error will be thrown with the following body: `No person with id {id} exists.`

Example successful response body:
`[Account{id='1', owner=Person{id='19990101XXXX'
}
}
]`
