# Complaint Registration App

A robust, modern, and high-performance Android application designed for streamlined complaint management. Built with Kotlin and powered by Firebase Firestore, this application offers a seamless user experience for registering, tracking, and viewing complaints with real-time data persistence and optimized local caching.

## 📱 Features

- **Real-time Persistence:** Powered by Firebase Cloud Firestore for instant data synchronization across devices.
- **Intelligent Caching:** Implements a sophisticated local caching strategy to minimize network calls and ensure a responsive UI.
- **Modern UI/UX:** A clean, professional redesign featuring a "Deep Navy & Vivid Red-Pink" theme with polished Material Design 3 components.
- **Comprehensive Workflow:**
  - **Dynamic Splash Screen:** Brand-focused entry point.
  - **Intuitive Registration:** Simplified form with validation and real-time progress feedback.
  - **Interactive List:** Categorized view of complaints with color-coded priority indicators (Low, Medium, High, Urgent).
  - **Detailed Insights:** Deep-dive view of individual complaints including status and submission timestamps.
- **Result-Oriented Navigation:** Optimized activity lifecycle management using `startActivityForResult` to ensure data freshness without redundant reloads.

## Screenshots

<img src="/assets/1.jpg" alt="Dashboard Overview" width="75%">
<img src="/assets/2.jpg" alt="Message Analysis" width="75%">
<img src="/assets/3.jpg" alt="Budget Planning" width="75%">
<img src="/assets/4.jpg" alt="Allocation Tracking" width="75%">

## 🛠 Tech Stack

- **Language:** Kotlin
- **Architecture:** Standard Android Component Architecture with View Binding.
- **Database:** Firebase Cloud Firestore (NoSQL).
- **UI Framework:** Material Design 3 (Material Components).
- **Dependency Management:** Gradle Version Catalog (`libs.versions.toml`).
- **Asynchronous Operations:** Firebase SDK Tasks & Handlers.

## 🎨 Design Philosophy

The application adheres to modern design principles, focusing on clarity, accessibility, and professional aesthetics:

- **Primary Palette:** Deep Navy (`#1A1A2E`) for authority and stability.
- **Accent Palette:** Vivid Red-Pink (`#E94560`) for interactive elements and highlights.
- **Components:** Uses Material Cards, Outlined Text Fields, and custom Pill-shaped badges for a layered, contemporary feel.
- **User Feedback:** Integrated `ProgressDialog` and `Toast` notifications for every state change.

## 🚀 Getting Started

### Prerequisites
- Android Studio Ladybug or later.
- A Firebase project with Firestore enabled in Test Mode (or with appropriate security rules).
- `google-services.json` file from your Firebase console.

### Installation
1. Clone the repository.
2. Place your `google-services.json` in the `app/` directory.
3. Sync the project with Gradle files.
4. Run the app on an emulator or physical device (Min SDK 24).

## 📁 Project Structure

```text
app/src/main/java/com/example/quiz2/
├── adapter/
│   └── ComplaintAdapter.kt      # RecyclerView adapter with dynamic styling
├── model/
│   └── Complaint.kt             # Firebase-compatible data model
├── MainActivity.kt              # Complaint registration logic
├── ComplaintListActivity.kt     # List management with local caching
├── ComplaintDetailActivity.kt   # Detailed complaint viewer
└── SplashActivity.kt            # Application entry point
```

## 📜 License
Mian Saad Tahir
2023-CS-62_B
Quiz 2 Mobile Application Development.
