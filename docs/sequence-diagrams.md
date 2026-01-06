## Sequence Diagram – User Login

User -> HTML Login Page: Enter number & password
HTML Login Page -> Backend API: POST /login
Backend API -> PostgreSQL DB: Validate credentials
PostgreSQL DB -> Backend API: User record
Backend API -> HTML Login Page: Login success
HTML Login Page -> User: Redirect to profile page


## Sequence Diagram – View Profile

User -> Profile Page: Request profile
Profile Page -> Backend API: GET /profile
Backend API -> PostgreSQL DB: Fetch user data
PostgreSQL DB -> Backend API: User profile info
Backend API -> Profile Page: Profile data
Profile Page -> User: Display profile
