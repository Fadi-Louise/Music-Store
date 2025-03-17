# Digital Music Store JavaFX Application 

## Project Overview
The Digital Music Store is a comprehensive digital music distribution platform developed using JavaFX for the frontend and MySQL for the backend. The application enables music discovery, purchasing, and artist content management through a robust client-server architecture.

## Key Features Implemented
- **User Authentication System**: Secure registration and login functionality with encrypted password storage.
- **Music Catalog Management**: Browsing and search capabilities filtered by genre, artist, and popularity.
- **E-commerce Functionality**: Shopping cart and checkout system for digital music purchases.
- **Artist Dashboard**: Content management interface for artists to upload, track, and manage their music.
- **Download Management**: Secure system for users to access and download purchased content.

## Technical Implementation
- **Client-Server Architecture**: Designed with JavaFX frontend communicating with MySQL database backend.
- **Database Design**: Normalized MySQL schema with six tables managing users, artists, songs, purchases, and downloads.
- **CRUD Services**: Comprehensive data interaction services for database operations.
- **UI Design**: Intuitive user interfaces with JavaFX including login, browsing, purchasing, and artist management views.
- **Security Implementation**: Encryption for sensitive data and secure authentication processes.

## Skills Demonstrated
- JavaFX application development
- Database design and MySQL implementation
- Client-server architecture
- UI/UX design for desktop applications
- Digital rights management for content distribution

## How to Run the Application
1. **Clone the Repository**: 
   ```bash
   git clone <repository-url>
   ```
2. **Set Up the Database**:
   - Import the provided SQL script to create the database schema.
   - Update the database credentials in the application configuration.

3. **Build the Application**:
   - Use an IDE like IntelliJ IDEA or Eclipse to open the project.
   - Build the project to resolve dependencies and compile the code.

4. **Run the Application**:
   - Execute the main class to start the JavaFX application.
   - Ensure the MySQL server is running and accessible.

## Screenshots
![Login Screen](screenshots/login.png)
*Login Screen*

![Music Catalog](screenshots/music_catalog.png)
*Music Catalog*

![Artist Dashboard](screenshots/artist_dashboard.png)
*Artist Dashboard*

## Future Enhancements
- **Streaming Service Integration**: Add functionality to stream music directly from the platform.
- **Mobile Application**: Develop a mobile version of the application using frameworks like Flutter or React Native.
- **Advanced Analytics**: Implement analytics for artists to gain insights into their music performance.



## License
This project is licensed under the MIT License - see the [LICENSE.md](LICENSE.md) file for details.

