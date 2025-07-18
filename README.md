# 🔐 Secure File Transfer Application

A production-ready, AES-encrypted file upload/download web application built with **Spring Boot**, secured and deployed using **Docker**, **GitHub Actions**, and **AWS EC2**. Integrated with **OWASP ZAP** for automated vulnerability scanning.

---

## 🚀 Features

* 🔒 AES-based file encryption before storage
* ☁️ Uploads/downloads encrypted files by user
* 🐳 Dockerized for container-based deployment
* ⚙️ GitHub Actions CI/CD pipeline
* ☁️ Hosted on AWS EC2 (Ubuntu)
* 🛡️ Integrated ZAP scan for baseline security analysis
* 📄 Downloadable scan reports in HTML, Markdown, and JSON

---

## 🧠 Tech Stack

| Layer      | Tools/Tech                |
| ---------- | ------------------------- |
| Backend    | Spring Boot (Java 21)     |
| Build Tool | Maven                     |
| Security   | AES Encryption, OWASP ZAP |
| DevOps     | Docker, GitHub Actions    |
| Hosting    | AWS EC2                   |

---

## 📦 Endpoints

| Method | Endpoint                        | Description               |
| ------ | ------------------------------- | ------------------------- |
| POST   | `/files/upload`                 | Upload and encrypt file   |
| GET    | `/files/download/{user}/{file}` | Download and decrypt file |

---

## 🔧 Setup & Run Locally

### Prerequisites

* Java 21
* Maven
* Docker (for container run)

### Clone the Repository

```bash
git clone https://github.com/Kiranvalalambe/secure-file-transfer.git
cd secure-file-transfer
```

### Run with Maven (Dev)

```bash
mvn spring-boot:run
```

### Run with Docker (Prod)

```bash
docker build -t secure-file-app .
docker run -p 8080:8080 secure-file-app
```

---

## 🚀 CI/CD Pipeline (GitHub Actions)

This project uses a custom GitHub Actions pipeline that:

1. Builds the JAR file using Maven
2. Builds and pushes Docker image to Docker Hub
3. Deploys container on AWS EC2 via SSH
4. Runs automated OWASP ZAP security scan
5. Uploads scan reports to GitHub Artifacts

✅ Pipeline Status: **Active & Green**

---

## 🛡️ Security Report

After each deployment, OWASP ZAP scans the live EC2 instance and generates a full security report:

📌 Download: Go to [Actions tab](https://github.com/Kiranvalalambe/secure-file-transfer/actions) → Select latest run → Download `zap-reports` artifact.

---

## 📷 Screenshots
<img width="1919" height="1016" alt="image" src="https://github.com/user-attachments/assets/e87cf0d3-eba5-4e85-aa77-2f6fcd5f01e9" />
<img width="951" height="519" alt="image" src="https://github.com/user-attachments/assets/9c08c909-9327-4df5-83cd-ab52f5201331" />


---

## 🙌 Acknowledgements

* [Spring Boot](https://spring.io/projects/spring-boot)
* [Docker](https://www.docker.com/)
* [GitHub Actions](https://github.com/features/actions)
* [OWASP ZAP](https://owasp.org/www-project-zap/)
* [SonarCloud](https://sonarcloud.io/) *(coming soon)*

---

## 🧑‍💻 Author

**Kiran Valalambe**
[LinkedIn](https://linkedin.com/in/kiran-v-15131323b/) • [GitHub](https://github.com/Kiranvalalambe)

---

## 📃 License

This project is licensed under the MIT License.
