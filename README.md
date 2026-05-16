# Virasat-Namma Guide - Tourism App

A heritage tourism app for exploring Karnataka's hidden gems!

## Features Implemented

- Site Discovery with simulated heritage site data
- Multi-language support (English & Kannada)
- QR Code Scanner using Google ML Kit
- Digital Travel Passport with Room DB persistence
- Audio Guide (MediaPlayer integration)
- Hidden Facts unlocked on check-in
- Temple Architecture inspired theme

## How to Add Real Audio Files

1. Create audio files for each heritage site and place them in `app/src/main/res/raw/` directory:
   - `audio_hampi1.mp3` - Hampi audio guide
   - `audio_mysore1.mp3` - Mysore Palace audio guide
   - `audio_belur1.mp3` - Belur audio guide
   - `audio_halebidu1.mp3` - Halebidu audio guide
   - `audio_pattadakal1.mp3` - Pattadakal audio guide

2. Update `AppDatabase.kt` to reference these audio files in the `populateDatabase()` function

3. Update `SiteDetailActivity.kt`'s `playAudio()` function to load the actual audio resource

## How to Run

1. Open in Android Studio
2. Sync Gradle
3. Connect device/emulator
4. Run the app!

## Technologies Used

- Kotlin
- Room DB
- Google ML Kit (Barcode Scanning)
- CameraX
- MediaPlayer
- Material Design Components
