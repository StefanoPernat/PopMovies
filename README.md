## Popular Movies Project

#### Description

This repository will be a copy of my previous [PopularMovies app](https://github.com/StefanoPernat/Popular-Movies-v2), I already submitted the project so I decided to create this repo for experimenting other solutions to common problems encountered developing the previous project

### The rubic

#### User Interface - Layout

- Movies are displayed in the main layout via a grid of their corresponding movie tumbnails. **[DONE]**
- UI contains an element (spinner or settings menu) to toggle the sort order of the movies by: **most popular**, **highest rated**. **[DONE]**
- UI contains a screen for displaying the details for a selected movie. **[DONE]**
- Movie details layout contains **title**, **release date**, **movie poster**, **vote average**, **plot synopsis**.

#### User Interface - Function

- When the user changes sort criteria, the main view gets updated correctly **[DONE]**
- When a movie poster tumbnail is selected the movie detail screen is launched. **[DONE]**

#### Network API Implementation

In the background tread, app queries the `/movie/popular` or `/movie/top_rated` API for the sort criteria, specified in the setting menu. **[DONE]**

### What I will use

- [x] EventBus
- [x] RecyclerView (and ViewHolder)

## Part II 

For **Part II** I decided to get rid of EventBus and use Loaders instead

### The rubic

#### User Interface - Layout

- [ ] Movie Details layout contains a section for displaying trailer videos and user reviews
 
#### User Interface - Function
 
- [ ] When a trailer is selected, app uses an intent to launch the trailer
- [ ] In the movie detail screen, a user can tap a button (like a star) to mark it as favorite

### Network API implementation

- [ ] App requests for related videos for a selected movie via the `/movie/{id}/videos` endpoint in a background thread and displays those details when the user selects a movie
- [ ] App requests the users review for a selected movie via the `/movie/{id}/reviews` endpoint in a background thread and displays those details when the user selects a movie

### Data Persistence

- [ ] The titles and ids of the user's favorite movies are stored in a Content Provider backed by a SQLite database, the Content Provider is updated whenever the user favorites and un-favorites a movie
- [ ] When the user select the favorites settings option the main view updates showing the favorite movies collection of the user based on the content provider

# What will I use

- [ ] Loader
- [ ] Content Provider