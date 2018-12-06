package board;

import board.Main;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JLabel;
import javax.swing.Timer;

/**
 * timer class, Implementation for timer's GUI function.
 * Reference: 1. ChessOOP Time function; 
 *			  2. countdown timer; https://www.cs.uic.edu/pub/CS342/LecturesS16/CountDown.java
 * 
 * @author Harry Fu
 * @version 1.0 (current version number of program)
 * @since 0.0
 */
public class countDownTimer {
	private JLabel label;
	Timer countdownTimer;
	int Timeremain;
	Main m;

	/**
	 * Constructor of countDownTimer reference Main class. set up reference the
	 * initiate the time. Set up the count down time based on initial settings on
	 * the welcome page.
	 */
	public countDownTimer(JLabel passedLabel, Main m) {
		countdownTimer = new Timer(1000, new CountdownTimerListener());
		this.label = passedLabel;
		this.m = m;
		Timeremain = m.timeremaining;
	}

	/**
	 * This function starts the timer.
	 */
	public void start() {
		countdownTimer.start();
	}

	/**
	 * This function stops the timer.
	 */
	public void stop() {
		countdownTimer.stop();
	}

	/**
	 * This function resets the timer based on initial settings on the welcome page
	 */
	public void reset() {
		Timeremain = m.timeremaining;
	}

	class CountdownTimerListener implements ActionListener {

		@Override
		/**
		 * Every tick of the timer will update the count down time display and
		 * discrement the Timeremain value by 1. Once Timeremain hits 0, the game is
		 * terminated by calling gameOver to evaluate the current situation and provide
		 * who is the winner.
		 */
		public void actionPerformed(ActionEvent e) {
			int min, sec;
			if (Timeremain > 0) {
				min = Timeremain / 60;
				sec = Timeremain % 60;
				label.setText(
						String.valueOf(min) + ":" + (sec >= 10 ? String.valueOf(sec) : "0" + String.valueOf(sec)));
				Timeremain--;
			} else {
				label.setText("Time up! Game Over!");
				m.playerChange();
				m.gameOver();
			}

		}
	}
}