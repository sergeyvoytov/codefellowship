#Codefellowship Application

Full stack application

- An ApplicationUser has  username, password (will be hashed using BCrypt), firstName, lastName, dateOfBirth, bio
- The site allows users to create an ApplicationUser on the “sign up” page. /signup
- the Controller has an @Autowired private PasswordEncoder passwordEncoder; and use that to run passwordEncoder.encode(password) before saving the password into the new user.
- The site has a page which allows viewing the data about a single ApplicationUser, at a /profile
