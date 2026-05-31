# Feature Elaboration

> **Dibuat:** {{tanggal}}
> **Berdasarkan:** `implementfeature.md`

---

## Fitur 1: [Nama]

### Flow / Alur
<!-- AI akan gambarkan alur fitur ini dari awal sampai akhir, lalu tanya ke user -->

**Proposed Flow:**
1. User membuka halaman
2. System memuat data dari API
3. User melihat list
4. User tap item → detail
5. User bisa edit/hapus

**Pertanyaan:**
1. Apakah alur di atas sudah sesuai? ( [ ] Ya / [ ] Tidak — sebutkan koreksi)
2. Apakah perlu konfirmasi sebelum hapus? ( [ ] Ya / [ ] Tidak)
3. Data baru harus muncul di: ( [ ] Atas list / [ ] Bawah list / [ ] Sesuai urutan)

### UI / Screen yang Dibutuhkan
- [ ] List Screen
- [ ] Detail Screen
- [ ] Create/Edit Form Screen
- [ ] Loading state ✅ (built-in template)
- [ ] Error state ✅ (built-in template)
- [ ] Empty state ✅ (built-in template)

### Data Model
```kotlin
@Serializable
data class SampleItem(
    val id: Int,
    val title: String,
    val body: String
)
```

**Pertanyaan:**
1. Apakah field di atas sudah sesuai? ( [ ] Ya / [ ] Tidak — sebutkan tambahan)

### API Endpoint
- `GET /api/items` → List
- `GET /api/items/{id}` → Detail
- `POST /api/items` → Create
- `PUT /api/items/{id}` → Update
- `DELETE /api/items/{id}` → Delete

**Pertanyaan:**
1. Apakah endpoint REST di atas sudah sesuai? ( [ ] Ya / [ ] Tidak)

---

> **Instruksi:** AI akan membuat bagian ini untuk setiap fitur. User jawab pertanyaan per fitur.
