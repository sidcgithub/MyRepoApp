# Convention Plugin Setup

## Benefits

Creating a convention plugin has several advantages:

1. **Consistency**: Convention plugins enforce a uniform setup across your project, reducing debugging time and enhancing code readability.
2. **Efficiency**: Convention plugins abstract common configurations, simplifying your build scripts.
3. **Reusability**: Convention plugins can be reused across multiple projects, reducing code duplication.

## Setup Steps

To set up a new convention plugin, follow these steps:

1. **Create a new Kotlin file**: This will be your convention plugin class. Create it in the `src/main/kotlin` directory of your `convention` module. For instance, you might create a `MyNewConventionPlugin.kt` file.

2. **Define your plugin class**: In your new Kotlin file, define a class that extends `Plugin<Project>`. This class will contain the logic for your convention plugin.

    ```kotlin
    class MyNewConventionPlugin : Plugin<Project> {
        override fun apply(target: Project) {
            // Your plugin logic goes here
        }
    }
    ```

3. **Register your plugin**: In your `build.gradle.kts` file, register your new convention plugin. This makes it available for use in your project.

    ```kotlin
    gradlePlugin {
        plugins {
            register("myNewConvention") {
                id = "myrepoapp.myNewConvention"
                implementationClass = "MyNewConventionPlugin"
            }
        }
    }
    ```

4. **Apply your plugin**: In your project's `build.gradle.kts` files, you can now apply your new convention plugin.

    ```kotlin
    plugins {
        alias(libs.plugins.myrepoapp.myNewConvention)
    }
    ```

## When to Create a Convention Plugin

A convention plugin should be created when you find yourself duplicating the same configurations across multiple modules. This not only saves time but also makes your build scripts cleaner and easier to maintain.