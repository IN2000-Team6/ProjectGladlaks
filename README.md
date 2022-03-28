# ProjectGladlaks

## GladlaksApp

Android app for the project Gladlaks written with Jetpack Compose (API-level 29)

<br>

## Setup

### Environment secrets

To run the app, add a new file called **gradle.properties** to the folder /GladlaksApp. 
After that copy the contents from /GladlaksApp/sample.gradle.properties into your new file, and add your secret properties
to the bottom of the file:

```
BARENTSWATCH_CLIENT="{your_client_string_here}"
BARENTSWATCH_SECRET="{your_secret_string_here}"
```

_Replace {your_client_string_here} with the actual string_

[Link to inspiration](https://richardroseblog.wordpress.com/2016/05/29/hiding-secret-api-keys-from-git/)
