# Android Deveveloper Test at DOT Indonesia

## ENGINEERING TEST

Sebuah sekolah swasta SMA Pasti Pintar sedang mengembangkan aplikasi mobile untuk album kenangan para alumni nya. Fitur-fitur tersebut dapat dijelaskan sebagai berikut

1. Sumber Data
   - [x] Menggunakan data Album & Photo dari https://gorest.co.in/
   - [x] Untuk mendapatkan auth token dalam mengakses API tersebut bisa daftar terlebih dahulu (gratis)
   
2. Album Photo
   - [x] (Must have) List album berupa gambar dan judul album.
   - [x] (Must have) List album dapat di scroll sesuai jumlah data dalam paginasinya dan menggunakan model infinite scroll untuk mengambil list album di halaman selanjutnya
   - [x] (Nice to have) Update data album dengan menggunakan metode swap to refresh
   - [x] (Must have) Karena pada API tidak ada gambar pada data album, maka gambar album bisa di replace menggunakan gambar dummy yang anda siapkan sendiri. Siapkan beberapa gambar agar gambar album bisa bervariasi / tidak monoton. Boleh diacak / random dalam memilih gambarnya
   - [x] (Nice to have) Untuk UX yang lebih bagus saat loading gambar, container gambar harus berisi gambar default yang kemudian akan ganti menjadi gambar aslinya setelah loading gambar selesai
   - [x] (Must have) Jika album di pilih, akan menuju list koleksi photo

3. Koleksi Photo
   - [x] (Must have) Karena tidak ada API yang menghubungkan antara album photo dengan koleksi photo, maka saat album di klik, boleh langsung ambil data dari API photo tanpa melihat ID albumnya
   - [x] (Must have) List photo ditampilkan dalam bentuk thumbnail dan grid dengan model 3 data ke samping.
   - [x] (Must have) Paginasi berupa infinite scroll
   - [x] (Nice to have) Untuk UX yang lebih bagus saat loading gambar, container gambar harus berisi gambar default yang kemudian akan ganti menjadi gambar aslinya setelah loading gambar selesai 
   - [x] (must have) Jika photo dipilih maka akan menampilkan popup foto dan judul foto

4. Koneksi Internet
   - [x] (Must have) Jika tidak ada koneksi internet, ada pemberitahuan jika internet tidak tersambung
   - [ ] (Nice to have) User masih dapat melihat data album / foto yang sudah di load sebelumnya jika tidak ada internet 

## KNOWLEDGE & EXPERIENCE

1. Sudah berapa lama anda mulai menggunakan Kotlin?
   
   - Jawab: Sejak Juli 2019
   
2. Apa saja library Kotlin yang sering atau biasa anda pakai? Jelaskan kegunaannya bagi anda.

   - Jawab:
      - <a href="https://github.com/bumptech/glide">Glide</a> : Untuk kebutuhan menampilkan gambar
      - <a href="https://square.github.io/retrofit/">Retrofit</a> : Sebagai Rest client untuk memudahkan dalam memparsing data JSON yang kemudian dijadikan Kotlin Data Class.
      - <a href="https://github.com/intuit/ssp">SSP</a> : Untuk memastikan text memiliki ukuran yang sama walaupun pada screen size yang berbeda
      - <a href="https://github.com/intuit/sdp">SDP</a> : Untuk memastikan layout memiliki ukuran yang sama walaupun pada screen size yang berbeda
      - <a href="https://github.com/chibatching/Kotpref">Kotpref</a> : Mempermudah dalam membuat SharedPreferences
   
3. Jelaskan penerapan clean code atau design pattern yang biasa anda gunakan dan manfaatnya untuk anda.
   - Jawab: Sejak awal menggunakan kotlin saya sudah belajar untuk menggunakan design pattern MVVM. Tujuannya yaitu untuk memisahkan antara User Interface (View) dengan proses bisnis (Logic). Sehingga kodingan akan terlihat lebih simple dan mudah dikelola, karena jika tidak dipisah akan menyulitkan dalam melacak fungsi Logic di class yang memiliki banyak sekali fungsi.

4. Apakah tantangan terbesar yang pernah anda temui dalam mengerjakan aplikasi Kotlin dan bagaimana anda menyelesaikannya dari sisi engineering? 
   - Jawab: Ketika requirement di dalam aplikasi melebihi kemampuan saya, seperti membuat fitur yang belum pernah saya buat sebelumnya. Kemudian untuk menyelesaikannya saya biasanya membutuhkan waktu yang cukup lama untuk riset dan belajar. Tetapi ketika deadlinenya sangat dekat dan tidak memungkinkan saya untuk riset lebih dalam, saya akan menerapkan apa yang saya dapatkan walaupun saya belum terlalu memahaminya. Kemudian ketika ada perubahaan saya akan merasa bingung untuk merubahnya dikarenakan belum terlalu memahaminya. Untuk menyelesaikan masalah tersebut biasanya saya akan meriset jauh jauh hari, jadi ketika ada requirement yang belum saya kuasai, saya akan mempelajarinya jauh jauh hari walaupun fitur tersebut belum waktunya dikerjakan.

5. Untuk efisiensi pengerjaan project dalam tim, bagaimana workflow anda dari proses development hingga merilis aplikasi hingga bisa digunakan oleh tester / client?
   - Jawab: Diawali dengan menentukan goals atau target dalam jangka waktu tertentu, kemudian menggunakan Trello atau spreedsheet untuk task management dan menggunakan git sebagai version control untuk dapat bekerja bersama dalam satu project. Setelah target tercapai apps akan dibuild untuk di tes oleh tester dan kemudian dievaluasi jika ada permasalahan dan perubahan.

6. Jelaskan teknik-teknik apa saja yang dapat meningkatkan performance dan keamanan sebuah aplikasi Kotlin.
   - Jawab: Dengan menggunakan ProGuard. ProGuard Android adalah tools proteksi code yang meliputi shrink (memperkecil ukuran dengan menghapus class dan method yang tidak dipakai), optimize (optimasi bytecode method), dan obfuscate (mengganti/rename class, method, variable, dll secara acak). Dengan menggunakan ProGuard, kodingan yang kita buat akan secara otomatis dioptimasi dan diproteksi. Dengan menghapus kode yang tidak terpakai dapat meningkatkan performance dari aplikasi, kemudian dengan kodingan kita menjadi sulit terbaca ketika ada yang men-decompile tentu dapat mengamankan kodingan.

7. Apakah anda bersedia onsite di Malang? 

   - Jawab: Bersedia
