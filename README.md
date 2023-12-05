
# MatchMate application

In this application will display the list of the profile to the user. The user can either accept or decline the profile. The profile data is fetched from the server and the is stored locally in Room database.

## Tech Stack

- Kotlin
- Kotlin coroutines
- Hilt
- ViewModel
- ViewModel Factory
- LiveData
- Room Database
- Retrofit
- Glide
- ViewBinding
- Data class
- Sealed class
- Lazy initialization
- Kotlin high order function
- Kotlin scope function.

## WorkFlow

- For the initial launch the Data will be fetched from the server and added to the Room Database.
- As fetching the data from the network may vary depending upon the time. On the next launch, firstly the data from the database will be displayed and then the data from the network server will be fetched.
- This fetched data will be displayed to the list as well as stored in the database.
- The user can accept and declined the profile using the button displayed on the profile card.
- This response will persist using Room database.
- The loading spinner will be shown when the data fetching is in progress.
- The error screen will be displayed for the error.

## Screenshots

### 1) Profile Screen
![Profile Screen](https://github.com/VishalChavan2011/matchmate_application/blob/master/Screenshot/Profile_Screen.jpg?raw=true)

### 2) Error Screen
![Error Screen](https://github.com/VishalChavan2011/matchmate_application/blob/master/Screenshot/Error_Screen.jpg?raw=true)

### 3) Loading Screen
![Loading Screen](https://github.com/VishalChavan2011/matchmate_application/blob/master/Screenshot/Loading_Screen.jpg?raw=true)

### 4) Accepted
![Accepted](https://github.com/VishalChavan2011/matchmate_application/blob/master/Screenshot/Accepted.jpg?raw=true)

### 5) Declined
![Declined](https://github.com/VishalChavan2011/matchmate_application/blob/master/Screenshot/Declined.jpg?raw=true)

