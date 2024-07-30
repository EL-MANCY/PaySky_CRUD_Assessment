# PaySky CRUD Assessment

PaySky CRUD Assessment is an Android application built to demonstrate CRUD (Create, Read, Update, Delete) operations. The app is developed using Kotlin and follows the MVVM (Model-View-ViewModel) architecture pattern. It uses Room for database management, LiveData for observable data, and ViewModel for managing UI-related data.

## Table of Contents

- [Features](#features)
- [Technologies Used](#technologies-used)
- [Architecture](#architecture)
- [Setup and Installation](#setup-and-installation)
- [Usage](#usage)
- [Contributing](#contributing)
- [License](#license)

## Features

- **Create**: Allows users to add new entries to the database.
- **Read**: Displays a list of items retrieved from the database.
- **Update**: Enables editing of existing entries.
- **Delete**: Supports removal of items from the database.

## Technologies Used

- **Kotlin**: Programming language for Android development.
- **Room**: An abstraction layer over SQLite to provide a robust database solution.
- **LiveData**: A lifecycle-aware observable data holder.
- **ViewModel**: Provides data to the UI and survives configuration changes.
- **RecyclerView**: For displaying list data in a flexible and efficient way.

## Architecture

The application follows the MVVM architecture pattern:

- **Model**: Handles the data layer, using Room for local data storage.
- **View**: The UI layer, which includes Activities and Fragments.
- **ViewModel**: Manages UI-related data in a lifecycle-aware manner, allowing the data to survive configuration changes like screen rotations.

## Setup and Installation

 **Clone the repository:**
   ```bash
   git clone https://github.com/EL-MANCY/PaySky_CRUD_Assessment.git
