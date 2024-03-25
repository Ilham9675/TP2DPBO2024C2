import javax.swing.*;
import javax.swing.table.DefaultTableModel;
import javax.swing.table.TableCellRenderer;
import javax.swing.table.TableColumnModel;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.sql.*;
import java.util.ArrayList;

public class Menu extends JFrame{


    public static void main(String[] args) {

        // isi window
        Menu window = new Menu();
        // letakkan window di tengah layar
        window.setLocationRelativeTo(null);

        // ubah warna background
        window.mainPanel.setBackground(Color.GRAY);
        // tampilkan window
        window.setSize(500,400);
        window.setContentPane(window.mainPanel);
        window.setVisible(true);
        // agar program ikut berhenti saat window diclose
        window.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
    }

    // index baris yang diklik
    private int selectedIndex = -1;
    // list untuk menampung semua mahasiswa
    private ArrayList<Mahasiswa> listMahasiswa;

    private Database database;

    private JPanel mainPanel;
    private JTextField nimField;
    private JTextField namaField;
    private JTable mahasiswaTable;
    private JButton AddUpdateButton;
    private JButton CancelButton;
    private JComboBox jenisKelaminComboBox;
    private JButton DeleteButton;
    private JLabel titleLabel;
    private JLabel nimLabel;
    private JLabel namaLabel;
    private JLabel jenisKelaminLabel;

    private JComboBox nilaicomboBox;
    private JComboBox kelascomboBox;

