# Android Deveveloper Test at DOT Indonesia

- ENGINEERING TEST

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
- [ ] (must have) Jika photo dipilih maka akan menampilkan popup foto dan judul foto

4. Koneksi Internet
- [x] (Must have) Jika tidak ada koneksi internet, ada pemberitahuan jika internet tidak tersambung
- [ ] (Nice to have) User masih dapat melihat data album / foto yang sudah di load sebelumnya jika tidak ada internet 
