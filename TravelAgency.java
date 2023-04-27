package TravelAgency;

import java.util.concurrent.Semaphore;

public class TravelAgency {
	
	static Semaphore F = new Semaphore(0);
	static Semaphore C = new Semaphore(0);
	static Semaphore B = new Semaphore(0);
	static Semaphore D = new Semaphore(0);
	static Semaphore H = new Semaphore(0);
	
	
	class Client extends Thread {
		
		public void run() {
			for(int i = 0; i < 5; i++) {
				System.out.println("Client provides data");
				F.release();
				try {
					C.acquire();
					System.out.println("Client pays");
					B.release();
				} catch (InterruptedException e) {
				}
				try {
					D.acquire();
					System.out.println("Purchase number " + i + " done");
				} catch (InterruptedException e) {
				}
			}
		}
	}
	
	class Flight extends Thread {
		
		public void run() {
			for(int i = 0; i < 5; i++) {
				try {
					F.acquire();
					System.out.println("Flight booked");
					H.release();
				} catch (InterruptedException e) {
				}
			}
		}
	}
	
	class Hotel extends Thread {
		
		public void run() {
			for(int i = 0; i < 5; i++) {
				try {
					H.acquire();
					System.out.println("Hotel booked");
					C.release();
				} catch (InterruptedException e) {
				}
			}
		}
	}
	
	class Bank extends Thread {
		
		public void run() {
			for(int i = 0; i < 5; i++) {
				try {
					B.acquire();
					System.out.println("Bank approved payment");
					D.release();
				} catch (InterruptedException e) {
				}
			}
		}
	}
	
	TravelAgency(){
		
		Client C = new Client();
		Flight F = new Flight();
		Hotel H = new Hotel();
		Bank B = new Bank();
		
		C.start();
		F.start();
		H.start();
		B.start();
		
		try {
			C.join();
			F.join();
			H.join();
			B.join();
		} catch (InterruptedException e) {
		}
		System.out.println("Program terminated correctly!");
	}
	
	
	public static void main(String[] args) {
		
		new TravelAgency();
		
	}
	
	
}
