#Codefellowship Application

Full stack application
## Tuesday work:
- An Application User has  username, password (will be hashed using BCrypt), firstName, lastName, dateOfBirth, bio
- The site allows users to create an ApplicationUser on the “sign up” page. /signup
- the Controller has an @Autowired private PasswordEncoder passwordEncoder; and use that to run passwordEncoder.encode(password) before saving the password into the new user.
- The site has a page which allows viewing the data about a single ApplicationUser, at a /profile

## Wednesday work:

- created post class
- User could put posts to the database
- posts are showing on profile page for logged in user
- User could access other user public profile by going to the route : /users/{id}
- users could see their profile info on the page /profile

## Thursday work:

- Created many to many relationship
- Created separate DB for relationship between following and influential users
- Implemented follow button on the pages
- All users that you follow will be output on the profile page


