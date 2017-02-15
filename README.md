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

- EventBus
- RecyclerView (and ViewHolder)
- ButterKnife [not sure] 
