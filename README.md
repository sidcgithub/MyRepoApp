# GitHub Repo Explorer App

## Project Overview
This project is an Android application built using Jetpack Compose that allows users to search for public profiles on GitHub, view their public repositories, and explore detailed information about each repository. The app emphasizes modern Android development practices, including modular architecture and best practices for scalability and maintainability.

## Table of Contents
- [Objective](#objective)
- [Motivation](#motivation)
- [Architecture](#architecture)
- [Setup and Installation](#setup-and-installation)
- [Testing](#testing)
- [Continuous Integration](#continuous-integration)

## Objective
The goal of this project is to build a GitHub repository app that allows users to:
- Search for public GitHub profiles.
- View a list of public repositories for a selected profile.
- Explore detailed information for each repository, including the total number of forks.
- Display a star badge if the total forks across all repos exceed 5000.

## Motivation
The project is designed to showcase:
- Development skills using **Jetpack Compose** for building modern, declarative UI.
- An understanding of scalable and maintainable architecture through MVVM and Clean Architecture principles, emphasizing separation of concerns and testability.
- Best practices in Android development, with a focus on code quality and effective project management.
- Knowledge of CI/CD pipelines to ensure consistent build and test processes.

## Architecture
The application is structured to follow modular design principles, divided into different layers and modules to ensure separation of concerns and ease of maintenance. The key layers and modules include:
- **App Module:** The main entry point of the application, responsible for bootstrapping the app.
- **Feature Modules:** Independent modules such as Search, RepoList, and RepoDetails, each handling specific functionalities.
- **Core Modules:** Shared components like networking, data models, and utilities that are reused across features.

Below is a visual representation of the architecture:

![Architecture Diagram](./RepoApp.png)

## Setup and Installation
To set up the project locally:
1. Clone the repository:
    ```bash
    git clone https://github.com/sidcgithub/MyRepoApp.git
    ```
2. Open the project in Android Studio.
3. Build the project and run it on an emulator or a physical device.

## Testing
Testing strategies and implementation details will be added as the project develops. The plan includes adding unit tests for core logic and UI tests for major user interactions.

## Continuous Integration
The project uses GitHub Actions to automate the build and test process with every commit. This helps in maintaining code quality and ensuring a stable build environment.
