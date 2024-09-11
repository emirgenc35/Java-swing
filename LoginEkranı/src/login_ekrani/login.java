package login_ekrani;

import java.awt.EventQueue;
import javax.swing.JOptionPane;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;
import javax.swing.JLabel;
import javax.swing.JTextField;
import javax.swing.JButton;
import java.awt.event.ActionListener;
import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.util.Scanner;
import java.awt.event.ActionEvent;
import javax.swing.JPasswordField;

public class login extends JFrame {

	private static final long serialVersionUID = 1L;
	private JPanel contentPane;
	private JTextField textField_1;
	private JPasswordField sifre;

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					login frame = new login();
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
	public login() {
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 450, 300);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));

		setContentPane(contentPane);
		contentPane.setLayout(null);
		
		JLabel lblKullancAd = new JLabel("Kullanıcı Adı  : ");
		lblKullancAd.setBounds(46, 67, 77, 14);
		contentPane.add(lblKullancAd);
		
		JLabel lblifre = new JLabel("Şifre :");
		lblifre.setBounds(46, 126, 46, 14);
		contentPane.add(lblifre);
		
		textField_1 = new JTextField();
		textField_1.setBounds(134, 64, 86, 20);
		contentPane.add(textField_1);
		textField_1.setColumns(10);
		
		JButton btnGiri = new JButton("Giriş");
		btnGiri.addActionListener(new ActionListener() {
			
			public void actionPerformed(ActionEvent e) {
				boolean giris_basarili = false;
					try(Scanner scanner = new Scanner(new BufferedReader(new FileReader("sifre.txt")))){
						while(scanner.hasNextLine()) {
							String liste = scanner.nextLine();
							String[] liste_txt = liste.split(" ");
							
						
							
							if(liste_txt[0].equals(textField_1.getText()) && liste_txt[1].equals(new String(sifre.getPassword()))) {
								giris_basarili = true;
								String message = "Başarıyla Giriş Yaptınız !";
								JOptionPane.showMessageDialog(null, message);
							}	
						}
						if(!giris_basarili) {
							String message = "Hatalı Giriş Lütfen Tekrar Deneyiniz !!!";
							JOptionPane.showMessageDialog(null, message);
						}
					} catch (FileNotFoundException e1) {
						System.out.println("Dosya Bulunamadı");
						e1.printStackTrace();
					}

			}
			
		});
		btnGiri.setBounds(134, 197, 89, 23);
		contentPane.add(btnGiri);
		
		JLabel lblGIR = new JLabel("G İ R İ Ş   Y A P");
		lblGIR.setBounds(152, 11, 107, 23);
		contentPane.add(lblGIR);
		
		sifre = new JPasswordField();
		sifre.setBounds(134, 123, 86, 20);
		contentPane.add(sifre);
	}
}
