# Use Cases & Functional Requirements - User Login & Profile Management

Bu doküman, Java uygulamamızdaki **kullanıcı giriş ve profil yönetimi süreçlerini** detaylı olarak açıklayan **use case’leri** ve bu süreçler için belirlenen **fonksiyonel gereksinimleri** içerir. Her use case, aktörleri, ön koşulları, tetikleyicileri, temel ve alternatif akışları kapsamaktadır ve FR-01’den başlayarak numaralandırılmıştır.  

---

## UC-1: User Login

**Actor:** User  
**Preconditions:** Kullanıcı sisteme daha önce kayıtlı olmalıdır.  
**Trigger:** Kullanıcı login sayfasını açar ve TC ile şifresini girer.  

### Basic Flow (Successful Login)
1. Kullanıcı login sayfasını açar.  
2. Kullanıcı TC numarasını ve şifresini girer.  
3. Sistem TC ve şifre formatını doğrular:
   - TC: 11 haneli, sadece rakam  
   - Şifre: en az 8 karakter, hem harf hem sayı  
4. Sistem veritabanında kullanıcıyı arar.  
5. TC mevcutsa, şifre doğrulanır.  
6. Giriş başarılıysa:
   - Sistem giriş kaydını (LoginAttempt) kaydeder.  
   - Kullanıcı profil sayfasına yönlendirilir.  
   - Mesaj: `"Giriş başarılı, profil sayfasına yönlendiriliyorsunuz."`  

### Alternative Flows (Hatalı Girişler)
- **Invalid TC:** Kullanıcı sistemde kayıtlı değilse, sistem mesaj verir: `"Kullanıcı mevcut değil"`.  
- **Invalid Password:** Şifre hatalıysa, sistem mesaj verir: `"Kullanıcı adı veya şifre hatalı"`.  
- **Failed Attempts Limit:** Maksimum 10 hatalı giriş sonrası 11. denemede:
  - Sistem SMS ve e-posta ile kullanıcıyı bilgilendirir.  
  - Şifre sıfırlama linki gönderilir.  

---

## UC-2: View & Edit Profile

**Actor:** User  
**Preconditions:** Kullanıcı başarılı şekilde giriş yapmış olmalıdır.  
**Trigger:** Kullanıcı profil sayfasını açar.  

### Basic Flow (View Profile)
1. Kullanıcı profil sayfasını açar.  
2. Sistem kullanıcı bilgilerini veritabanından çeker.  
3. Profil sayfasında aşağıdaki bilgiler görüntülenir:  
   - Ad ve Soyad  
   - TC numarası (sadece görüntülenir, değiştirilemez)  
   - Telefon numarası  
   - E-posta  
   - Doğum tarihi  
   - Profil fotoğrafı  

### Alternative Flow (Edit Profile)
1. Kullanıcı "Edit" butonuna tıklar.  
2. Kullanıcı aşağıdaki alanları güncelleyebilir:
   - Ad / Soyad  
   - Telefon numarası  
   - E-posta  
   - Doğum tarihi  
   - Profil fotoğrafı  
3. Sistem alan doğrulamasını yapar:
   - Telefon: 10–11 haneli, sadece rakam  
   - E-posta: `@` ve domain içermeli  
   - Doğum tarihi: geçerli bir tarih, gelecekte olamaz  
4. Tüm doğrulamalar geçerse:
   - Sistem veritabanını günceller.  
   - Mesaj: `"Profile updated successfully"`  
5. Bir doğrulama hatası varsa:
   - Sistem kullanıcıya uygun hata mesajını gösterir (ör. `"Geçersiz telefon numarası"`, `"Geçersiz e-posta formatı"`, `"Geçersiz doğum tarihi"`).  

---

## Special Requirements

- Kullanıcı TC numarası sadece görüntülenebilir, değiştirilemez.  
- Şifre değişikliği **login sayfasında şifre sıfırlama akışı** üzerinden yapılır.  
- Hatalı giriş sayısı takip edilir ve güvenlik için limit aşımında kullanıcı bilgilendirilir.  
- Profil fotoğrafı yükleme işlemi, yalnızca geçerli format ve boyutlarda kabul edilir.  

---

# Functional Requirements (FR)

