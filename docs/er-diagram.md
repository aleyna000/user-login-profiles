## ER Diagram – User Login Profiles

### Entity: User

| Field Name  | Type        | Description                  |
|------------|------------|------------------------------|
| id         | UUID       | Primary key                  |
| username   | VARCHAR    | Unique username              |
| password   | VARCHAR    | Hashed password              |
| email      | VARCHAR    | User email                   |
| created_at| TIMESTAMP  | Account creation date        |

### Relationships

- A User has exactly one Profile
