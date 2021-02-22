# Welcome to Playground TMDB!

Project created to demonstrate some android apps development concepts. (personal)


# Project

This project make use of the API TMDB to help in some scenarios that simulate day to day of a developer.


## Layout

All layouts were inspired by [UpLabs](https://www.uplabs.com/posts/movies-e0f9c1ea-a644-4666-857b-10933c4089ca)
credits [Mickael Guillaume](https://www.uplabs.com/guillaumemick)

![](https://github.com/AleBarreto/playground-tmdb/blob/main/home_main.png?raw=true) ![](https://github.com/AleBarreto/playground-tmdb/blob/main/home_search.png?raw=true) 


## Github Actions

Check all the builds made in [Github Actions](https://github.com/AleBarreto/playground-tmdb/actions)

obs: an important step is the key decryption (TMDB API) in the [secrets.properties.gpg](https://github.com/AleBarreto/playground-tmdb/blob/main/secrets.properties.gpg)  file. File using gpg and the AES256 cipher algorithm.
The decrypted file is the same below:
```properties
API_KEY_TMDB=XXXYYYZZZ
```
More info about [**Encrypted secrets**](https://docs.github.com/en/actions/reference/encrypted-secrets)
