USER

/user/{id}
    @getMapping return user from id
/user
    @postMapping create user (request body has data {name, email, password, phone, type })

/user/{id}
    @postMapping update user at id (request body has data {name, email, password, phone })
/user/{id}
    @deleteMapping delete user with id
/user/login
    @getMapping return id of user if email and password match in database (request body has data {email, password })

/user/pets/{id}
    @getMapping return the list of pets where user id = id

/checkInbox/{userID}
    @getMapping retrun array of messages from database where toID = userID;



GROOMINGAPTS

/

PETS

/pet/{id}
    @getMapping fetch pet with id = id

/pet
    @postMapping create new pet (request body has data {ownerId, name, breed, notes, image })

/pet/{id}
    @postMapping update pet at id (request body has data {ownerId, name, breed, notes, image })

/pet/docs/{id}
    @getMApping fetch documents from pet with id

/pet/docs/{id}
    @postMapping post document for pet with id (request body has data {images: [] photos})

