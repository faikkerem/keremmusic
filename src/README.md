# KeremMusic Projesi

Bu proje, Spring Boot ve H2 veritabanı kullanılarak geliştirilmiş bir kullanıcı yönetim sistemidir. Kullanıcıların kayıt olabildiği, giriş yapabildiği ve admin panelinden kullanıcı listesini yönetebildiği Full-Stack bir web uygulamasıdır.

## 🚀 Teknolojiler
* **Backend:** Java, Spring Boot, Spring Data JPA
* **Frontend:** HTML, Bootstrap, JavaScript (Fetch API)
* **Database:** H2 Database (In-Memory)

## 🛠 Özellikler
* **Kullanıcı Kayıt:** Yeni kullanıcı oluşturma (POST /api/users/register)
* **Kullanıcı Giriş:** Sisteme giriş yapma (POST /api/users/login)
* **Listeleme:** Kayıtlı kullanıcıları dashboard üzerinde görüntüleme (GET /api/users)
* **Silme:** Admin panelinden kullanıcı silme (DELETE /api/users/{id})

## 💻 Kurulum ve Çalıştırma
1. Projeyi klonlayın veya indirin.
2. IntelliJ IDEA üzerinde projeyi açın.
3. `KeremMusicApplication.java` dosyasını çalıştırın.
4. Tarayıcınızdan `http://127.0.0.1:9092/tables.html` adresine gidin.
5. Tarayıcınızdan http://127.0.0.1:9092/h2-console/l  adresine gidin.
6. INSERT INTO USERS (username, email, role, password) VALUES ('kerem', 'kerem@email.com', 'USER', '123456');git add .

## 📋 Proje Yapısı
- `src/main/java/com/keremmusic/keremmusic/controller`: API uç noktaları
- `src/main/java/com/keremmusic/keremmusic/service`: İş mantığı
- `src/main/java/com/keremmusic/keremmusic/repository`: Veritabanı işlemleri
- `srgit --versionc/main/resources/static`: HTML ve JS dosyaları