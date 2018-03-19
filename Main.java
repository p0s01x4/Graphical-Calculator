import java.awt.EventQueue;

public class Main {

	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					Calculator window = new Calculator();
					
					
					
				window.setVisible(true);
				window.setResizable(false);
				
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}
}
