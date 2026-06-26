# Telemedicine — Symptom Checker (Android, Java)

A basic Android application (prototype) for assessing COVID-19-related symptoms and providing contact information for the corresponding Health Insurance Provider (EPS). This version is intended for educational purposes only and **does not replace professional medical diagnosis or advice**.

## Features

- Symptom questionnaire including:
  - Fever
  - Cough
  - Shortness of breath
  - Loss of smell or taste
  - Sore throat
  - Fatigue
- Risk assessment (Low / Medium / High) based on a simple scoring system.
- EPS contact mapping (phone number and email) loaded from `assets/eps.json`.
- One-tap options to call or email the corresponding EPS using Android intents.

## Getting Started

1. Open the project in **Android Studio**.
2. Update `app/src/main/assets/eps.json` with the actual contact information for the corresponding EPS providers.
3. Build and run the application on a physical Android device or an emulator.

## Disclaimer

This project was developed for educational purposes only and is **not intended to provide medical diagnosis or treatment**. Always consult qualified healthcare professionals and follow the recommendations of local health authorities.
