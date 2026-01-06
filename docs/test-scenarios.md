## Test Scenarios – User Login Profiles (TDD)

### Feature: User Login

#### Scenario 1: Successful login
- Given user exists in database
- And username and password are correct
- When user submits login form
- Then user should be redirected to profile page

#### Scenario 2: Login with wrong password
- Given user exists in database
- And password is incorrect
- When user submits login form
- Then system should display error message

#### Scenario 3: Login with non-existing user
- Given user does not exist
- When login form is submitted
- Then system should display user not found error

---

### Feature: User Profile

#### Scenario 4: View user profile
- Given user is authenticated
- When profile page is opened
- Then user details (name, surname, number, photo) should be displayed

#### Scenario 5: Unauthorized profile access
- Given user is not authenticated
- When profile page is accessed
- Then system should redirect to login page
