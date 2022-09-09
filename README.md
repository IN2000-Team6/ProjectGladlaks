# NOTE!

This is *not* the original repo for this project! Users outside the University of Oslo Enterprise was not able to view the original. This repo was created to act as a showcase for the project and *does not* reflect the workflow of the original project development!

# ProjectGladlaks

An Android app for monitoring water and disease conditions in norwegian seabased fish farms. The app was planned, designed and created fully from scratch by students as course work for the course [IN2000 - Software Engineering with Project Work ](https://www.uio.no/studier/emner/matnat/ifi/IN2000/index-eng.html) at The University of Oslo.

🌟 **The project was awarded "Best App 2022" by the lecturers and TA's in the course** 🌟

*Data from open access API's from MET and Barentswatch*

The project was created by

- Martin Allan Myrvold Jensen (mamjense@ifi.uio.no)
- Ask Palmstrøm Fenn (askpf@ifi.uio.no)
- Jesper Dahl Norgård (jesperdn@ifi.uio.no)
- Erik Ørnsrud Alstad (erikoa@ifi.uio.no)
- Ina Bergli Henriksen (inabh@ifi.uio.no)
- Tone Eide Hilmen (tonehi@ifi.uio.no)

---

<img align="center" src="https://media.github.uio.no/user/6920/files/dd71c30c-508e-455f-9c75-c7f6020e67f0" alt="Screenshot">

![image](https://media.github.uio.no/user/6920/files/8709fe6e-fe7a-4722-82c1-c77baef72740)

Screenshots from the app running on an Android emulator.

## GladlaksApp

Android app for the project Gladlaks written with Jetpack Compose (API-level 29)

Written in Kotlin with the use of Jetpack Compose and modern Android development tools and libraries, like Hilt, Room, Ktor and JUnit.

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

Also add the following to your local.properties file:

MAPS_API_KEY="{your_maps_api_key_here}"

**You are now ready to build and run the app in Android Studio ❤️**

[Link to inspiration](https://richardroseblog.wordpress.com/2016/05/29/hiding-secret-api-keys-from-git/)
