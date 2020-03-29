### Approach

- Supported devices - API 21 (Android 5.0, Lollipop) and above.
- The URLs have been scraped using JSOUP. It's a simple library that does that job.
- The UI contains two textViews for single results each, and one recyclerview for a large blob of list. The recyclerview has been chosen to avoid blocking the UI and only render data according to the current scroll position.
- The project uses fast-list to quickly inflate a functional recyclerview.
- The code design is powered by a view layer, interactor layer (viewModel) and a data layer.
- Using a sealed class called outcome to cleanly get a success or a failure response in the view layer.
- The business logic has been separated to a dedicated class
- For finding words separated by any whitespace with the number of repetitions, i've used kotlin's built-in functions to filter out data. 
- Please disconnect from any VPN while running this app. It may cause exception while scraping the URLs.
