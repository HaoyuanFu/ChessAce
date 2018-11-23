package board;

import board.Main;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JLabel;
import javax.swing.Timer;
/**
 *  \brief timer class, Implementation for timer's GUI function.
 */
public class countDownTimer {
	private JLabel label;
	Timer countdownTimer;
	int Timeremain;
	public countDownTimer(JLabel passedLabel) {
		countdownTimer = new Timer(1000, new CountdownTimerListener());
		this.label = passedLabel;
		Timeremain = Main.timeremaining;
	}
	
	public void start() {
		countdownTimer.start();
	}
	
	public void reset() {
		Timeremain = Main.timeremaining;
	}

	class CountdownTimerListener implements ActionListener{

		@Override
		public void actionPerformed(ActionEvent e) {
			int min, sec;
			if (Timeremain > 0) {
				min = Timeremain/60;
				sec = Timeremain%60;
				label.setText(String.valueOf(min)+":"+(sec>=10?String.valueOf(sec):"0"+String.valueOf(sec)));
				Timeremain--;
			}
			else {
				label.setText("Time up! Game Over!");
			}
		
		}
	}
}