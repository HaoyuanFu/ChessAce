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
	long lastUpdate;
	Main m;
	public countDownTimer(JLabel passedLabel, Main m) {
		countdownTimer = new Timer(1000, new CountdownTimerListener());
		this.label = passedLabel;
		this.m = m;
		Timeremain = m.timeremaining;
	}
	
	public void start() {
		countdownTimer.start();
	}
	
	void resume() {
	    // Restore the time we're counting down from and restart the timer.
	    lastUpdate = System.currentTimeMillis();
	    countdownTimer.start(); // Start the timer
	  }
	
	public void pause() {
		long now = System.currentTimeMillis();
	    Timeremain -= (now - lastUpdate);
	    countdownTimer.stop(); // Stop the timer
	}
	
	public void stop() {
		countdownTimer.stop();
	}
	
	public void reset() {
		Timeremain = m.timeremaining;
	}
	
	public Main getM() {
		return m;
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
				m.playerChange();
				m.gameOver();
			}
		
		}
	}
}