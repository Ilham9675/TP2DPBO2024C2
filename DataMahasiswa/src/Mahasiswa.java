import java.util.Objects;

public class Mahasiswa {
    private String nim;
    private String nama;
    private String jenisKelamin;
    private String nilai;
    private String kelas;

    public Mahasiswa(String nim, String nama, String jenisKelamin,String kelas,String nilai) {
        this.nim = nim;
        this.nama = nama;
        this.jenisKelamin = jenisKelamin;
        this.kelas = kelas;
        this.nilai = nilai;
    }

    public void setNim(String nim) {
        this.nim = nim;
    }

    public void setNama(String nama) {
        this.nama = nama;
    }

    public void setJenisKelamin(String jenisKelamin) {
        this.jenisKelamin = jenisKelamin;
    }

    public String getNim() {
        return this.nim;
    }

    public String getNama() {
        return this.nama;
    }

    public String getJenisKelamin() {
        return this.jenisKelamin;
    }

    @Override
    public boolean equals(Object obj) {
        if (this == obj) {
            return true;
        }
        if (obj == null || getClass() != obj.getClass()) {
            return false;
        }
        Mahasiswa mahasiswa = (Mahasiswa) obj;
        return Objects.equals(nim, mahasiswa.nim) &&
                Objects.equals(nama, mahasiswa.nama) &&
                Objects.equals(jenisKelamin, mahasiswa.jenisKelamin) &&
                Objects.equals(kelas, mahasiswa.kelas) &&
                Objects.equals(nilai, mahasiswa.nilai);
    }

    public String getNilai() {
        return nilai;
    }

    public void setNilai(String nilai) {
        this.nilai = nilai;
    }

    public String getKelas() {
        return kelas;
    }

    public void setKelas(String kelas) {
        this.kelas = kelas;
    }
}
