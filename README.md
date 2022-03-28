# ProjectGladlaks

## GladlaksApp

Android app for the project Gladlaks written with Jetpack Compose (API-level 29)

<br>

## Setup

### Environment secrets

To run the app, you simply need to create a global gradle.properties file in your operating system. Write the following in the terminal:

```
cd ~/.gradle/ && echo "" > gradle.properties
```

```
nano gradle.properties
```

Copy the following into the terminal window _(remember to replace {your_client_string_here} and {your_secret_string_here} with the actual strings)_:

```
BARENTSWATCH_CLIENT="{your_client_string_here}"
BARENTSWATCH_SECRET="{your_secret_string_here}"
```

Press **Ctrl X**, then **Y** and then press **Enter**.

**You are now ready to build and run the app in Android Studio ❤️**

[Link to inspiration](https://richardroseblog.wordpress.com/2016/05/29/hiding-secret-api-keys-from-git/)
