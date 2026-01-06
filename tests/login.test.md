# Login Service Tests (TDD)

## Test 1: Successful login

**Given**
- User exists in database
- Username: aleyna
- Password: correct_password

**When**
- Login service is called with valid credentials

**Then**
- Authentication should succeed
- User profile data should be returned


## Test 2: Login with wrong password

**Given**
- User exists in database
- Username: aleyna
- Password: wrong_password

**When**
- Login service is called

**Then**
- Authentication should fail
- Error message "Invalid credentials" should be returned


## Test 3: Login with non-existing user

**Given**
- User does not exist

**When**
- Login service is called

**Then**
- Authentication should fail
- Error message "User not found" should be returned
