### What the app does

- This a super-simple and bare-bones implementation of web-scraping using JSOUP, and UI rendering with Jetpack Compose.
- Jetpack Compose is in BETA stage, so this project will only run on Android Studio Arctic Fox.
- The UI contains a lazy column from Jetpack Compose, an equivalent of RecyclerView for rendering a large blob of list. 
- The code design is basically a presentation layer (view + viewModel), domain and a data layer.
- Using a sealed class called 'Outcome' to cleanly return a success or a failure response back to the view layer.
- Business logic has been separated to a dedicated class.
- The data will persist across screen orientation changes and will only request for an API call if the live data is empty or theres a request to force the network call.
- Please disconnect from any VPN while running this app. It may cause exception while scraping the URLs.
