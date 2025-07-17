
# 🛡️ Secure File Transfer System

A secure and lightweight file upload/download system built using **Spring Boot**, featuring **AES encryption**, **file validation**, and structured for **Dockerization**, **CI/CD integration**, and **AWS deployment**.

---

## 🔧 Tech Stack
- Java 21  
- Spring Boot  
- REST API  
- AES Encryption  
- Postman (API Testing)  
- Maven

---

## 🚀 Features
- 🔐 **AES-Encrypted File Uploads**: Files are stored securely with strong symmetric encryption.
- 📥 **Decrypted File Downloads**: Users can download files securely and decrypt on the fly.
- 📁 **User-Based Storage**: Files are stored in `uploads/{username}` directories.
- ✅ **File Type Validation**: Only PDF, PNG, JPEG, and TXT files allowed.
- 🧱 **Modular Architecture**: Clean separation of controller and service layers.

---

## 📸 API Endpoints

### 📤 Upload a File
```
POST /files/upload
```
**Form-data Params:**
- `file`: the file to upload  
- `username`: your username

### 📥 Download a File
```
GET /files/download/{username}/{filename}
```
Returns the decrypted file with `Content-Disposition: attachment`.

---

## 🛠️ Upcoming (Planned):
- 🐳 Docker containerization
- 🔄 CI/CD with GitHub Actions
- 🛡️ OWASP ZAP security testing
- ☁️ AWS EC2 deployment
- 🧾 File logging + metadata API

---

## ✅ How to Run Locally

1. Clone this repo  
   ```bash
   git clone https://github.com/yourusername/secure-file-transfer
   cd secure-file-transfer
   ```

2. Build the project  
   ```bash
   mvn clean install
   ```

3. Run the app  
   ```bash
   mvn spring-boot:run
   ```

4. Test via Postman  
   - POST: `http://localhost:8080/files/upload`
   - GET:  `http://localhost:8080/files/download/{username}/{filename}`

---

## 📂 Folder Structure

```
src
├── main
│   ├── java
│   │   └── com.example.File
│   │       ├── controller
│   │       └── service
│   └── resources
│       └── application.yml
uploads/
downloads/
```

---

## 📄 License
This project is for educational and demonstration purposes.
