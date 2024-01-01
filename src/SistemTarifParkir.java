import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class SistemTarifParkir extends JFrame implements ActionListener {

    private JComboBox<String> jenisParkir;
    private JComboBox<String> tipeKendaraan;
    private JTextField waktuMasuk;
    private JTextField waktuKeluar;
    private JTextField lama;
    private JButton hitung;
    private JLabel jumlahBayar;
    private JLabel jenisTarifLabel;
    private JLabel jumlahBayarLabel;
    private JLabel lamaLabel;
    private JLabel waktuKeluarLabel;
    private JLabel waktuMasukLabel;
    private JLabel jenisKendaraanLabel;
    private JPanel Panell;

    public SistemTarifParkir() {
        setTitle("Sistem Tarif Parkir");
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setLayout(new FlowLayout());
        jenisTarifLabel = new JLabel("Jenis Tarif: ");
        jenisParkir = new JComboBox<>(new String[]{"Umum", "Pegawai", "Inap"});
        tipeKendaraan = new JComboBox<>(new String[]{"Motor", "Mobil", "Bus"});

        Panell = new JPanel();
        waktuMasuk = new JTextField(20);
        waktuMasukLabel = new JLabel("Waktu Masuk: ");
        waktuKeluar = new JTextField(10);
        lama = new JTextField(10);
        hitung = new JButton("Hitung");
        jumlahBayar = new JLabel("Jumlah Bayar:");

        add(jenisParkir);
        add(tipeKendaraan);
        add(Panell);
        add(waktuMasukLabel);
        add(waktuMasuk);
        add(waktuKeluar);

        add(lama);
        add(hitung);
        add(jumlahBayar);


        hitung.addActionListener(this);

        setSize(300, 200);
        setVisible(true);
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        if (e.getSource() == hitung) {
            String jenis = jenisParkir.getSelectedItem().toString();
            String tipe = tipeKendaraan.getSelectedItem().toString();
            String waktuMasukStr = waktuMasuk.getText();
            String waktuKeluarStr = waktuKeluar.getText();
            int lamaHari = 0;

            try {
                int waktuMasukInt = Integer.parseInt(waktuMasukStr.substring(0, 2)) * 60 + Integer.parseInt(waktuMasukStr.substring(3, 5)) + Integer.parseInt(waktuMasukStr.substring(6, 8));
                int waktuKeluarInt = Integer.parseInt(waktuKeluarStr.substring(0, 2)) * 60 + Integer.parseInt(waktuKeluarStr.substring(3, 5)) + Integer.parseInt(waktuKeluarStr.substring(6, 8));
                int lamaInt = waktuKeluarInt - waktuMasukInt;

                if (jenis.equals("Inap")) {
                    lamaHari = lamaInt / (60 * 60 * 24);
                    lamaInt = lamaInt - (lamaHari * 60 * 60 * 24);
                }

                int tarif = 0;
                if (jenis.equals("Umum")) {
                    if (tipe.equals("Motor")) {
                        tarif = 2000 * lamaInt;
                    } else if (tipe.equals("Mobil")) {
                        tarif = 5000 * lamaInt;
                    } else if (tipe.equals("Bus")) {
                        tarif = 10000 * lamaInt;
                    }
                } else if (jenis.equals("Pegawai")) {
                    if (tipe.equals("Motor")) {
                        tarif = 1000 * lamaInt;
                    } else if (tipe.equals("Mobil")) {
                        tarif = 2500 * lamaInt;
                    } else if (tipe.equals("Bus")) {
                        tarif = 5000 * lamaInt;
                    }
                } else if (jenis.equals("Inap")) {
                    tarif = 50000 * lamaHari;
                }

                jumlahBayar.setText("Rp. " + tarif);
            } catch (NumberFormatException ex) {
                JOptionPane.showMessageDialog(this, "Format waktu salah.", "Peringatan", JOptionPane.WARNING_MESSAGE);
            }


        }
    }
    public static void main (String[]args){
        new SistemTarifParkir();
    }
}