| FR ID  | Actor       | Behavior / Description | Condition | Degree | Veri Sözlüğü |
|--------|------------|----------------------|-----------|--------|--------------|
| FR-01  | User       | Kullanıcı login sayfasını açar | Uygulama başlatıldığında | Mandatory | - |
| FR-02  | User       | TC ve şifre girer | Kullanıcı login yapmak istiyor | Mandatory | TC: VARCHAR(11), Şifre: VARCHAR(8+) |
| FR-03  | System     | TC formatını doğrular | Kullanıcı TC girdiğinde | Mandatory | TC: 11 haneli, rakam |
| FR-04  | System     | Şifre formatını doğrular | Kullanıcı şifre girdiğinde | Mandatory | Şifre: 8+ karakter, harf ve sayı |
| FR-05  | System     | Kullanıcıyı veritabanında kontrol eder | TC girildiğinde | Mandatory | User Table: tc_no |
| FR-06  | System     | Hatalı TC için mesaj gösterir | TC sistemde yoksa | Mandatory | Mesaj: "Kullanıcı mevcut değil" |
| FR-07  | System     | Şifre hatalıysa mesaj gösterir | TC doğru ama şifre yanlışsa | Mandatory | Mesaj: "Kullanıcı adı veya şifre hatalı" |
| FR-08  | System     | Hatalı girişleri kaydeder | Her login denemesi sonrası | Mandatory | LoginAttempt Table: user_id, attempt_time, successful |
| FR-09  | System     | Maksimum 10 hatalı giriş sonrası SMS ve e-posta gönderir | 11. hatalı girişte | Mandatory | Notification Service: phone_number, email, reset_token |
| FR-10  | System     | Başarılı girişte kullanıcıyı profil sayfasına yönlendirir | TC ve şifre doğruysa | Mandatory | - |
| FR-11  | User       | Profil sayfasını açar | Başarılı giriş sonrası | Mandatory | - |
| FR-12  | System     | Kullanıcının profil bilgilerini çeker | Profil sayfası açıldığında | Mandatory | User Table: first_name, last_name, tc_no, phone_number, email, birth_date, profile_photo |
| FR-13  | User       | Profil bilgilerini görüntüler | Profil sayfası açıldığında | Mandatory | - |
| FR-14  | User       | Profil bilgilerini günceller | "Edit" butonuna tıklanırsa | Mandatory | User Table: first_name, last_name, phone_number, email, birth_date, profile_photo |
| FR-15  | System     | Alan doğrulaması yapar (telefon, e-posta, doğum tarihi, fotoğraf) | Kullanıcı güncelleme yaparken | Mandatory | Telefon: 10-11 rakam, Email: valid format, Doğum tarihi: geçmiş veya bugünün tarihi, Fotoğraf: geçerli format |
| FR-16  | System     | Geçerli güncellemelerde veritabanını günceller | Tüm doğrulamalar geçerli ise | Mandatory | User Table: updated fields |
| FR-17  | System     | Hatalı veri girildiğinde kullanıcıya mesaj gösterir | Validation başarısızsa | Mandatory | Mesaj: "Geçersiz telefon numarası", "Geçersiz e-posta formatı", "Geçersiz doğum tarihi" |
| FR-18  | User       | Şifre sıfırlama talebi oluşturur | Hatalı giriş limitini aşarsa | Mandatory | PasswordResetRequest Table: user_id, reset_token, request_time, status |
| FR-19  | System     | Şifre sıfırlama linkini kullanıcıya gönderir | Şifre sıfırlama talebi oluşturulduğunda | Mandatory | Notification Service: email, SMS |
| FR-20  | User       | Şifre sıfırlama sayfasında yeni şifre belirler | Geçerli reset token ile | Mandatory | Şifre: 8+ karakter, harf ve sayı |
| FR-21  | System     | Yeni şifreyi doğrular ve kaydeder | Kullanıcı yeni şifre belirlediğinde | Mandatory | User Table: password |
| FR-22  | System     | Başarılı şifre güncelleme mesajı gösterir | Şifre geçerli formatta ve güncellendiğinde | Mandatory | Mesaj: "Şifre başarıyla güncellendi" |

---

## Veri Sözlüğü

- **TC (tc_no):** 11 haneli, yalnızca rakam. Kullanıcının benzersiz kimlik numarası.  
- **Şifre (password):** Minimum 8 karakter, hem harf hem sayı içermeli.  
- **Telefon (phone_number):** 10–11 haneli, sadece rakam.  
- **E-posta (email):** `@` ve geçerli domain içermeli.  
- **Doğum tarihi (birth_date):** Geçerli tarih, gelecekte olamaz.  
- **Profil fotoğrafı (profile_photo):** Geçerli format (jpg/png), boyut sınırlı.  
- **LoginAttempt:** Kullanıcının her login denemesini kaydeden tablo; alanlar: user_id, attempt_time, successful.  
- **PasswordResetRequest:** Şifre sıfırlama taleplerini tutar; alanlar: user_id, reset_token, request_time, status.  
- **Notification Service:** SMS ve e-posta gönderimi için kullanılan servis; alanlar: phone_number, email, reset_token.  
- **Mesajlar:** Kullanıcıya gösterilecek hata veya başarı mesajları, kullanıcı deneyimi için net olmalıdır.
