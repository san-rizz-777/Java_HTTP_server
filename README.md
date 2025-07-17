# ☕  Java HTTP Server

A minimal yet modular HTTP server in Java built from scratch — supports:

- 🧵 Thread pool based on available CPU cores
- 📁 Directory listing
- 🛑 Graceful shutdown
- 📄 Static file serving (HTML, CSS, JS, images)
- 🧱 Modular structure (Server, Handler, Utilities)

---

## 🚀 How to Run

### Prerequisites

- Java 8 or later
- IDE like Eclipse, IntelliJ, or CLI (javac + java)

### Steps (Eclipse)

1. Clone this repo or download the source.
2. Open Eclipse → Create **New Java Project**
3. Inside `src/`, create a package called `server`
4. Copy the `.java` files into `src/server/`
5. Create a folder `public/` (outside `src/`) and place an `index.html`
6. Run `SimpleHTTPServer.java`
7. Open [http://localhost:8080](http://localhost:8080)

---

## 📁 Directory Layout
SimpleJavaHTTPServer/
├── public/ # Static files to serve
│ └── index.html
├── src/
│ └── server/
│ ├── SimpleHTTPServer.java
│ ├── ClientHandler.java
│ └── HTTPUtils.java
├── README.md


---

## 🔧 Features

| Feature           | Description                                                                 |
|-------------------|-----------------------------------------------------------------------------|
| 🔄 Thread Pool     | Uses `Runtime.getRuntime().availableProcessors()` for scalable performance |
| 📁 Directory List  | Lists folder contents via dynamic HTML                                      |
| 🧱 Modular Code     | Separated into server, handler, utility classes                            |
| 🛑 Graceful Stop   | Shutdown hook handles `Ctrl+C` or Eclipse stop button cleanly              |
| 📄 Static Serving  | Serves HTML, CSS, JS, images from `public/` folder                         |

---


## 🔮 Future Ideas & Improvements

- 🔄 Support for **POST** requests and form submissions  
- 🔐 Add **basic authentication** or IP filtering  
- 📝 Implement **logging** (to console or file)  
- ⚙️ Add **config file** for port, base directory, max threads  
- 🧪 Add **unit tests** for handlers and utility methods  
- 📦 Support **MIME upload/file download** features  
- 🌐 Enable **CORS** headers for cross-origin support
---

 📬 Feedback & Contact

Have suggestions, feedback, or want to collaborate?

- 📧 Email: [sanskargunde@gmail.com](mailto:sanskargunde@gmail.com)  
- 🔗 LinkedIn: [www.linkedin.com/in/sanskar-gunde-7b9a0b33a](www.linkedin.com/in/sanskar-gunde-7b9a0b33a)  
- 📱 Mobile: +91 8857035293

Feel free to reach out for Java backend projects, internships, open-source collaboration, or tech chats!
