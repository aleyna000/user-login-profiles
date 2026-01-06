# Test Scenarios - User Login & Profile Management

Bu doküman, Java uygulamasında kullanıcı giriş ve profil yönetimi için belirlenen **test senaryolarını** içerir. Senaryolar, hem pozitif hem negatif durumları, validasyon kontrollerini ve alternatif akışları kapsar.  

---

## 1. Login Page Test Scenarios

| FR ID | Test Senaryosu | Input / Action | Expected Result | Notes |
|-------|----------------|----------------|----------------|-------|
| FR-01 | Kullanıcı geçerli TC ve şifre ile giriş yapar | TC: 11 haneli kayıtlı TC, Şifre: 8+ karakter, harf ve sayı | Kullanıcı başarılı giriş yapar, profil sayfasına yönlendirilir | Pozitif senaryo |
| FR-02 | TC numarası hatalı veya sistemde yok | TC: kayıtlı değil, Şifre: geçerli format | Hata mesajı: "Kullanıcı mevcut değil" | Negatif senaryo |
| FR-03 | Şifre hatalı | TC: kayıtlı TC, Şifre: yanlış | Hata mesajı: "Kullanıcı adı veya şifre hatalı" | Negatif senaryo |
| FR-04 | Hatalı giriş sayısı 11’e ulaştığında | TC: kayıtlı, yanlış şifre 11 kez | SMS ve e-posta bildirim gider, şifre sıfırlama linki gönderilir | Alternatif akış |
| FR-05 | TC formatı kontrolü | TC: 11’den kısa/uzun, rakam olmayan karakterler | Hata mesajı gösterilir | Validation |
| FR-06 | Şifre formatı kontrolü | Şifre < 8 karakter, sadece harf veya sayı | Hata mesajı gösterilir | Validation |
| FR-07 | Başarılı giriş sonrası yönlendirme | Geçerli TC ve şifre | Profil sayfasına yönlendirilir | Pozitif senaryo |

---

## 2. Profile Page Test Scenarios

| FR ID | Test Senaryosu | Input / Action | Expected Result | Notes |
|-------|----------------|----------------|----------------|-------|
| FR-08 | Profil bilgilerini görüntüleme | Kullanıcı giriş yapmış | Tüm alanlar görüntülenir: ad, soyad, TC, telefon, e-posta, doğum tarihi, profil fotoğrafı | Pozitif senaryo |
| FR-09 | Profil güncelleme (geçerli veri) | Ad/Soyad/Telefon/Email/Doğum tarihi/Fotoğraf güncelleme | Başarılı güncelleme mesajı: "Profile updated successfully" | Pozitif senaryo |
| FR-10 | Telefon numarası formatı hatalı | 9 haneli veya harf içeriyor | Hata mesajı gösterilir | Validation |
| FR-11 | E-posta formatı hatalı | @ veya domain eksik | Hata mesajı gösterilir | Validation |
| FR-12 | Doğum tarihi gelecekteki tarih | 2050-01-01 gibi | Hata mesajı gösterilir | Validation |
| FR-13 | Profil fotoğrafı geçersiz formatta | .txt dosyası yükleme | Hata mesajı gösterilir | Validation |
| FR-14 | TC numarası değiştirmeye çalışma | Kullanıcı TC alanını düzenler | TC değiştirilemez, hata mesajı | Business rule |

---

## 3. Password Reset Scenarios

| FR ID | Test Senaryosu | Input / Action | Expected Result | Notes |
|-------|----------------|----------------|----------------|-------|
| FR-15 | Kullanıcı hatalı giriş limitini aştığında | 11. hatalı giriş | SMS ve e-posta gönderilir, şifre sıfırlama linki iletilir | Alternatif akış |
| FR-16 | Kullanıcı şifre sıfırlama linkine tıklar | Geçerli reset token | Kullanıcı şifre sıfırlama sayfasına yönlendirilir | Pozitif senaryo |
| FR-17 | Kullanıcı geçersiz/expired token ile giriş yapar | Token: expired veya invalid | Hata mesajı gösterilir | Negatif senaryo |
| FR-18 | Yeni şifre formatı kontrolü | Şifre < 8 karakter veya sadece harf/sayı | Hata mesajı gösterilir | Validation |
| FR-19 | Başarılı şifre sıfırlama | Geçerli token, şifre formatı uygun | Şifre güncellenir, başarı mesajı gösterilir | Pozitif senaryo |

---

## Notlar

- Tüm test senaryoları **pozitif ve negatif durumları** kapsayacak şekilde tasarlanmıştır.  
- Login ve profile sayfasındaki **validation kuralları**:  
  - TC numarası: 11 haneli, sadece rakam  
  - Şifre: minimum 8 karakter, hem harf hem sayı  
  - Telefon: 10–11 rakam, sadece rakam  
  - E-posta: `@` ve domain zorunlu  
  - Doğum tarihi: geçerli tarih, gelecekte olamaz  
- Hatalı giriş sayısı takibi ve şifre sıfırlama akışları **kritik güvenlik senaryoları** olarak eklenmiştir.  

---

