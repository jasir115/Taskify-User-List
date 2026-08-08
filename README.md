# Android UserList MVVM

A modern Android application built with Kotlin that demonstrates clean architecture, networking with Retrofit, and efficient UI handling with ListAdapter and Pagination.

## 🚀 Overview

This project displays a list of users fetched from a REST API. It follows the **MVVM (Model-View-ViewModel)** architectural pattern and implements best practices for Android development, including reactive UI updates and infinite scrolling.

## ✨ Features

- **MVVM Architecture**: Clean separation of concerns between UI, Business Logic, and Data.
- **Reactive UI**: Uses `LiveData` and `ViewModel` to handle configuration changes and state management.
- **Networking**: Efficient API calls using **Retrofit 2** and **OkHttp**.
- **Infinite Scrolling (Pagination)**: Automatically loads the next page of users as the user scrolls down.
- **Pull-to-Refresh**: Integrated `SwipeRefreshLayout` to manually refresh the user list.
- **Efficient UI Updates**: Uses `ListAdapter` with `DiffUtil` to update only changed items in the RecyclerView, ensuring smooth performance.
- **View Binding**: Type-safe and null-safe access to layout components.

## 🏗️ Architecture & Design

The app is structured into the following layers:

### 1. View Layer (`MainActivity`)
- Responsible for observing `LiveData` from the ViewModel and updating the UI.
- Handles user interactions like scrolling and refreshing.
- Uses **View Binding** for efficient layout access.

### 2. ViewModel Layer (`UserViewModel`)
- Acts as a bridge between the View and the Data source.
- Manages the UI state (loading, error, data).
- Contains the logic for pagination and data fetching using `viewModelScope`.

### 3. Data Layer
- **Model**: Kotlin Data Classes (`User`) representing the JSON response.
- **Remote Data Source**: `ApiService` interface defining the Retrofit endpoints.

## 🛠️ Tech Stack

- **Language**: [Kotlin](https://kotlinlang.org/)
- **UI Framework**: [Material Components for Android](https://material.io/develop/android)
- **Lifecycle Management**: [ViewModel](https://developer.android.com/topic/libraries/architecture/viewmodel), [LiveData](https://developer.android.com/topic/libraries/architecture/livedata)
- **Networking**: [Retrofit](https://square.github.io/retrofit/), [OkHttp](https://square.github.io/okhttp/)
- **JSON Parsing**: [GSON](https://github.com/google/gson)
- **Image/List Loading**: [RecyclerView](https://developer.android.com/guide/topics/ui/layout/recyclerview), [ListAdapter](https://developer.android.com/reference/androidx/recyclerview/widget/ListAdapter)

## 🚦 Getting Started

### Prerequisites
- Android Studio Ladybug (or newer)
- JDK 11+
- Android SDK 26+

### Installation
1. Clone the repository:
   ```bash
   git clone https://github.com/yourusername/Android-UserList-MVVM.git
   ```
2. Open the project in **Android Studio**.
3. Sync the project with Gradle files.
4. Run the app on an emulator or a physical device (API 26+).


