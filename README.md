# Universitet İdarəetmə Sistemi

## Ümumi Məlumat
**Universitet İdarəetmə Sistemi** universitetlə bağlı əməliyyatları idarə etmək üçün hazırlanmış Spring Boot tətbiqidir. Bu sistem tələbələri, müəllimləri, işçiləri və qeydiyyatları idarə etmək üçün RESTful API-lər təqdim edir. 

Bu sistem universitet daxilində aşağıdakı əsas funksiyaları yerinə yetirir:
- Tələbələrin qeydiyyatı və idarə edilməsi
- Müəllimlərin və işçilərin idarə edilməsi
- Dərs qeydiyyatlarının və kursların izlənməsi
- İstifadəçilər üçün autentifikasiya və avtorizasiya

## İstifadə Olunan Texnologiyalar
- **Spring Boot** – Backend üçün
- **Spring Data JPA** – ORM və verilənlər bazası ilə iş üçün
- **Lombok** – Kodun daha səliqəli və qısa olması üçün
- **Gradle** – Layihə idarəetmə və asılılıqların idarə olunması üçün
- **MySQL** – Verilənlər bazası
- **Spring Security** – İstifadəçi identifikasiyası və təhlükəsizlik üçün

## Quraşdırma və İşə Salma
1. Repozitoriyanı klonlayın:
   ```bash
   git clone <repo-url>
   ```
2. Layihə qovluğuna keçin:
   ```bash
   cd university-management
   ```
3. Layihəni Gradle ilə yığın:
   ```bash
   ./gradlew build
   ```
4. Tətbiqi işə salın:
   ```bash
   ./gradlew bootRun
   ```
5. Tətbiq `http://localhost:8080` ünvanında işləyəcək.

## API Endpoint-lər

### İşçi API-si
- **POST** `/com.aladdin/university-site/employee/new` - Yeni işçi əlavə edir
- **PUT** `/com.aladdin/university-site/employee/put` - Mövcud işçini yeniləyir
- **GET** `/com.aladdin/university-site/employee/get` - Bütün işçiləri qaytarır

### Qeydiyyat API-si
- **POST** `/com.aladdin/university-site/enrollment/new` - Yeni qeydiyyat əlavə edir
- **GET** `/com.aladdin/university-site/enrollment/get` - Bütün qeydiyyatları qaytarır

### Tələbə API-si
- **POST** `/com.aladdin/university-site/student/new` - Yeni tələbə əlavə edir
- **GET** `/com.aladdin/university-site/student/get` - Bütün tələbələri qaytarır

### Müəllim API-si
- **POST** `/com.aladdin/university-site/teacher/new` - Yeni müəllim əlavə edir
- **GET** `/com.aladdin/university-site/teacher/get` - Bütün müəllimləri qaytarır

### Universitet API-si
- **POST** `/com.aladdin/university-site/university/new` - Yeni universitet əlavə edir
- **GET** `/com.aladdin/university-site/university/get` - Bütün universitetləri qaytarır

### İstifadəçi API-si
- **POST** `/com.aladdin/university-site/user/new` - Yeni istifadəçi əlavə edir
- **GET** `/com.aladdin/university-site/user/get` - Bütün istifadəçiləri qaytarır

## Verilənlər Bazası Strukturu
Tətbiq ORM əsaslı verilənlər bazası strukturundan istifadə edir. Əsas entitilər və onların əlaqələri:
- **Tələbə** (ID, ad, soyad, qeydiyyat tarixi, universitet ID)
- **Müəllim** (ID, ad, soyad, fənn, universitet ID)
- **İşçi** (ID, ad, soyad, vəzifə, universitet ID)
- **Universitet** (ID, ad, ünvan, rektor)
- **Qeydiyyat** (ID, tələbə ID, kurs ID, tarix)

## Təhlükəsizlik və İstifadəçi İdarəetməsi
Sistem **Spring Security** istifadə edərək autentifikasiya və avtorizasiyanı təmin edir. Aşağıdakı istifadəçi rolları mövcuddur:
- **Admin** – Sistemi tam idarə edə bilər
- **Müəllim** – Tələbələri və kursları idarə edə bilər
- **Tələbə** – Öz dərslərini və qeydiyyatlarını görə bilər