    // constructor
    public Menu() {
        // inisialisasi listMahasiswa
        listMahasiswa = new ArrayList<>();

        database = new Database();
        // isi listMahasiswa

        mahasiswaTable.setModel(setTable());

        // isi tabel mahasiswa
        mahasiswaTable.setModel(setTable());
        resizeColumnWidth(mahasiswaTable);

        // ubah styling title
        titleLabel.setFont(new Font("Arial", Font.BOLD, 20));

        // atur isi combo box
        jenisKelaminComboBox.addItem("Laki-laki");
        jenisKelaminComboBox.addItem("Perempuan");

        nilaicomboBox.addItem("A");
        nilaicomboBox.addItem("B");
        nilaicomboBox.addItem("C");
        nilaicomboBox.addItem("D");
        nilaicomboBox.addItem("E");

        kelascomboBox.addItem("C1");
        kelascomboBox.addItem("C2");

        // sembunyikan button delete
        clearForm();

        // saat tombol add/update ditekan
        AddUpdateButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                if(selectedIndex == -1){
                    insertData();

                }else{
                    updateData();
                }
            }
        });
        // saat tombol delete ditekan
        DeleteButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                deleteData();
            }
        });
        // saat tombol cancel ditekan
        CancelButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                clearForm();
            }
        });
        // saat salah satu baris tabel ditekan
        mahasiswaTable.addMouseListener(new MouseAdapter() {
            @Override
            public void mousePressed(MouseEvent e) {
                // ubah selectedIndex menjadi baris tabel yang diklik
                selectedIndex = mahasiswaTable.rowAtPoint(e.getPoint());

                // simpan value textfield dan combo box
                String nim = nimField.getText();
                String nama = namaField.getText();
                if (jenisKelaminComboBox.getSelectedItem() != null) {
                    String jeniskelamin = jenisKelaminComboBox.getSelectedItem().toString();
                }
                if (jenisKelaminComboBox.getSelectedItem() != null) {
                    String kelas = kelascomboBox.getSelectedItem().toString();
                }
                if (jenisKelaminComboBox.getSelectedItem() != null) {
                    String nilai = nilaicomboBox.getSelectedItem().toString();
                }


                // ubah isi textfield dan combo box

                nimField.setText(mahasiswaTable.getValueAt(selectedIndex, 1).toString());
                namaField.setText(mahasiswaTable.getValueAt(selectedIndex, 2).toString());
                jenisKelaminComboBox.setSelectedItem(mahasiswaTable.getValueAt(selectedIndex, 3));
                kelascomboBox.setSelectedItem(mahasiswaTable.getValueAt(selectedIndex, 4));
                nilaicomboBox.setSelectedItem(mahasiswaTable.getValueAt(selectedIndex, 5));

                // buat field NIM tidak dapat diedit
                nimField.setEditable(false);

                // ubah button "Add" menjadi "Update"
                AddUpdateButton.setText("Update");

                // tampilkan button delete
                DeleteButton.setVisible(true);
            }
        });
    }

    public final DefaultTableModel setTable() {
        //Object[] column = {"No","NIM","Nama","Jenis Kelamin","Kelas","Nilai"};
        Object[] column = {"No","NIM","Nama","Jenis Kelamin","Kelas","Nilai"};
        DefaultTableModel temp = new DefaultTableModel(null,column){
            public boolean isCellEditable(int row, int column) {
               return false; // Ini membuat semua sel tidak bisa diedit
           }
        };

        try {
            ResultSet resultSet = database.selectcekQuery("SELECT * FROM mahasiswa");
            int i = 0;
            while (resultSet.next()){
                Object[] row = new Object[6];
                row[0] = i+ 1;
                row[1] = resultSet.getString("nim");
                row[2] = resultSet.getString("nama");
                row[3] = resultSet.getString("jenis_kelamin");
                row[4] = resultSet.getString("kelas");
                row[5] = resultSet.getString("nilai");

                temp.addRow(row);
                i++;
            }
        }catch (SQLException e){
            throw new RuntimeException();
        }


        return temp; // return juga harus diganti
    }

    public void resizeColumnWidth(JTable table) {
        final TableColumnModel columnModel = table.getColumnModel();
        for (int column = 0; column < table.getColumnCount(); column++) {
            int width = 15; // Min width
            for (int row = 0; row < table.getRowCount(); row++) {
                TableCellRenderer renderer = table.getCellRenderer(row, column);
                Component comp = table.prepareRenderer(renderer, row, column);
                width = Math.max(comp.getPreferredSize().width + 1 , width);
            }
            if(width > 300) width = 300;
            columnModel.getColumn(column).setPreferredWidth(width);
        }
    }

    public void insertData() {
        // ambil value dari textfield dan combobox
        String nama = namaField.getText();
        String nim = nimField.getText();
        Object jenisKelaminObject = jenisKelaminComboBox.getSelectedItem();
        Object kelasObject = kelascomboBox.getSelectedItem();
        Object nilaiObject = nilaicomboBox.getSelectedItem();

        // cek jika ada input yang kosong atau combobox tidak dipilih
        if (nama.isEmpty() || nim.isEmpty() || jenisKelaminObject == null || kelasObject == null || nilaiObject == null) {
            JOptionPane.showMessageDialog(null, "Error: Semua field harus diisi!", "Error", JOptionPane.ERROR_MESSAGE);
        } else {
            String jeniskelamin = jenisKelaminObject.toString();
            String kelas = kelasObject.toString();
            String nilai = nilaiObject.toString();

            // check if NIM already exists
            ResultSet rs = database.selectcekQuery("SELECT * FROM mahasiswa WHERE nim = '" + nim + "'");
            try {
                if (!rs.next()) {
                    // NIM does not exist, proceed with insert
                    database.insertUpdateDelateQuery("INSERT INTO mahasiswa (nim,nama,jenis_kelamin,kelas, nilai) VALUES ('" + nim + "', '" + nama +"','" + jeniskelamin + "','" + kelas + "','" + nilai + "')");

                    // update tabel
                    mahasiswaTable.setModel(setTable());
                    resizeColumnWidth(mahasiswaTable);

                    // bersihkan form
                    clearForm();

                    // feedback
                    JOptionPane.showMessageDialog(null, "Data berhasil ditambahkan");
                } else {
                    // NIM exists, show error message
                    JOptionPane.showMessageDialog(null, "Error: NIM sudah ada!", "Error", JOptionPane.ERROR_MESSAGE);
                }
            } catch (SQLException e) {
                e.printStackTrace();
            }
        }
    }





    public void updateData() {
        // ambil data dari form
        String nama = namaField.getText();
        String nim = nimField.getText();
        String jeniskelamin = jenisKelaminComboBox.getSelectedItem().toString();
        String kelas = kelascomboBox.getSelectedItem().toString();
        String nilai = nilaicomboBox.getSelectedItem().toString();

        // cek jika ada input yang kosong
        if (nama.isEmpty() || nim.isEmpty() || jeniskelamin.isEmpty() || kelas.isEmpty() || nilai.isEmpty()) {
            JOptionPane.showMessageDialog(null, "Error: Semua data harus diisi!", "Error", JOptionPane.ERROR_MESSAGE);
        } else {
            database.insertUpdateDelateQuery("UPDATE `mahasiswa` SET `nim` = '"+ nim +"', `nama` = '"+ nama +"', `jenis_kelamin` = '"+ jeniskelamin +"', `kelas` = '"+ kelas +"', `nilai` = '"+ nilai +"' WHERE `mahasiswa`.nim = '"+ nim +"'");

            // update tabel
            mahasiswaTable.setModel(setTable());
            resizeColumnWidth(mahasiswaTable);

            // bersihkan form
            clearForm();

            // feedback
            JOptionPane.showMessageDialog(null, "Data berhasil diubah");
        }
    }


    public void deleteData() {
        // hapus data dari list
        String nim = nimField.getText();
        if (nim.isEmpty()) {
            JOptionPane.showMessageDialog(null, "Error: NIM harus diisi!", "Error", JOptionPane.ERROR_MESSAGE);
        } else {
            // buat dialog konfirmasi
            int dialogResult = JOptionPane.showConfirmDialog(null, "Apakah Anda yakin ingin menghapus data ini?", "Konfirmasi", JOptionPane.YES_NO_OPTION);

            // jika pengguna memilih 'Ya', hapus data
            if(dialogResult == JOptionPane.YES_OPTION) {
                // hapus data dari database
                database.insertUpdateDelateQuery("DELETE FROM mahasiswa WHERE nim = '"+ nim +"'");

                // update tabel
                mahasiswaTable.setModel(setTable());
                resizeColumnWidth(mahasiswaTable);

                // bersihkan form
                clearForm();

                // feedback
                JOptionPane.showMessageDialog(null, "Data berhasil dihapus");
            }
        }
    }

    public void clearForm() {
        // kosongkan semua texfield dan combo box

        namaField.setText("");
        nimField.setText("");

        jenisKelaminComboBox.setSelectedItem(null);
        kelascomboBox.setSelectedItem(null);
        nilaicomboBox.setSelectedItem(null);



        // ubah button "Update" menjadi "Add"
        AddUpdateButton.setText("Add");
        // sembunyikan button delete
        DeleteButton.setVisible(false);

        nimField.setEditable(true);

        // ubah selectedIndex menjadi -1 (tidak ada baris yang dipilih)
        selectedIndex = -1;
    }
