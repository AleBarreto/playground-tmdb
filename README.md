# Welcome to Playground TMDB!

Project created to demonstrate some android apps development concepts. (personal)


# Project

This project make use of the API TMDB to help in some scenarios that simulate day to day of a developer.


## Layout

All layouts were inspired by [UpLabs](https://www.uplabs.com/posts/movies-e0f9c1ea-a644-4666-857b-10933c4089ca)
credits [Mickael Guillaume](https://www.uplabs.com/guillaumemick)

![](https://raw.githubusercontent.com/AleBarreto/playground-tmdb/main/prints/home_main.png) ![](https://raw.githubusercontent.com/AleBarreto/playground-tmdb/main/prints/home_search.png) ![](https://raw.githubusercontent.com/AleBarreto/playground-tmdb/main/prints/detail.png)

## How to build the project

Create a file called "secrets.properties" at the project root and add your api key from TMDB.
```properties
API_KEY_TMDB=XXXYYYZZZ
```
in build.gradle (app level)
```properties
static def getApiKey() {
    Properties props = new Properties()
    props.load(new FileInputStream(new File('secrets.properties')))
    return props['API_KEY_TMDB']
}

...
buildConfigField "String", "API_KEY_TMDB", "\"" + getApiKey() + "\""
....

```

## Github Actions

Check all the builds made in [Github Actions](https://github.com/AleBarreto/playground-tmdb/actions)

obs: an important step is the key decryption (TMDB API) in the [secrets.properties.gpg](https://github.com/AleBarreto/playground-tmdb/blob/main/secrets.properties.gpg)  file. File using gpg and the AES256 cipher algorithm.
More info about [**Encrypted secrets**](https://docs.github.com/en/actions/reference/encrypted-secrets)

## TODO 
* Add DataBinding
* Add mmore tests
* Add any(Hilt or Koin) library from Dependency Injection
* Feature search movie
* Add paging library
* Modify CI(GithubActions) to run unit tests


 
