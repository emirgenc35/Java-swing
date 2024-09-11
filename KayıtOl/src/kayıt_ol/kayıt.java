package kayıt_ol;

import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.ButtonGroup;
import javax.swing.JButton;
import javax.swing.JPasswordField;
import java.awt.event.ActionListener;
import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.io.Writer;
import java.util.ArrayList;
import java.util.Scanner;
import java.awt.event.ActionEvent;
import javax.swing.JCheckBox;
import javax.swing.JTextField;
import javax.swing.JRadioButton;

public class kayıt extends JFrame {

	private static final long serialVersionUID = 1L;
	private JPanel contentPane;
	private JPasswordField sifre;
	private JTextField textField;

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					kayıt frame = new kayıt();
					frame.setVisible(true);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}

	/**
	 * Create the frame.
	 */
	public kayıt() {
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 450, 300);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));

		setContentPane(contentPane);
		contentPane.setLayout(null);
		
		JLabel lblKAY = new JLabel("K A Y I T      O L");
		lblKAY.setBounds(154, 11, 86, 25);
		contentPane.add(lblKAY);
		
		JLabel lblKullancAd = new JLabel("Kullanıcı Adı :");
		lblKullancAd.setBounds(37, 50, 72, 14);
		contentPane.add(lblKullancAd);
		
		JLabel lblifre = new JLabel("Şifre : ");
		lblifre.setBounds(37, 103, 46, 14);
		contentPane.add(lblifre);
		
		JRadioButton radio_1 = new JRadioButton("Erkek");
		radio_1.setBounds(131, 155, 109, 23);
		contentPane.add(radio_1);
		
		JRadioButton radio_2 = new JRadioButton("Kadın");
		radio_2.setBounds(261, 155, 109, 23);
		contentPane.add(radio_2);
		
		ButtonGroup bg = new ButtonGroup(); //Radio butonu tek seçim yapmamızı sağlıyor...
		bg.add(radio_1);
		bg.add(radio_2);
		
		JButton button_1 = new JButton("KAYIT OL");
		button_1.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				try(BufferedWriter writer = new BufferedWriter(new FileWriter("kullanici_listesi.txt",true))){
					String kullanici_adi = textField.getText() ;
					String sifre_1 = new String(sifre.getPassword());
					writer.write(kullanici_adi);
					writer.write(" ");
					writer.write(sifre_1);					
					writer.write(" ");
					if(radio_1.isSelected()) {
						writer.write("Erkek");
					}
					if(radio_2.isSelected()) {
						writer.write("Kadın");
					}
					
					
					writer.write("\n");
					String message = "Başarıyla Kayıt Oluşturuldu . İyi Günler Dileriz !!!";
					JOptionPane.showMessageDialog(null, message);
					
					
					
				} catch (IOException e1) {
					// TODO Auto-generated catch block
					e1.printStackTrace();
				}
				
			}
		});
		button_1.setBounds(154, 214, 89, 23);
		contentPane.add(button_1);
		
		sifre = new JPasswordField();
		sifre.setBounds(154, 100, 89, 20);
		contentPane.add(sifre);
		
		JLabel lblCinsiyet = new JLabel("Cinsiyet :");
		lblCinsiyet.setBounds(37, 159, 46, 14);
		contentPane.add(lblCinsiyet);
		
		textField = new JTextField();
		textField.setBounds(154, 47, 86, 20);
		contentPane.add(textField);
		textField.setColumns(10);
		
		JButton btnKaytSil = new JButton("KAYIT SİL");
		btnKaytSil.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				File file = new File("kullanici_listesi.txt");
		        ArrayList<String> updatedList = new ArrayList<>();
		        boolean silme_basarili = false;
		        
		        try (Scanner scanner = new Scanner(file)) {
		            while (scanner.hasNextLine()) {
		                String kullanici_listesi = scanner.nextLine();
		                String[] liste = kullanici_listesi.split(" ");
		                
		                String kullanici_adi = liste[0];
		                String sifre_1 = liste[1];
		                String sifre_3 = new String(sifre.getPassword());
		                // Eğer textField'e girilen kullanıcı adı ve şifre ile eşleşmiyorsa, listeye ekleyin
		                if (!(textField.getText().equals(kullanici_adi) && sifre_3.equals(sifre_1))) {
		                    updatedList.add(kullanici_listesi);
		                } else {
		                    silme_basarili = true;
		                }
		            }

		            // Dosyayı güncelle
		            try (BufferedWriter writer = new BufferedWriter(new FileWriter(file))) {
		                for (String data : updatedList) {
		                    writer.write(data);
		                    writer.newLine();
		                }
		            }

		            if (silme_basarili) {
		                String message = "Kayıt başarıyla silinmiştir!";
		                JOptionPane.showMessageDialog(null, message);
		            } else {
		                String message = "Böyle bir kayıt bulunamadı. Tekrar deneyiniz!";
		                JOptionPane.showMessageDialog(null, message);
		            }

		        } catch (IOException e1) {
		            String message = "Dosya işlemi sırasında bir hata oluştu.";
		            JOptionPane.showMessageDialog(null, message);
		            e1.printStackTrace();
		        }
		    }
			
		});
		btnKaytSil.setBounds(267, 214, 89, 23);
		contentPane.add(btnKaytSil);
		
		
	}
}