//UPDATE `mahasiswa` SET `kelas` = 'C2', `nilai` = 'A' WHERE `mahasiswa`.`id` = 20;
    private void populateList() {
        listMahasiswa.add(new Mahasiswa("2203999", "Amelia Zalfa Julianti", "Perempuan","C1","A"));
        listMahasiswa.add(new Mahasiswa("2202292", "Muhammad Iqbal Fadhilah", "Laki-laki","C2","A"));
        listMahasiswa.add(new Mahasiswa("2202346", "Muhammad Rifky Afandi", "Laki-laki","C2","A"));
        listMahasiswa.add(new Mahasiswa("2210239", "Muhammad Hanif Abdillah", "Laki-laki","C1","A"));
        listMahasiswa.add(new Mahasiswa("2202046", "Nurainun", "Perempuan","C1","A"));
        listMahasiswa.add(new Mahasiswa("2205101", "Kelvin Julian Putra", "Laki-laki","C2","A"));
        listMahasiswa.add(new Mahasiswa("2200163", "Rifanny Lysara Annastasya", "Perempuan","C2","A"));
        listMahasiswa.add(new Mahasiswa("2202869", "Revana Faliha Salma", "Perempuan","C2","A"));
        listMahasiswa.add(new Mahasiswa("2209489", "Rakha Dhifiargo Hariadi", "Laki-laki","C1","A"));
        listMahasiswa.add(new Mahasiswa("2203142", "Roshan Syalwan Nurilham", "Laki-laki","C2","A"));
        listMahasiswa.add(new Mahasiswa("2200311", "Raden Rahman Ismail", "Laki-laki","C2","A"));
        listMahasiswa.add(new Mahasiswa("2200978", "Ratu Syahirah Khairunnisa", "Perempuan","C2","A"));
        listMahasiswa.add(new Mahasiswa("2204509", "Muhammad Fahreza Fauzan", "Laki-laki","C2","A"));
        listMahasiswa.add(new Mahasiswa("2205027", "Muhammad Rizki Revandi", "Laki-laki","C2","A"));
        listMahasiswa.add(new Mahasiswa("2203484", "Arya Aydin Margono", "Laki-laki","C1","A"));
        listMahasiswa.add(new Mahasiswa("2200481", "Marvel Ravindra Dioputra", "Laki-laki","C2","A"));
        listMahasiswa.add(new Mahasiswa("2209889", "Muhammad Fadlul Hafiizh", "Laki-laki","C2","A"));
        listMahasiswa.add(new Mahasiswa("2206697", "Rifa Sania", "Perempuan","C2","A"));
        listMahasiswa.add(new Mahasiswa("2207260", "Imam Chalish Rafidhul Haque", "Laki-laki","C1","A"));
        listMahasiswa.add(new Mahasiswa("2204343", "Meiva Labibah Putri", "Perempuan","C1","A"));
    }
}
