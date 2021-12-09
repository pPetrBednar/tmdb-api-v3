# Simple TMDB API wrapper

## Supported functions
* Search for movie (title)
* Search for series (title)
* Obtain metadata for movie (movie id)
* Obtain metadata for series (series id)
* Obtain metadata for series season (series id, season number)
* Obtain metadata for series episode (series id, season number episode number)
* Obtain cover image from TMDB (image location from metadata)

## Full metadata wrappers
* Movie results
* Movie result
* Movie metadata
* Series results
* Series result
* Series metadata
* Series season metadata
* Series episode metadata

## Usage
```
    // Initialize TMDB API controller
    Tmdb tmdb = new Tmdb("api_key", Localization.EN_US);
    
    // Obtain search results for movies
    MovieResults results = tmdb.searchForMovie("avengers");
    
    // Obtain wanted result
    MovieResult result = results.getResults().getFirst();
    
    // Obtain metadata for movie under obtained id
    MovieMeta movie = tmdb.getMovieMeta((int) result.getId());

   // Print movie information
    System.out.println(movie.getOriginalTitle());
```

```
    // Initialize TMDB API controller
    Tmdb tmdb = new Tmdb("api_key", Localization.EN_US);
    
    // Obtain search results for series
    SeriesResults results = tmdb.searchForSeries("mentalist");
    
    // Obtain wanted result
    SeriesResult result = results.getResults().getFirst();
    
    // Obtain metadata for series under obtained id
    SeriesMeta series = tmdb.getSeriesMeta((int) result.getId());

   // Print series information
    System.out.println(series.getName());
```
